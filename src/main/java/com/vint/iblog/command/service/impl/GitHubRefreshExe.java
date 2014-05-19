package com.vint.iblog.command.service.impl;

import com.vint.iblog.command.service.interfaces.ICommandExe;
import com.vint.iblog.common.CacheManager;
import com.vint.iblog.common.cache.ArticleCatalogCacheLoader;
import com.vint.iblog.plugins.github.GitHubApiTool;
import com.vint.iblog.service.interfaces.ArticleSV;
import com.vint.iblog.service.interfaces.CommonSV;
import com.vint.iblog.service.interfaces.VersionControlSV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vint.iblog.common.bean.nor.CBNGitHubCatalog;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 刷新GitHub内容
 * Created by Vin on 14-5-17.
 */
public class GitHubRefreshExe implements ICommandExe {
    private transient static Log log = LogFactory.getLog(GitHubRefreshExe.class);

    private List<TempObject> adds = new ArrayList<TempObject>();
    private List<TempObject> modifies = new ArrayList<TempObject>();
    private List<TempObject> deletes = new ArrayList<TempObject>();

    @Override
    public void execute(Map params) throws Exception {
        long startTimes = System.currentTimeMillis();
        //String cmd = MapUtils.getString(params, "cmd");

        // 获取GitHub目录列表
        CommonSV commonSV = ServiceFactory.getService(CommonSV.class);
        VersionControlSV vcSV = ServiceFactory.getService(VersionControlSV.class);
        List<CBNGitHubCatalog> catalogs = commonSV.getGitHubCatalogs();

        /*缓存的文章信息*/
        Map<String, Object> articleCatalog =  CacheManager.getData(ArticleCatalogCacheLoader.class.getName());

        // 循环目录获取目录下的文章列表
        for(CBNGitHubCatalog catalog : catalogs){
            // 获取远程目录下的数据
            List<Map<String, String>> catalogFiles =
                    vcSV.pullGitHubFiles(catalog.getOwner(), catalog.getRepo(), catalog.getPath());

            // 解析远程目下的文件名
            List<String> remoteFileNames = parseFileNames(catalogFiles);
            Map<String, String> remoteFileSha = parseFileSha(catalogFiles);

            List cached = (List)articleCatalog.get(catalog.getRepoInfo());
            for(Object cache : cached){
                ArticleCatalogCacheLoader.ArticleSummary as = (ArticleCatalogCacheLoader.ArticleSummary) cache;
                if(!remoteFileNames.contains(as.getTitle())){
                    deletes.add(new TempObject(as.getTitle(), catalog.getPath(), catalog.getOwner(), catalog.getRepo()));
                }
                if(!as.getSha().equals(remoteFileSha.get(as.getTitle()))){
                    modifies.add(new TempObject(as.getTitle(), catalog.getPath(), catalog.getOwner(), catalog.getRepo()));
                }
            }
            for(String fileName : remoteFileNames){
                boolean existed = Boolean.FALSE;
                for(Object cache : cached){
                    ArticleCatalogCacheLoader.ArticleSummary as = (ArticleCatalogCacheLoader.ArticleSummary) cache;
                    if(as.getTitle().equals(fileName)){
                        existed = Boolean.TRUE;
                        break;
                    }
                }
                if(!existed){
                    adds.add(new TempObject(fileName, catalog.getPath(), catalog.getOwner(), catalog.getRepo()));
                }
            }
        }

        ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
        List<CBNArticle> addArticles = new ArrayList<CBNArticle>();
        for(TempObject to : adds){
            addArticles.add(vcSV.pullGitHubFile(to.getOwner(), to.getRepo(), to.getPath() + "/" + to.getName()));
        }
        articleSV.saveArticles(addArticles);

        for(TempObject to : modifies){
            articleSV.modifyArticle(vcSV.pullGitHubFile(to.getOwner(), to.getRepo(), to.getPath() + "/" + to.getName()));
        }

        if(log.isInfoEnabled()){
            log.info("GitHub数据刷新完成，耗时" + (System.currentTimeMillis() - startTimes) + "ms.");
        }
        CacheManager.reloadAll();
    }

    private List<String> parseFileNames(List<Map<String, String>> filesInfo){
        List<String> fileNames = new ArrayList<String>();
        for(Map<String, String> fileInfo : filesInfo){
            fileNames.add(fileInfo.get(GitHubApiTool.NAME));
        }
        return fileNames;
    }

    private Map<String, String> parseFileSha(List<Map<String, String>> filesInfo){
        Map<String, String> fileSha = new HashMap<String, String>();
        for(Map<String, String> fileInfo : filesInfo){
            fileSha.put(fileInfo.get(GitHubApiTool.NAME), fileInfo.get(GitHubApiTool.SHA));
        }
        return fileSha;
    }

    public static class TempObject{
        private String name;
        private String path;
        private String owner;
        private String repo;

        TempObject(String name, String path, String owner, String repo){
            setName(name);
            setPath(path);
            setOwner(owner);
            setRepo(repo);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getRepo() {
            return repo;
        }

        public void setRepo(String repo) {
            this.repo = repo;
        }
    }
}
