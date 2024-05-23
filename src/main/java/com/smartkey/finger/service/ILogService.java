package com.smartkey.finger.service;

import com.smartkey.finger.entity.Log;

public interface ILogService {
    Log addLog(Log log, String fingerId);
}
