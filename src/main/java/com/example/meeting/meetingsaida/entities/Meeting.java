package com.example.meeting.meetingsaida.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Data;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    private String link;
    private Date date;
    public Meeting(String link , Date date){
        this.link=link;
        this.date=date;
    }

    @ManyToMany
    @JoinTable(name = "Meeting_users",
            joinColumns = @JoinColumn(name = "meeting_uuid"),
            inverseJoinColumns = @JoinColumn(name = "users_uuid"))
    private Set<User> users = new LinkedHashSet<>();
}
