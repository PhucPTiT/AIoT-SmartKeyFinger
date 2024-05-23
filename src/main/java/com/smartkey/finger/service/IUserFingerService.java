package com.smartkey.finger.service;

import com.smartkey.finger.entity.UserFinger;
import org.apache.catalina.User;

public interface IUserFingerService {
    UserFinger addUserFinger(UserFinger userFinger);
    UserFinger updateUserFinger(Long id, UserFinger userFinger);
}
