package com.smartkey.finger.service.impl;

import com.smartkey.finger.entity.UserFinger;
import com.smartkey.finger.repository.UserFingerRepository;
import com.smartkey.finger.service.IUserFingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserFingerService implements IUserFingerService {

    @Autowired
    private UserFingerRepository userFingerRepository;
    @Override
    public UserFinger addUserFinger(UserFinger userFinger) {
        UserFinger ex = userFingerRepository.findByFingerId(userFinger.getFingerId());
        if(ex != null) {
            throw new RuntimeException("FingerId already exists");
        }
        userFinger.setCreatAt(new Date());
        return userFingerRepository.save(userFinger);
    }

    @Override
    public UserFinger updateUserFinger(Long id, UserFinger userFinger) {
        Optional<UserFinger> ex = userFingerRepository.findById(id);
        if(ex == null) {
            throw new RuntimeException("UserFinger not found");
        }
        if(userFinger.getFingerId() != null) {
            ex.get().setFingerId(userFinger.getFingerId());
        }
        if(userFinger.getUserName() != null) {
            ex.get().setUserName(userFinger.getUserName());
        }
        if(userFinger.getEmail() != null) {
            ex.get().setEmail(userFinger.getEmail());
        }
        if(userFinger.getPhone() != null) {
            ex.get().setPhone(userFinger.getPhone());
        }
        if(userFinger.getGender() != null) {
            ex.get().setGender(userFinger.getGender());
        }
        return userFingerRepository.save(ex.get());
    }


}
