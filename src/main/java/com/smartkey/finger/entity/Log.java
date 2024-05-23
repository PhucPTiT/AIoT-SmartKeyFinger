package com.smartkey.finger.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean action;
    private Date time;

    @ManyToOne
    @JoinColumn(name = "user_finger_id")
    private UserFinger userFinger;
}
