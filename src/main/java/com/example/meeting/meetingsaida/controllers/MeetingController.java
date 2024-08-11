package com.example.meeting.meetingsaida.controllers;

import com.example.meeting.meetingsaida.Utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.meeting.meetingsaida.entities.Meeting;
import com.example.meeting.meetingsaida.services.MeetingService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meetings")
public class MeetingController {
    private final MeetingService meetingService;
    @GetMapping
    public ResponseEntity<List<Meeting>> getmeetings() throws IOException, WriterException {
        List<Meeting> meetings = meetingService.getmeetings();
        if (!meetings.isEmpty()){
            for (Meeting meeting : meetings){
                QRCodeGenerator.generateQRCode(meeting);
            }
        }
        return ResponseEntity.ok(meetingService.getmeetings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Meeting meeting = meetingService.getMeetingByUuid(id);
        if (meeting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(meeting);
    }

    @PostMapping("/add")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        Meeting createdMeeting = meetingService.createMeeting(meeting);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeeting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, @RequestBody Meeting meeting) {
        Meeting updatedMeeting = meetingService.updateMeeting(id, meeting);
        if (updatedMeeting == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMeeting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.ok().build();

    }

}
