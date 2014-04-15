package com.vint.iblog.common;

import com.vint.iblog.common.cache.CacheDataLoader;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Vin on 14-4-11.
 */
public class CacheManager {

    private transient static Log log = LogFactory.getLog(CacheManager.class);

    private CacheManager(){}

    static String appPreFix = "";

    static Cache cache = null;
    static List<CacheElement> _caches = new ArrayList<CacheElement>();

    public static List<CacheElement> getCacheElement(){
        return _caches;
    }

    /**
     * get cached data
     * @param cln   cache loader name
     * @return  cached data
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> getData(String cln){
        return (Map<String, Object>)cache.get(appPreFix + cln);
    }

    /**
     * Load cache data
     * @throws Exception
     */
    public static void load() throws Exception{
        if(_caches.isEmpty()){
            //throw new LRuntimeException("Cache load failed");
            log.info("No cache configuration found, skip cache-loading.");
            return;
        }
        for(CacheElement ce : _caches){
            CacheDataLoader cdl = (CacheDataLoader)Class.forName(ce.getCacheClazz()).newInstance();
            Map<String, Object> data = cdl.loadData();
            if(null != data && data.size() > 0){
                cache.put(appPreFix + ce.getCacheClazz(), data);
                log.info("Cache[" + ce.getCacheClazz() + "] loaded, " + data.size() + " counts.");
            }
        }

    }

    static{
        try{
            CacheFactory cacheFactory = net.sf.jsr107cache.CacheManager.getInstance().getCacheFactory();
            cache = cacheFactory.createCache(Collections.emptyMap());
            log.info("=============JCache initialized successfully.=============");

            SAXBuilder saxBuilder = new SAXBuilder();
            Document doc =
                    saxBuilder.build(CacheManager.class.getResourceAsStream("/system/cache/cache.xml"));
            XPathFactory xPathFactory = XPathFactory.instance();
            XPathExpression<Element> xp = xPathFactory.compile("/cache_list/cache", Filters.element());
            List<Element> caches = xp.evaluate(doc);
            if(null != caches && caches.size() > 0){
                CacheElement ce;
                for(Element _cache : caches){
                    ce = new CacheElement();
                    ce.setClazz(_cache.getAttribute("loader").getValue());
                    _caches.add(ce);
                }
            }
            log.info("=============Cache configuration loaded successfully.=============");

        } catch (CacheException ce){
            log.error("!!!!!!!!!!JCache initialized failed!!!!!!!!!", ce);
        } catch (IOException ioe){
            log.error("!!!!!!!!!!Read cache configuration file failed.", ioe);
        } catch (JDOMException je){
            log.error("!!!!!!!!!!Build sax object failed.", je);
        }
    }

    static class CacheElement{
        private String clazz;
        public String getCacheClazz(){
            return clazz;
        }
        public void setClazz(String clazz1){
            this.clazz = clazz1;
        }
    }
}
