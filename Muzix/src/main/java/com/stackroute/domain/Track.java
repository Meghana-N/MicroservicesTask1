package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Playlist")//This declare this class as Document
@Data//Implicitly adding getters and setters
@NoArgsConstructor//Implicitly adding no args constructor
@AllArgsConstructor//Implicitly adding all args constructor
public class Track {

    @Id//making the trackid as the ID
    private int trackid;

    private String trackname;

    private String comments;

    @Override
    public String toString() {
        return "Track{" +
                "trackid=" + trackid +
                ", trackname='" + trackname + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
