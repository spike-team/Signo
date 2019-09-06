package kim.jaehoon.studentable.signo.notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson() throws JSONException {
        LocalDate localDate = LocalDate.now();
        JSONObject body = new JSONObject();

        List<String> tokens = List.of("eyfgo71tp38:APA91bGKzByb1OnC9IZZzbFjI9bpOswXXRk1-ysNEoe0pljlek_HOZGqHqPl2A7lk0dI1fydKTtz-QiNeJPMEqJisGaBBrcgGrEoebn3ogg7D2Pg4UOuUTyAEIcUj-c8G_HPea82nQvI");

        JSONArray array = new JSONArray();

        for (String token: tokens) {
            array.put(token);
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title", "긴급사항안내");
        notification.put("body", localDate.getDayOfWeek().name() + "박영진 바보로 들어나... [충격]");

        body.put("notification", notification);
        System.out.println(body.toString());

        return body.toString();
    }
}
