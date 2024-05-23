package com.smartkey.finger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class UserFinger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fingerId;
    private String userName;
    private String phone;
    private String email;
    private String gender;
    private Date creatAt;
}
