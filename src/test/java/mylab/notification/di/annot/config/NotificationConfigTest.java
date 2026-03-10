package mylab.notification.di.annot.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import mylab.notification.di.annot.EmailNotificationService;
import mylab.notification.di.annot.NotificationManager;
import mylab.notification.di.annot.SmsNotificationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = NotificationConfig.class)
public class NotificationConfigTest {

    @Autowired
    private NotificationManager notificationManager;

    @Test
    public void testNotificationManager() {
        // a. NotificationManager 주입 검증
        assertNotNull(notificationManager, "NotificationManager 빈이 주입되지 않았습니다.");

        // b & c. 이메일 서비스 검증
        EmailNotificationService emailService = (EmailNotificationService) notificationManager.getEmailService();
        assertNotNull(emailService, "EmailService가 주입되지 않았습니다.");
        assertEquals("smtp.gmail.com", emailService.getSmtpServer());
        assertEquals(587, emailService.getPort());

        // d. SMS 서비스 검증
        SmsNotificationService smsService = (SmsNotificationService) notificationManager.getSmsService();
        assertNotNull(smsService, "SmsService가 주입되지 않았습니다.");
        assertEquals("SKT", smsService.getProvider());

        // e. 메서드 실행
        notificationManager.sendNotificationByEmail("테스트 이메일");
        notificationManager.sendNotificationBySms("테스트 SMS");
    }
}