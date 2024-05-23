package com.smartkey.finger.repository;

import com.smartkey.finger.entity.UserFinger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFingerRepository extends JpaRepository<UserFinger, Long> {
    UserFinger findByFingerId(String fingerId);
}
