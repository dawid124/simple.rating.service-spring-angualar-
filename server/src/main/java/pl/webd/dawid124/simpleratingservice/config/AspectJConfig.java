package pl.webd.dawid124.simpleratingservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.webd.dawid124.simpleratingservice.comments.service.CommentsService;
import pl.webd.dawid124.simpleratingservice.email.service.EmailNotificationService;
import pl.webd.dawid124.simpleratingservice.interceptors.PerformanceLogAspect;
import pl.webd.dawid124.simpleratingservice.interceptors.SendNotificationsAspect;
import pl.webd.dawid124.simpleratingservice.ratings.service.RatingsService;

@Configuration
@EnableAspectJAutoProxy
public class AspectJConfig {

    private CommentsService commentsService;
    private RatingsService ratingsService;
    private EmailNotificationService emailNotificationService;

    public AspectJConfig(CommentsService commentsService, RatingsService ratingsService, EmailNotificationService emailNotificationService) {
        this.commentsService = commentsService;
        this.ratingsService = ratingsService;
        this.emailNotificationService = emailNotificationService;
    }

    @Bean
    public PerformanceLogAspect performanceAspect(){
        return new PerformanceLogAspect();
    }

    @Bean
    public SendNotificationsAspect notificationsAspect() {
        return new SendNotificationsAspect(commentsService, ratingsService, emailNotificationService);
    }

}
