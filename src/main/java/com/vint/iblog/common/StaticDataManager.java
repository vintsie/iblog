package com.vint.iblog.common;

import com.vint.iblog.common.cache.StaticDataCacheLoader;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 静态数据管理器
 * Created by Vin on 14-2-17.
 */
public class StaticDataManager {

    //private transient static Log log = LogFactory.getLog(StaticDataManager.class);

    //private static Cache staticDataCache = null;

    /**
     * 获取静态数据
     *
     * @param dataType  静态数据类型
     * @return  ArrayList&lt;StaticData&gt;
     * @throws Exception
     */
    public static Set getStaticData(String dataType) throws Exception {
        //Object object = staticDataCache.get(dataType);
        //if(null == object) return null;
        //return (List<StaticData>) object;
        Map<String, Object> staticData =
                CacheManager.getData(StaticDataCacheLoader.class.getName());
        if(null == staticData) {
            return Collections.EMPTY_SET;
        }
        return (Set)staticData.get(dataType);
    }

/*    static {
        try {
            CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
            staticDataCache = cacheFactory.createCache(Collections.emptyMap());

            StaticDataDAO sdd = ServiceFactory.getService(StaticDataDAO.class);
            Map<String, List<StaticData>> cacheC = new HashMap<String, List<StaticData>>();
            List<StaticData> sds = sdd.getAllStaticData();
            if (!sds.isEmpty()) {
                for (StaticData sd : sds) {
                    List<StaticData> tmp = cacheC.get(sd.getDataType());
                    if (null == tmp) {
                        tmp = new ArrayList<StaticData>();
                        tmp.add(sd);
                        cacheC.put(sd.getDataType(), tmp);
                    } else {
                        tmp.add(sd);
                    }
                }
                log.info("静态数据缓存加载完成，共加载" + sds.size() + "条数据。");
            }
            staticDataCache.putAll(cacheC);
        } catch (Exception ce) {
            ce.printStackTrace();
            log.error("静态数据缓存加载失败", ce);
        }
    }*/
}
