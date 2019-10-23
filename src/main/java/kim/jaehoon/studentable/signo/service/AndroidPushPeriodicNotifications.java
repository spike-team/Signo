package kim.jaehoon.studentable.signo.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson(String deviceToken, String title, String content) throws JSONException {
        JSONObject body = new JSONObject();
        JSONArray array = new JSONArray();

        array.put(deviceToken);

        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", content);

        body.put("registration_ids", array);
        body.put("notification", notification);

        return body.toString();
    }
}
