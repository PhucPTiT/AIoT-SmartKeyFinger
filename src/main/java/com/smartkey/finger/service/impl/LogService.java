package com.smartkey.finger.service.impl;

import com.smartkey.finger.entity.Log;
import com.smartkey.finger.entity.UserFinger;
import com.smartkey.finger.repository.LogRepository;
import com.smartkey.finger.repository.UserFingerRepository;
import com.smartkey.finger.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogService implements ILogService {

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private UserFingerRepository userFingerRepository;
    @Override
    public Log addLog(Log log, String fingerId) {
        UserFinger userFinger = new UserFinger();
        userFinger = userFingerRepository.findByFingerId(fingerId);
        log.setUserFinger(userFinger);
        log.setTime(new Date());
        return logRepository.save(log);
    }
}
