package utils;

import spark.Request;

public class ResponseTypeUtil {
    public static boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }
}
