package com.vint.iblog.web.servlet;

import com.vint.iblog.common.SequenceManager;
import com.vint.iblog.service.interfaces.ArticleSV;
import com.vint.iblog.service.interfaces.VersionControlSV;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vintsie.jcobweb.proxy.ServiceFactory;

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

        String owner = req.getParameter("owner");
        String repo = req.getParameter("repo");
        String path = req.getParameter("path");

        StringBuilder sb = new StringBuilder();

        if (StringUtils.isEmpty(owner) || StringUtils.isEmpty(repo) || StringUtils.isEmpty(path)) {
            sb.append("参数不可为空");
        } else {
            try {
                VersionControlSV versionControlSV = ServiceFactory.getService(VersionControlSV.class);
                CBNArticle article = versionControlSV.pullGitHubFile(owner, repo, path);
                if(null != article){
                    ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
                    article.sethCode(SequenceManager.getInstance().getNewSeq());
                    articleSV.saveArticle(article);
                }
                //log.error(article);
            } catch (Exception e) {
                log.error("发生了异常", e);
                sb.append("发生了异常").append(e.getMessage());
            }
        }
        PrintWriter pw = resp.getWriter();
        pw.write(sb.toString());

        pw.flush();
        pw.close();
    }
}
