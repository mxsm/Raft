package com.github.mxsm.raft.web.listen;

import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author mxsm
 * @Date 2020/1/8 23:34
 */
@Component
public class RunningConfig implements ApplicationListener<WebServerInitializedEvent> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static int serverPort;

    private static volatile boolean webServerInitialized = false;

    private static String contextPath;

    @Autowired
    private ServletContext servletContext;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        serverPort = event.getWebServer().getPort();
        webServerInitialized = true;
        contextPath = servletContext.getContextPath();
        logger.info("Server Initialized Port:{} , contextPath:{}",serverPort,contextPath);
    }

    public static int getServerPort() {
        return serverPort;
    }

    public static boolean isWebServerInitialized() {
        return webServerInitialized;
    }

    public static String getContextPath() {
        return contextPath;
    }
}
