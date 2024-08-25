package pl.zdzimi.server.core;

import java.io.IOException;
import java.io.InputStream;

public class RequestParser {

    private static final char CARRIAGE_RETURN = 13;

    public Request getRequest(InputStream inputStream) throws IOException {
        Request.Builder requestBuilder = Request.builder();
        String statusLine = "";
        StringBuffer statusLineBuffer = new StringBuffer();
        StringBuffer headerLineBuffer = new StringBuffer();
        StringBuffer bodyBuffer = new StringBuffer();
        boolean allHeadersLoaded = false;
        boolean noBodyMethod = false;
        while (true) {
            char sign = (char) inputStream.read();
            if (statusLine.equals("")) {
                if (sign != CARRIAGE_RETURN) {
                    statusLineBuffer.append(sign);
                } else {
                    inputStream.read();
                    statusLine = statusLineBuffer.toString();
                    noBodyMethod = parseStatusLine(statusLine, requestBuilder);
                }
            } else if (!allHeadersLoaded) {
                if (sign != CARRIAGE_RETURN) {
                    headerLineBuffer.append(sign);
                } else {
                    inputStream.read();
                    String headerLine = headerLineBuffer.toString();
                    if (headerLine.equals("")){
                        allHeadersLoaded = true;
                        if (noBodyMethod) {
                            return requestBuilder.build();
                        }
                    } else {
                        Header header = createHeader(headerLine);
                        requestBuilder.headers(header);
                        headerLineBuffer = new StringBuffer();
                    }
                }
            } else {
                if (sign != CARRIAGE_RETURN) {
                    bodyBuffer.append(sign);
                } else {
                    inputStream.read();
                    String body = bodyBuffer.toString();
                    requestBuilder.body(body);
                    return requestBuilder.build();
                }
            }
        }
    }

    private Header createHeader(String headerLine) {
        String[] split = headerLine.trim().split(" ");
        String key = split[0].substring(0, split[0].length() - 1);
        return new Header(key, split[1]);
    }

    private boolean parseStatusLine(String statusLine, Request.Builder requestBuilder) {
        String[] split = statusLine.split(" ");
        Method method = Method.valueOf(split[0]);
        requestBuilder.method(method);
        requestBuilder.url(split[1]);
        requestBuilder.version(split[2]);
        return method.equals(Method.HEAD) || method.equals(Method.GET);
    }

}
