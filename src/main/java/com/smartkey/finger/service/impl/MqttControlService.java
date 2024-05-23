package com.smartkey.finger.service.impl;

import com.smartkey.finger.config.MqttGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public class MqttControlService {
    @Autowired
    private MqttGateway mqttGateway;
    @Autowired
    private MessageChannel mqttInputChannel;

    public void control(String s) {
        String topic = "enroll_fingerprint";
        try {
            mqttGateway.senToMqtt(s, topic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String receiveFromMqtt() {
        QueueChannel queueChannel = (QueueChannel) mqttInputChannel;
        Message<?> receivedMessage = queueChannel.receive(90000000); // timeout in milliseconds
        while ((receivedMessage = queueChannel.receive(90000000)) != null) { // timeout in milliseconds
            String topic = receivedMessage.getHeaders().get("mqtt_receivedTopic").toString();
            String payload = receivedMessage.getPayload().toString();
            if ("rep".equals(topic) && "ok".equals(payload)) {
                System.out.println("Topic 'rep' has message 'ok'");
                return payload;
            }
            if("rep".equals(topic) && "notok".equals(payload)) {
                System.out.println("Topic 'rep' has message 'fail'");
                return null;
            }
        }
        return null;
    }
}
