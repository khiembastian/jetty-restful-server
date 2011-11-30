package org.jettyrest.service;

import org.eclipse.jetty.http.HttpHeaders;
import org.eclipse.jetty.server.NCSARequestLog;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;

import java.io.IOException;

public class NCSARequestLogExt extends NCSARequestLog {


    @Override
    protected void logExtended(Request request, Response response, StringBuilder b) throws IOException {
        super.logExtended(request, response, b);
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization == null)
            b.append("\"-\" ");
        else {
            b.append(" \"");
            b.append(authorization);
            b.append("\"");
        }

        String acceptEncoding = request.getHeader(HttpHeaders.ACCEPT_ENCODING);
        if (acceptEncoding == null)
            b.append("\"-\" ");
        else {
            b.append(" \"");
            b.append(HttpHeaders.ACCEPT_ENCODING).append(": ");
            b.append(acceptEncoding);
            b.append("\"");
        }
    }
}
