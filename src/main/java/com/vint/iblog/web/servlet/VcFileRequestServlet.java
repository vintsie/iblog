package com.vint.iblog.web.servlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * Created by Vin on 14-4-27.
 */
public class VcFileRequestServlet extends HttpServlet {

    static transient Log log = LogFactory.getLog(VcFileRequestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String vcType = req.getParameter("vcType");
        //req.setCharacterEncoding("utf-8");
        PrintWriter pw = resp.getWriter();
        if("gh".equalsIgnoreCase(vcType)){
            String contentType = req.getContentType();
            log.error(contentType);
            String path = req.getParameter("path");
            log.error(path);

        }else{
            pw.write("unsupported version control type.");
        }

        pw.flush();
        pw.close();
    }
}
