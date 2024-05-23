package com.smartkey.finger.controller;

import com.smartkey.finger.entity.UserFinger;
import com.smartkey.finger.repository.UserFingerRepository;
import com.smartkey.finger.service.impl.MqttControlService;
import com.smartkey.finger.service.impl.UserFingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/control")
public class Remote {
    @Autowired
    private MqttControlService mqttControlService;

    @Autowired
    private UserFingerService userFingerService;

    @Autowired
    private UserFingerRepository userFingerRepository;

    @PostMapping
    public ResponseEntity<?> addControlLog(@RequestBody UserFinger userFinger) {
        try {
            mqttControlService.control(userFinger.getFingerId());
            String res = mqttControlService.receiveFromMqtt();
            if (res != null) {
                userFingerService.addUserFinger(userFinger);
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add control log");
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateControlLog(@PathVariable Long id, @RequestBody UserFinger userFinger) {
        try {
            mqttControlService.control(userFinger.getFingerId());
            String res = mqttControlService.receiveFromMqtt();
            if (res != null) {
                userFingerService.updateUserFinger(id, userFinger);
                return ResponseEntity.ok(res);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update control log");
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteControlLog(@PathVariable Long id) {
        try {
            //gọi hàm xoá publish mqtt nếu phản hồi thành công thì chayj tiếp xoá trong db
            userFingerRepository.deleteById(id);
            return ResponseEntity.ok("Deleted control log");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }

    // mở cửa
    @PostMapping("/open")
    public ResponseEntity<?> openDoor() {
        try {
            //gọi hàm publish mo cua
            return ResponseEntity.ok("Opened door");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }

    // đóng cửa
    @PostMapping("/close")
    public ResponseEntity<?> closeDoor() {
        try {
            //gọi hàm publish dong cua
            return ResponseEntity.ok("Closed door");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex);
        }
    }
}
