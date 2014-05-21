package com.vint.iblog.command.service.impl;

import com.vint.iblog.command.service.interfaces.ICommandExe;
import com.vint.iblog.datastore.define.CommonDAO;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.Map;

/**
 *
 * Created by Vin on 14-5-17.
 */
public class DevTempExe implements ICommandExe {
    @Override
    public void execute(Map params) throws Exception {

        CommonDAO cd = ServiceFactory.getService(CommonDAO.class);
        cd.saveGitHubCatalog("vintsie", "notebook", "life", "r");
        cd.saveGitHubCatalog("vintsie", "notebook", "programming", "r");
//        ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
//        List<CBNArticle> articles = articleSV.getArticles(2, 20);
//        System.out.println(Arrays.toString(articles.toArray()));
    }
}
