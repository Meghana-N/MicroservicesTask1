package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.lang.annotation.Documented;

@Document(value = "Userdata")//This declare this class as Document
@Data//Implicitly adding getters and setters
@NoArgsConstructor//Implicitly adding no args constructor
@AllArgsConstructor//Implicitly adding all args constructor
public class User {

    @Id
    private int userid;
    private String password;
    private String emaild;

    @Override
    public String toString() {
        return "Track{" +
                "trackid=" + userid +
                ", trackname='" + password + '\'' +
                ", comments='" + emaild + '\'' +
                '}';
    }
}
