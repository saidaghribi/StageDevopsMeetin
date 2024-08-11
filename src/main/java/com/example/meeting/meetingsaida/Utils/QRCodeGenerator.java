package com.example.meeting.meetingsaida.Utils;

import com.example.meeting.meetingsaida.entities.Meeting;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class QRCodeGenerator {
    public static void generateQRCode(Meeting meeting) throws WriterException, IOException {
        String qrCodePath = "C:\\Users\\SAIDA\\QRCodeMeeting\\";
        String qrCodeName = qrCodePath+meeting.getLink()+meeting.getUuid()+"-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(
                "uuid: "+meeting.getUuid()+ "\n"+
                        "link: "+meeting.getLink()+ "\n"+
                        "date: "+meeting.getDate(), BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }
}
