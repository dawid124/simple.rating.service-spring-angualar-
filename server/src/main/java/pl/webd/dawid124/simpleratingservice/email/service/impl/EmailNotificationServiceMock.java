package pl.webd.dawid124.simpleratingservice.email.service.impl;

import org.springframework.stereotype.Service;
import pl.webd.dawid124.simpleratingservice.email.service.EmailNotificationService;

@Service
public class EmailNotificationServiceMock implements EmailNotificationService {
    @Override
    public void sendEmailNotification(String email, String message) {
        return;
    }
}
