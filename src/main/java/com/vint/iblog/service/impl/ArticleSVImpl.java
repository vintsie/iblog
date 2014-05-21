package com.vint.iblog.service.impl;

import com.vint.iblog.common.CacheManager;
import com.vint.iblog.common.SequenceManager;
import com.vint.iblog.common.cache.ArticleCatalogCacheLoader;
import com.vint.iblog.common.exception.LException;
import com.vint.iblog.datastore.define.ArticleDAO;
import com.vint.iblog.service.interfaces.ArticleSV;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 *
 * Created by Vin on 14-2-13.
 */
public class ArticleSVImpl implements ArticleSV {

    private static final Log log = LogFactory.getLog(ArticleSVImpl.class);


    @Override
    public void deleteArticle(String hexCode) throws Exception {
        ArticleDAO ad = ServiceFactory.getService(ArticleDAO.class);
        ad.deleteArticle(hexCode);
    }

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
    public List<ArticleCatalogCacheLoader.ArticleSummary> getCachedArticleList(String catalog) throws Exception {
        List<String> foundedRepoInfo = new ArrayList<String>();
        Map<String, Object> cachedData = CacheManager.getData(ArticleCatalogCacheLoader.class.getName());
        if (null == cachedData || cachedData.size() < 1) {
            throw new LException("System Error.");
        }
        for (String repoInfoKey : cachedData.keySet()) {
            String[] splits = repoInfoKey.split(Pattern.quote("^"));
            if (splits.length == 3 && splits[2].equals(catalog)) {
                foundedRepoInfo.add(repoInfoKey);
            }
        }
        if (foundedRepoInfo.size() < 1) {
            throw new LException("Catalog[" + catalog + "] can't be found.");
        }

        List<ArticleCatalogCacheLoader.ArticleSummary> ass = new ArrayList<ArticleCatalogCacheLoader.ArticleSummary>();
        for (String repoInfo : foundedRepoInfo) {
            List tmp = (List) cachedData.get(repoInfo);
            if (null != tmp && tmp.size() > 0) {
                for (Object obj : tmp) {
                    ass.add((ArticleCatalogCacheLoader.ArticleSummary) obj);
                }
            }
        }
        if(!ass.isEmpty()){
            Collections.sort(ass);
        }
        return ass;
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
