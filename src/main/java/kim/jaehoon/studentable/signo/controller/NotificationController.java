package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.service.AndroidPushNotificationsService;
import kim.jaehoon.studentable.signo.util.AndroidPushPeriodicNotifications;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @ResponseBody
    @GetMapping(value = "/send")
    public ResponseEntity<String> send(
            @RequestParam String deviceToken, @RequestParam String title, @RequestParam String content
    ) throws JSONException, InterruptedException {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson(deviceToken, title, content);

        HttpEntity<String> request = new HttpEntity<>(notifications);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            logger.debug("got interrupted!");
            throw new InterruptedException();
        } catch (ExecutionException e) {
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR", HttpStatus.BAD_REQUEST);
    }
}
