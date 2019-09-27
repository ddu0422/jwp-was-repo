package webserver.http.response;

import java.util.UUID;

public class GeneratedSessionIdStrategy implements GeneratedId {
    @Override
    public String create() {
        return UUID.randomUUID().toString();
    }
}
