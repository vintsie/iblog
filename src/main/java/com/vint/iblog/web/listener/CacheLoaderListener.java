package com.vint.iblog.web.listener;

import com.vint.iblog.common.CacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * Created by Vin on 14-4-12.
 */
public class CacheLoaderListener implements ServletContextListener {

    private transient static Log log = LogFactory.getLog(CacheLoaderListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try{
            CacheManager.load();
        }catch (Exception e){
            log.error("Cache load failed.", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
