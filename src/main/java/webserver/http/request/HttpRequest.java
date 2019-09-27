package webserver.http.request;

import webserver.http.common.HttpHeader;
import webserver.http.session.GeneratedSessionIdStrategy;
import webserver.http.session.HttpSession;
import webserver.http.session.Session;

public class HttpRequest {
    private RequestLine requestLine;
    private HttpHeader httpHeader;
    private QueryStringParams queryStringParams;
    private HttpSession httpSession;

    public void init(final RequestLine requestLine,
                       final HttpHeader httpHeader,
                       final QueryStringParams queryStringParams) {
        this.requestLine = requestLine;
        this.httpHeader = httpHeader;
        this.queryStringParams = queryStringParams;
        this.httpSession = createSession();
    }

    private HttpSession createSession() {
        String cookie = httpHeader.get("Cookie");

        if (cookie != null && cookie.contains("JSESSIONID")) {
            return Session.getInstance().getHttpSession(httpSession.getId());
        }

        return Session.getInstance().createSession(new GeneratedSessionIdStrategy());
    }

    public void addHeader(final String key, final String value) {
        httpHeader.put(key, value);
    }

    public void removeHeader(final String key) {
        httpHeader.remove(key);
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public HttpHeader getHttpHeader() {
        return httpHeader;
    }

    public QueryStringParams getQueryStringParams() {
        return queryStringParams;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestLine=" + requestLine +
                ", httpHeader=" + httpHeader +
                ", httpRequestParams=" + queryStringParams +
                '}';
    }
}