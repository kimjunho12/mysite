package com.poscoict.web.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcUtil {
    public static void forward(String path, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp").forward(request, response);
    }

    public static void redirect(String url, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.sendRedirect(url);
    }
}
