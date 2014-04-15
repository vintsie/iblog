package com.vint.iblog.web.servlet;

import com.vint.iblog.datastore.define.SequenceManagerDAO;
import com.vint.iblog.datastore.define.StaticDataDAO;
import org.apache.commons.lang3.StringUtils;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * Created by Vin on 14-2-17.
 */
public class ConfigurationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cfgType = req.getParameter("cfgType");
        PrintWriter pw = resp.getWriter();
        // 配置静态数据
        if (StringUtils.equals(cfgType, "sd")) {
            String operation = req.getParameter("operation");
            // 新增配置数据
            if (StringUtils.equals("add", operation)) {
                String dataType = req.getParameter("dataType");
                String dataValue = req.getParameter("dataValue");
                String sort = req.getParameter("sort");
                if (StringUtils.isEmpty(dataType) || StringUtils.isEmpty(dataValue)) {
                    pw.write("When creating new static data, neither dataType or dataValue can not be null.");
                } else {
                    try {
                        StaticDataDAO sdd = ServiceFactory.getService(StaticDataDAO.class);
                        sdd.newStaticData(dataType, dataValue, Integer.parseInt(sort));
                        pw.write("success");
                    } catch (Exception e) {
                        pw.write(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } else if (StringUtils.equals(cfgType, "seq")) {

            /**
             * 序列的操作分为两种，read和write.如果传入的operation是read,则只查询
             * 对应Type的序列值。当传入的操作时write时，则会清理对应Type的序列数据，
             * 并使用新的16进制字符串代替。
             *
             * Url实例：
             * http://localhost:8080/cfg?cfgType=seq&operation=read&type=BLOG&hex=101c0701
             * http://localhost:8080/cfg?cfgType=seq&operation=wirte&type=BLOG&hex=101c0701
             */
            String type = req.getParameter("type");
            String hex = req.getParameter("hex");
            String operation = req.getParameter("operation");
            try {
                SequenceManagerDAO sequenceManagerDAO = ServiceFactory.getService(SequenceManagerDAO.class);
                if (StringUtils.equals("read", operation)) {
                    pw.write(type + "'s current Sequence is " + sequenceManagerDAO.getCurrentSeq(type));
                } else if (StringUtils.equals("write", operation)) {
                    sequenceManagerDAO.createSequence(type, hex);
                    pw.write("Success!!");
                }
            } catch (Exception e) {
                pw.write(e.getMessage());
                e.printStackTrace();
            }
        } else {
            pw.write("No mapping operation found according to " + cfgType);
        }
        pw.flush();
        pw.close();
    }
}
