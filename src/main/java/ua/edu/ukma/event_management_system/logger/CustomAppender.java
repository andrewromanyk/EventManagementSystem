package ua.edu.ukma.event_management_system.logger;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;
import org.slf4j.*;
import ua.edu.ukma.event_management_system.controller.BuildingController;

@NoArgsConstructor
@AllArgsConstructor
public class CustomAppender extends AppenderBase<ILoggingEvent> {


    private Layout<ILoggingEvent> layout;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        System.out.println(layout.doLayout(iLoggingEvent));
    }

    public static void main(String[] args) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

        CustomLayout layout = new CustomLayout();

        CustomAppender app = new CustomAppender(layout);
        app.setContext(loggerContext);
        app.start();

        Logger logg = (Logger) LoggerFactory.getLogger(CustomAppender.class);
        logg.addAppender(app);

        logg.info("Hello World");
    }

}
