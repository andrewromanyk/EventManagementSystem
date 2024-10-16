package ua.edu.ukma.event_management_system.logger;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.LayoutBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomLayout extends LayoutBase<ILoggingEvent> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String doLayout(ILoggingEvent iLoggingEvent) {
        return "[" +
                iLoggingEvent.getLevel() +
                "] " +
                DATE_FORMAT.format(new Date(iLoggingEvent.getTimeStamp())) +
                " in (" + iLoggingEvent.getLoggerName() +
                ") at {" +
                iLoggingEvent.getThreadName() +
                "}" +
                " | " +
                iLoggingEvent.getFormattedMessage() +
                '\n';
    }
}
