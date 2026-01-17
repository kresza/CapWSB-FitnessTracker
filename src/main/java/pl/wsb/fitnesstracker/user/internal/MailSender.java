package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.mail.api.EmailDto;
import pl.wsb.fitnesstracker.mail.api.EmailSender;

@Component
@RequiredArgsConstructor
public class MailSender implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void send(EmailDto email) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.toAddress());
        message.setSubject(email.subject());
        message.setFrom("system@info.com");
        message.setText(email.content());
        mailSender.send(message);
    }
}
