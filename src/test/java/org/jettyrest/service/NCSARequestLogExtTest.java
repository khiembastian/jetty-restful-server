package org.jettyrest.service;

import org.eclipse.jetty.http.HttpHeaders;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class NCSARequestLogExtTest {

    private NCSARequestLogExt requestLog;

    @Mock
    private Request request;

    @Mock
    private Response response;

    @Before
    public void setup() {
        requestLog = new NCSARequestLogExt();
    }


    @Test
    public void shouldAddHeaderAuthorization() throws IOException {

        StringBuilder buffer = new StringBuilder();

        given(request.getHeader(HttpHeaders.REFERER)).willReturn("referrrInfo");
        given(request.getHeader(HttpHeaders.USER_AGENT)).willReturn("Mozilla");
        given(request.getHeader(HttpHeaders.AUTHORIZATION)).willReturn("some authorization string");

        requestLog.logExtended(request, response, buffer);

        assertThat(buffer.toString(), equalTo("\"referrrInfo\" \"Mozilla\" \"some authorization string\"\"-\" "));
    }

    @Test
    public void shouldAddHeaderAcceptEncoding() throws IOException {

        StringBuilder buffer = new StringBuilder();

        given(request.getHeader(HttpHeaders.REFERER)).willReturn("referrrInfo");
        given(request.getHeader(HttpHeaders.USER_AGENT)).willReturn("Mozilla");
        given(request.getHeader(HttpHeaders.ACCEPT_ENCODING)).willReturn("gzip");

        requestLog.logExtended(request, response, buffer);

        assertThat(buffer.toString(), equalTo("\"referrrInfo\" \"Mozilla\"\"-\"  \"Accept-Encoding: gzip\""));
    }
}
