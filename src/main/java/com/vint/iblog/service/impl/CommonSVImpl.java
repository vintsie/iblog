package com.vint.iblog.service.impl;

import com.vint.iblog.datastore.define.CommonDAO;
import com.vint.iblog.datastore.define.SequenceManagerDAO;
import com.vint.iblog.service.interfaces.CommonSV;
import org.vint.iblog.common.bean.nor.CBNGitHubCatalog;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.List;

/**
 * 公共服务实现类
 *
 * Created by Vin on 14-4-14.
 */
public class CommonSVImpl implements CommonSV {

    @Override
    public String getSeqByType(String type) throws Exception {

        SequenceManagerDAO smd =
                ServiceFactory.getService(SequenceManagerDAO.class);
        return smd.getCurrentSeq(type);
    }

    @Override
    public void modifySeqByType(String type, String seq) throws Exception {
        SequenceManagerDAO smd =
                ServiceFactory.getService(SequenceManagerDAO.class);
        smd.createSequence(type, seq);
    }

    @Override
    public List<CBNGitHubCatalog> getGitHubCatalogs() throws Exception {
        CommonDAO cd = ServiceFactory.getService(CommonDAO.class);
        return cd.getGitHubCatalogs();
    }
}
