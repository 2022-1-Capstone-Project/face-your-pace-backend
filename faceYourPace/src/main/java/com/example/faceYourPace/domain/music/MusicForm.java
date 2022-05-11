package com.example.faceYourPace.domain.music;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.sql.Timestamp;


@Getter
@Setter
public class MusicForm {

    private Long id;


    private String musicName; // 음악 이름
    //private String artist; // 가수
    private Time musicStart; // 음악 시작 시간
    private Time musicEnd; // 음악 종료 시간
    private Integer musicRepeat; // 음악 반복 횟수

    private Timestamp createDate;
}
