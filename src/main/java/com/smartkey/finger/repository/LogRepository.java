package com.smartkey.finger.repository;

import com.smartkey.finger.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
