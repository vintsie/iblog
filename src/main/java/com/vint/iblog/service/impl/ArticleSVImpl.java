package com.vint.iblog.service.impl;

import com.vint.iblog.common.SequenceManager;
import com.vint.iblog.datastore.define.ArticleDAO;
import com.vint.iblog.service.interfaces.ArticleSV;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.List;

/**
 *
 * Created by Vin on 14-2-13.
 */
public class ArticleSVImpl implements ArticleSV {

    private static final Log log = LogFactory.getLog(ArticleSVImpl.class);


    public CBNArticle getArticle(String hCode) throws Exception{
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        return ad.getArticle(hCode);
    }

    @Override
    public List<CBNArticle> getArticles(int pageNum, int pageSize) throws Exception {
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        return ad.getArticles(pageNum, pageSize);
    }

    @Override
    public void modifyArticle(CBNArticle article) throws Exception {
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        ad.modifyArticle(article);
    }

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

    @Override
    public String saveArticle(CBNArticle article) throws Exception {
        if(StringUtils.isEmpty(article.gethCode())){
            article.sethCode(SequenceManager.getInstance().getNewSeq());
        }
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        return ad.postNewArticle(article);
    }

    @Override
    public void saveArticles(List<CBNArticle> articles) throws Exception {
        if(null == articles || articles.isEmpty()){
            return;
        }
        for(CBNArticle article : articles){
            if(StringUtils.isEmpty(article.gethCode())){
                article.sethCode(SequenceManager.getInstance().getNewSeq());
            }
        }
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        ad.saveArticles(articles);
    }
}
