package com.example.meeting.meetingsaida.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.meeting.meetingsaida.entities.Meeting;
import com.example.meeting.meetingsaida.repositories.MeetingRepository;



import java.util.List;

@Service
@RequiredArgsConstructor

public class MeetingService {

    private final MeetingRepository meetingRepository;
    public List<Meeting> getmeetings(){
        return meetingRepository.findAll();
    }
    public Meeting addmeeting(Meeting meeting){
        return meetingRepository.save(meeting);
    }

    public Meeting findById(Long id){
        return meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("meeting not found"));
    }

    public Meeting getMeetingByUuid(Long id) {
        return meetingRepository.findById(id).orElse(null);
    }

    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Meeting updateMeeting(Long uuid, Meeting meeting) {
        meeting.setUuid(uuid);
        return meetingRepository.save(meeting);
    }

    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }


}
