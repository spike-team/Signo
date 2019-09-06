package kim.jaehoon.studentable.signo.notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson(String deviceToken, String content) throws JSONException {
        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();

        array.put(deviceToken);
        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title", "TEST");
        notification.put("body", content);

        body.put("notification", notification);

        return body.toString();
    }
}
