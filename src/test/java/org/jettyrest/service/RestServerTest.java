package org.jettyrest.service;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RestServerTest {

    private RestServer server;

    @Before
    public void setup() {
        server = new RestServer("classpath:org/jettyrest/service/spring-resource.xml");
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(8098);
        server.setServerConfig(serverConfig);
    }

    @Test
    public void testNotHaveValidAppConfigPath() {

        try {
            server.setAppConfigPath("");
            server.startAndWait();
        } catch (Exception e) {
            assertFalse(server.isRunning());
            return;
        }

        fail("should have throw exception");
    }


    @Test
    public void testBadConfiguration() {

        try {
            server.setAppConfigPath("classpath:_missing_.xml");
            server.startAndWait();
        } catch (Exception e) {
            assertFalse(server.isRunning());
            return;
        }

        fail("should have thrown exception");
    }

    @Test
    public void testNoResources() {
        try {
            server.setAppConfigPath("classpath:spring-empty.xml");
            server.startAndWait();
        } catch (Exception e) {
            assertFalse(server.isRunning());
            return;
        }

        fail("should have throw exception");
    }

    @Test
    public void testResource() throws IOException {

        server.startAndWait();
        assertTrue(server.isRunning());

        URL url = new URL("http://localhost:8098/simple");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String response;
        while ((response = reader.readLine()) != null) {
            assertEquals("OK", response);
        }

        server.stopAndWait();
        assertFalse(server.isRunning());
    }

    @Test
    public void shouldWriteRequestToLog() throws IOException {
        server.getServerConfig().setEnableRequestLog(true);

        NCSARequestLog requestLog = new NCSARequestLog();
        requestLog.setFilename("./target/restServerTest-yyyy_mm_dd-request.log");
        server.getServerConfig().setRequestLog(requestLog);

        server.startAndWait();
        assertTrue(server.isRunning());

        Handler[] handlers = server.jettyServer.getHandlers();
        HandlerCollection handlerCollection = (HandlerCollection) handlers[0];

        assertThat(handlerCollection.getHandlers().length, equalTo(3));

        URL url = new URL("http://localhost:8098/simple");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String response;
        while ((response = reader.readLine()) != null) {
            assertEquals("OK", response);
        }

        server.stopAndWait();
        assertFalse(server.isRunning());
    }
}
