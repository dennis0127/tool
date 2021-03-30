package com.yyyow.tool.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HttpErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error";


    @RequestMapping(value = ERROR_PATH)
    public String errorHtml(HttpServletRequest request, HttpServletResponse response) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        request.setAttribute("code", statusCode);

        response.setStatus(statusCode);
        request.setAttribute("footerKey", "index");
        if (statusCode == 404) {
            return "err/404";
        }

        return "err/err";

    }


}
