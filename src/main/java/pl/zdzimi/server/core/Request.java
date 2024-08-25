package pl.zdzimi.server.core;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private final Method method;
    private final String url;
    private final String version;
    private final List<Header> headers;
    private final String body;

    private Request(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.version = builder.version;
        this.headers = builder.headers;
        this.body = builder.body;
    }

    static Builder builder() {
        return new Builder();
    }

    public Method getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }

    public List<Header> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        String headersAsString = "";
        for (Header header : headers) {
            headersAsString = headersAsString + header + "\n";
        }
        return String.format("Request:\nmethod: %s\nurl: %s\nversion: %s\nheaders:\n%sbody: %s", method, url, version, headersAsString, body);
    }

    static class Builder {

        private Method method;
        private String url;
        private String version;
        private List<Header> headers = new ArrayList<>();
        private String body;

        public Builder method(Method method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder headers(Header header) {
            this.headers.add(header);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Request build() {
            return new Request(this);
        }

    }

}
