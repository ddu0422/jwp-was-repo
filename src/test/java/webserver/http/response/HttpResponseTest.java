package webserver.http.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HttpResponseTest {

    @Test
    void redirect_응답() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.redirect("/index.html");

        assertEquals("HTTP/1.1 302 FOUND", httpResponse.getStatusLine().toString());
        assertEquals("/index.html", httpResponse.getHttpHeader().get("Location"));
    }

    @Test
    void forward_응답() {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.forward("/index.html", "<html></html>".getBytes());

        assertEquals("HTTP/1.1 200 OK", httpResponse.getStatusLine().toString());
        assertEquals("text/html", httpResponse.getHttpHeader().get("Content-Type"));
    }
}