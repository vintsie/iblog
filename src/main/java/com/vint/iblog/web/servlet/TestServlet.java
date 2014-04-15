package com.vint.iblog.web.servlet;

import com.vint.iblog.common.SequenceManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
//            List<StaticData> list = StaticDataManager.getStaticData("ENTITY_TYPE");
//            log.info(list);
//            String title = req.getParameter("title");
//            if (StringUtils.isEmpty(title))
//                title = RandomStringUtils.random(20, true, false);
            //ServiceFactory.getService(ArticleSV.class).postNewArticle(title, "");
            //log.info(ServiceFactory.getService(ArticleSV.class).readArticleViewCount(1));
            for(int i=0; i<1000000;i++){
                log.info(i + "++" + SequenceManager.getInstance().getNewSeq());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
