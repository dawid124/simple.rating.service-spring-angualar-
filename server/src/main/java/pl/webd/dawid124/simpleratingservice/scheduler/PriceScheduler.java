package pl.webd.dawid124.simpleratingservice.scheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PriceScheduler {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    public static final String EVERY_DAY_AT_2 = "0 0 2 * * ?";

//    @Scheduled(initialDelay = 0, cron = 5000)
    @Scheduled(cron = EVERY_DAY_AT_2)
    private void updatePrice() {
        LOGGER.info("Run PriceScheduler");
    }
}
