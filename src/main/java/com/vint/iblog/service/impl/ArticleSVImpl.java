package com.vint.iblog.service.impl;

import com.vint.iblog.datastore.define.ArticleDAO;
import com.vint.iblog.service.interfaces.ArticleSV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vintsie.jcobweb.proxy.ServiceFactory;

/**
 *
 * Created by Vin on 14-2-13.
 */
public class ArticleSVImpl implements ArticleSV {

    private static final Log log = LogFactory.getLog(ArticleSVImpl.class);

    @Override
    public int readArticleViewCount(int articleId) throws Exception {
        //log.debug("-------------ArticleSV is called.");
        int count = ServiceFactory.getService(ArticleDAO.class).getArticleViewCount(12);
        return count;
    }

    @Override
    public String postNewArticle(String title, String writer) throws Exception {
        // 获取序列

        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        //ad.postNewArticle(title, writer);
        return "";
    }

    public CBNArticle getArticle(String hCode) throws Exception{
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        return ad.getArticle(hCode);
    }
}
