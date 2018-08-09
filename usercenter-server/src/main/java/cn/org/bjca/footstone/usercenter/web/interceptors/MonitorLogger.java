package cn.org.bjca.footstone.usercenter.web.interceptors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author LvYong
 * @create 2018-03-29
 **/
@Slf4j
public final class MonitorLogger {

    private static final String SEPERATER_CHAR          = "^_^";
    private static final int    MAX_LOG_LENGTH          = 5000;
    public static final  String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private MonitorLogger() {}

    public static void log(final String caller, final StringBuilder request, final String response,
                           final long startTime,
                           final long expiredTime, final Exception e) {
        if (!log.isInfoEnabled()) {
            return;
        }
        try {
            StringBuilder logContentBuilder = new StringBuilder(caller);
            logContentBuilder.append("[End]:").append(startTime)
                             .append(SEPERATER_CHAR).append(request == null ? "" : request).append(SEPERATER_CHAR)
                             .append(response.length() > MAX_LOG_LENGTH ? response
                                     .substring(0, MAX_LOG_LENGTH) + "..." : response)
                             .append(SEPERATER_CHAR).append(expiredTime).append(SEPERATER_CHAR)
                             .append(e == null ? false : true);
            if (e != null) {
                logContentBuilder.append(SEPERATER_CHAR).append(e.getMessage());
            }
            log.info(logContentBuilder.toString());
        } catch (Exception ex) {
            log.error("MonitorLogger", ex);
        }
    }
}
