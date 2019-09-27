package was.controller;

import db.DataBase;
import was.template.HandlebarTemplates;
import webserver.http.request.HttpRequest;
import webserver.http.response.HttpResponse;

public class ListUserController extends AbstractController {
    private ListUserController() {}

    public static ListUserController getInstance() {
        return ListUserController.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final ListUserController INSTANCE = new ListUserController();
    }

    @Override
    void doGet(final HttpRequest httpRequest, final HttpResponse httpResponse) {
        System.out.println(httpRequest);
        if (httpRequest.getHttpHeader().get("Cookie").contains("logined=true")) {
            HandlebarTemplates handlebarTemplates = new HandlebarTemplates("/templates", "user/list", ".html");

            handlebarTemplates.put("users", DataBase.findAll());

            httpResponse.forward("/user/list.html", handlebarTemplates.apply().getBytes());
            return;
        }

        httpResponse.redirect("/user/login.html");
    }
}
