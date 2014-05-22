package com.vint.iblog.common.cache;

import com.vint.iblog.service.interfaces.ArticleSV;
import com.vint.iblog.service.interfaces.CommonSV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vint.iblog.common.bean.nor.CBNGitHubCatalog;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.io.Serializable;
import java.util.*;

/**
 *
 * Created by Vin on 14-5-18.
 */
public class ArticleCatalogCacheLoader implements CacheDataLoader {

    private transient static Log log = LogFactory.getLog(ArticleCatalogCacheLoader.class);

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> loadData() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
        List<CBNGitHubCatalog> catalogs = commonSV.getGitHubCatalogs();
        if (!catalogs.isEmpty()) {
            for (CBNGitHubCatalog catalog : catalogs) {
                data.put(catalog.getRepoInfo(), new TreeSet<ArticleSummary>());
                data.put(catalog.getRepoInfo()+"^base", catalog);
            }
            ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
            for (int i = 1; ; i++) {
                List<CBNArticle> articles = articleSV.getArticles(i, 20);
                if (null == articles || articles.isEmpty()) {
                    break;
                }
                for (CBNArticle article : articles) {
                    if (data.containsKey(article.getRepoInfo())) {
                        ArticleSummary as = new ArticleSummary();
                        as.setTitle(article.getTitle());
                        as.setSha(article.getSha());
                        as.setDate(article.getCreateDate());
                        as.setRepoInfo(article.getRepoInfo());
                        as.setHexCode(article.gethCode());
                        Set set = (Set) data.get(article.getRepoInfo());
                        log.info(as.getRepoInfo() + "|" + as.getTitle() + "|" +as.getDate().toString() + set.add(as));
                    }
                }
            }
        }
        return data;
    }

    @Override
    public Object load(Object o) {
        return null;
    }

    @Override
    public Map loadAll(Collection collection) {
        return null;
    }

    public static class ArticleSummary implements Serializable, Comparable<ArticleSummary>{

        private String title;
        private String sha;
        private Date date;
        private String repoInfo;
        private String hexCode;

        public String getHexCode() {
            return hexCode;
        }

        public void setHexCode(String hexCode) {
            this.hexCode = hexCode;
        }

        public String getRepoInfo() {
            return repoInfo;
        }

        public void setRepoInfo(String repoInfo) {
            this.repoInfo = repoInfo;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        @Override
        public int compareTo(ArticleSummary o) {
            return o.getDate().compareTo(this.getDate());
        }
    }
}
