package pl.webd.dawid124.simpleratingservice.interceptors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.webd.dawid124.simpleratingservice.comments.model.ProductRelation;
import pl.webd.dawid124.simpleratingservice.comments.service.CommentsService;
import pl.webd.dawid124.simpleratingservice.email.service.EmailNotificationService;
import pl.webd.dawid124.simpleratingservice.ratings.service.RatingsService;

import java.util.List;
import java.util.stream.Stream;

@Aspect
public class SendNotificationsAspect {

    public static final String MESSAGE = "Nowa aktywność dla produktu który oceniłeś lub skomentowałeś";
    private CommentsService commentsService;
    private RatingsService ratingsService;
    private EmailNotificationService emailNotificationService;

    public SendNotificationsAspect(CommentsService commentsService, RatingsService ratingsService, EmailNotificationService emailNotificationService) {
        this.commentsService = commentsService;
        this.ratingsService = ratingsService;
        this.emailNotificationService = emailNotificationService;
    }

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(SendNotifications)")
    public void allMethodWithAnnotation() {}

    @Pointcut("execution(public * *(..))")
    public void publicMethod() {}

    @AfterReturning(value = "publicMethod() && allMethodWithAnnotation()", returning = "result")
    public void logExecutionTime(JoinPoint joinPoint, ProductRelation result) {
        List<String> allCommentingUsers = commentsService.getCommentingUsers(result.getProductId());
        List<String> allRatingUsers = ratingsService.getRatingUsers(result.getProductId());

        Stream<String> usersToSendNotification = Stream.concat(allCommentingUsers.stream(), allRatingUsers.stream());
        usersToSendNotification.forEach(email -> emailNotificationService.sendEmailNotification(email, MESSAGE));
    }
}
