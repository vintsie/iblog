package com.vint.iblog.web.servlet;

import com.vint.iblog.common.StaticDataManager;
import com.vint.iblog.service.interfaces.ArticleSV;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.vint.iblog.common.bean.config.StaticData;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Just for testing
 * <p/>
 * Created by Vin on 14-2-13.
 */
public class TestServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(TestServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<StaticData> list = StaticDataManager.getStaticData("ENTITY_TYPE");
            log.info(list);
            String title = req.getParameter("title");
            if (StringUtils.isEmpty(title))
                title = RandomStringUtils.random(20, true, false);
            //ServiceFactory.getService(ArticleSV.class).postNewArticle(title, "");
            //log.info(ServiceFactory.getService(ArticleSV.class).readArticleViewCount(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
