package pl.webd.dawid124.simpleratingservice.email.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailNotificationService {

    void sendEmailNotification(String email, String message);
}
