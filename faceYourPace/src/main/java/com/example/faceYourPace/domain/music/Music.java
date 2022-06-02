package com.example.faceYourPace.domain.music;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public class Music {

    @Id
    @GeneratedValue
    @Column(name = "music_id")
    private Long id;

    private String userId; // 음악 추가한 userId

    //private String name; // 플레이리스트 이름

    private String musicName; // 음악 이름 <- 폴더명으로 받아옴
    private String musicStart; // 음악 시작 시간
    private String musicEnd; // 음악 종료 시간
    private String musicRepeat; // 음악 반복 횟수

    private String musicImg_url; // 음악 사진
    private String music_url; // 음악 사진
    private String length; // 음악 전체 길이
    private String title; // 음악 제목

    private String target_bpm; //target_bpm

    private String s3Title; // s3에서 파일명

    private LocalDateTime createDate;

}
