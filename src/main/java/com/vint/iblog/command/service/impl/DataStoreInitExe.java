package com.vint.iblog.command.service.impl;

import com.vint.iblog.command.service.interfaces.ICommandExe;
import com.vint.iblog.datastore.define.CommonDAO;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.Map;

/**
 * 初始化Google 数据存储
 * Created by Vin on 14-5-22.
 */
public class DataStoreInitExe implements ICommandExe {

    @Override
    public void execute(Map params) throws Exception {
        CommonDAO commonDAO = ServiceFactory.getService(CommonDAO.class);
        commonDAO.initGoogleDs();
    }
}
