package com.vint.iblog.command.service.impl;

import com.vint.iblog.command.service.interfaces.ICommandExe;
import com.vint.iblog.common.CacheManager;

import java.util.Map;

/**
 * 缓存刷新命令处理程序
 *
 * Created by Vin on 14-5-20.
 */
public class CacheRefreshExe implements ICommandExe {

    @Override
    public void execute(Map params) throws Exception {
        // Just call reloadAll
        CacheManager.reloadAll();
    }
}
