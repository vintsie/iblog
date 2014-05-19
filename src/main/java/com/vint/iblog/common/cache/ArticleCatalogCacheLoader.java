package com.vint.iblog.common.cache;

import com.vint.iblog.service.interfaces.ArticleSV;
import com.vint.iblog.service.interfaces.CommonSV;
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
    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> loadData() throws Exception {
        Map<String, Object> data = new HashMap<String, Object>();
        CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
        List<CBNGitHubCatalog> catalogs = commonSV.getGitHubCatalogs();
        if (!catalogs.isEmpty()) {
            for (CBNGitHubCatalog catalog : catalogs) {
                data.put(catalog.getRepoInfo(), new ArrayList<ArticleSummary>());
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
                        List list = (List) data.get(article.getRepoInfo());
                        list.add(as);
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

    public static class ArticleSummary implements Serializable{

        private String title;
        private String sha;

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
    }
}
