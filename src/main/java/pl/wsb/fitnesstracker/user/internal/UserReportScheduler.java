package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserReportScheduler {
    private final UserServiceImpl userService;
    private final MailSender mailSender;

@Scheduled(cron = "0 0 0 ? * MON")
    void scheduler(){
        final LocalDate today = LocalDate.from(LocalDate.now().atStartOfDay());
        final LocalDate lastMonday = today.minusDays(7);
        final Date startDate = Date.from(lastMonday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        userService.generateReport(startDate, Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()));

    }


}
