package pl.webd.dawid124.simpleratingservice.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReportScheduler {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static final String LAST_DAY_ON_MONTH = "0 0 0 21 * ?";

    @Scheduled(cron = LAST_DAY_ON_MONTH)
    public void createReport() {
        LOGGER.info("Run ReportScheduler");
    }
}
