package org.jettyrest.service;

import com.google.common.util.concurrent.AbstractService;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.RequestLogHandler;
import org.eclipse.jetty.server.handler.StatisticsHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;

public class RestServer extends AbstractService {

    private Logger log = LoggerFactory.getLogger(RestServer.class);

    Server jettyServer;

    private String appConfigPath;

    private ServerConfig serverConfig;

    private ServletHolder jerseyServlet;

    private boolean initialized;

    public RestServer(String appConfigPath) {
        setAppConfigPath(appConfigPath);
        String gzipFilter = "com.sun.jersey.api.container.filter.GZIPContentEncodingFilter";

        jerseyServlet = new ServletHolder(new SpringServlet());
        jerseyServlet.setInitParameter(JSONConfiguration.FEATURE_POJO_MAPPING, "true");
        jerseyServlet.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", gzipFilter);
        jerseyServlet.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", gzipFilter);
    }

    public void setAppConfigPath(String appConfigPath) {
        log.info("Using application context configuration at {}", appConfigPath);
        this.appConfigPath = appConfigPath;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        log.info("Server configuration: {}", serverConfig);
        this.serverConfig = serverConfig;
    }

    public ServerConfig getServerConfig() {
        return this.serverConfig;
    }


    protected void init() {
        log.info("Initializing server");

        if (serverConfig == null) {
            serverConfig = new ServerConfig();
        }

        jettyServer = new Server(serverConfig.getPort());

        if (serverConfig.getThreadPool() != null) {
            jettyServer.setThreadPool(serverConfig.getThreadPool());
            log.info("added {} threadpool", serverConfig.getThreadPool());
        }

        if (serverConfig.isEnableSSL() && serverConfig.getSslConnector() != null) {
            jettyServer.addConnector(serverConfig.getSslConnector());
            log.info("added {} ssl connector", serverConfig.getSslConnector());
        }

        for (Connector connector : jettyServer.getConnectors()) {
            connector.setRequestHeaderSize(serverConfig.getRequestHeaderSize());
            connector.setRequestBufferSize(serverConfig.getRequestBufferSize());
            connector.setResponseHeaderSize(serverConfig.getResponseHeaderSize());
            connector.setResponseBufferSize(serverConfig.getResponseBufferSize());
        }

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        contextHandler.setContextPath("/");
        contextHandler.getInitParams().put("contextConfigLocation", appConfigPath);
        contextHandler.addEventListener(new ContextLoaderListener());
        contextHandler.addServlet(jerseyServlet, "/*");


        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.addHandler(contextHandler);


        addRequestLog(contexts, serverConfig);

        contexts.addHandler(new StatisticsHandler());

        jettyServer.setHandler(contexts);

        initialized = true;
    }

    @Override
    protected void doStart() {
        log.info("Starting server...");
        try {

            init();

            jettyServer.start();

            if (jettyServer.getHandler().isFailed()) {
                throw new Exception("failed to initialize spring context");
            }

            if (!jerseyServlet.isAvailable()) {
                throw new Exception("failed to initialize Jersey servlet");
            }

            notifyStarted();
        } catch (Exception e) {
            doStop();
            notifyFailed(e);
        } finally {
            log.info("doStart current state {}", state().name());
        }
    }

    @Override
    protected void doStop() {
        log.info("Stopping jettyServer...");
        try {
            if (jettyServer != null) {
                for (Connector connector : jettyServer.getConnectors()) {
                    connector.close();
                }

                if (jettyServer.isStarted()) {
                    jettyServer.stop();
                    notifyStopped();
                }
            }
        } catch (Exception e) {
            notifyFailed(e);
        } finally {
            log.info("doStop current state {}", state().name());
        }
    }


    private void addRequestLog(ContextHandlerCollection contexts, ServerConfig serverConfig) {

        if (serverConfig.isEnableRequestLog() && serverConfig.getRequestLog() != null) {
            log.info("adding request logging {}", serverConfig.getRequestLog());
            RequestLogHandler requestLogHandler = new RequestLogHandler();
            requestLogHandler.setRequestLog(serverConfig.getRequestLog());
            contexts.addHandler(requestLogHandler);
        }

    }
}
