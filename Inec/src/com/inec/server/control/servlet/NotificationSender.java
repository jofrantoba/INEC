package com.inec.server.control.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class NotificationSender extends HttpServlet {
    private static final Logger log = Logger.getLogger(NotificationSender.class.getName());

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.severe("EXECUTING TASK FROM QUEUE " + req.getParameter("job_id"));
    }
}
