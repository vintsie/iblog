package com.vint.iblog.command.service.impl;

import com.vint.iblog.command.service.interfaces.ICommandExe;
import com.vint.iblog.datastore.define.CommonDAO;
import com.vint.iblog.service.interfaces.ArticleSV;
import org.vint.iblog.common.bean.nor.CBNArticle;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Vin on 14-5-17.
 */
public class DevTempExe implements ICommandExe {
    @Override
    public void execute(Map params) throws Exception {
//        CommonDAO cd = ServiceFactory.getService(CommonDAO.class);
//        cd.saveGitHubCatalog("vintsie", "notebook", "_2014", "test");
        ArticleSV articleSV = ServiceFactory.getService(ArticleSV.class);
        List<CBNArticle> articles = articleSV.getArticles(2, 20);
        System.out.println(Arrays.toString(articles.toArray()));
    }
}
