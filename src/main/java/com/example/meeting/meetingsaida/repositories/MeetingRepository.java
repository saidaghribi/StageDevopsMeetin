package com.example.meeting.meetingsaida.repositories;

import com.example.meeting.meetingsaida.entities.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
