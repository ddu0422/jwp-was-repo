package webserver.http.response;

import webserver.http.common.HttpHeader;
import webserver.http.common.HttpVersion;

public class HttpResponse {
    private StartLine startLine;
    private HttpHeader httpHeader;
    private HttpResponseBody httpResponseBody;

    public HttpResponse() {
        startLine = new StartLine(HttpVersion.HTTP_1_1);
        httpHeader = new HttpHeader();
        httpResponseBody = HttpResponseBody.empty();
    }

    public void redirect(final String url) {
        setHttpStatus(HttpStatus.FOUND);
        addHeader("Location", url);
    }

    public void forward(final String url, final byte[] file) {
        setHttpStatus(HttpStatus.OK);
        httpResponseBody = HttpResponseBody.of(file);
        addHeader("Content-Type", HttpContentType.findContentType(url));
    }

    public void addHeader(final String key, final String value) {
        httpHeader.put(key, value);
    }

    public void setHttpStatus(final HttpStatus httpStatus) {
        this.startLine.setHttpStatus(httpStatus);
    }

    public StartLine getStartLine() {
        return startLine;
    }

    public HttpHeader getHttpHeader() {
        return httpHeader;
    }

    public HttpResponseBody getHttpResponseBody() {
        return httpResponseBody;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "startLine=" + startLine +
                ", httpHeader=" + httpHeader +
                ", httpResponseBody=" + httpResponseBody +
                '}';
    }
}
