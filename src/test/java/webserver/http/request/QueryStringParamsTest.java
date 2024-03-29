package webserver.http.request;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryStringParamsTest {

    @Test
    void params_생성_테스트() {
        QueryStringParams requestParams = QueryStringParams.of("username=duho&password=1234");

        assertEquals("duho", requestParams.get("username"));
        assertEquals("1234", requestParams.get("password"));
    }

    @Test
    void 값이_없는_경우_빈값_확인() {
        QueryStringParams requestParams = QueryStringParams.of("username=duho&password=");

        assertEquals("", requestParams.get("password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 값이_null_이나_빈_칸이_들어가는_경우(String line) {
        assertEquals(Collections.emptyMap(), QueryStringParams.of(line).getQueryParams());
    }
}