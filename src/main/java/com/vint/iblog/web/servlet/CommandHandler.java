package com.vint.iblog.web.servlet;

import com.vint.iblog.command.service.impl.CacheRefreshExe;
import com.vint.iblog.command.service.impl.DevTempExe;
import com.vint.iblog.command.service.impl.GitHubRefreshExe;
import com.vint.iblog.command.service.interfaces.ICommandExe;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 命令处理分发器
 *@author vin
 * Created by Vin on 14-5-17.
 */
public class CommandHandler extends HttpServlet {

    private transient static Log log = LogFactory.getLog(CommandHandler.class);

    private static HashMap<String, String> handlers = new HashMap<String, String>() {
        {
            put("C001", GitHubRefreshExe.class.getName());
            put("C002", CacheRefreshExe.class.getName());
            put("dev", DevTempExe.class.getName());
        }
    };

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        try {
            String command = req.getParameter("cmd");
            if (StringUtils.isEmpty(command)) {
                pw.write("No command found.");
            } else if (!handlers.containsKey(command)) {
                pw.write("No command mapping found.");
            } else {
                long startTime = System.currentTimeMillis();
                ICommandExe exe = (ICommandExe) Class.forName(handlers.get(command)).newInstance();
                exe.execute(getParamMap(req));
                pw.write("Yes, command has been executed. cost:" + (System.currentTimeMillis() - startTime) + "ms.");
            }
        } catch (Exception e) {
            log.error("Exception..", e);
            e.printStackTrace(pw);
        } finally {
            pw.flush();
            pw.close();
        }
    }

    /**
     * 把HttpServletRequest中的参数全部找出来，放入Map中传给相应的处理类。
     *
     * @param req {@link javax.servlet.http.HttpServletRequest}
     * @return {@link java.util.Map}
     */
    private Map<String, Object> getParamMap(HttpServletRequest req) {
        Enumeration enumeration = req.getParameterNames();
        if (null != enumeration) {
            Map<String, Object> param = new HashMap<String, Object>();
            while (enumeration.hasMoreElements()) {
                String obj = (String) enumeration.nextElement();
                param.put(obj, req.getParameter(obj));
            }
            return param;
        }
        return null;
    }
}
