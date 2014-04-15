package com.vint.iblog.common.cache;

import com.vint.iblog.datastore.define.StaticDataDAO;
import org.vint.iblog.common.bean.config.StaticData;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.*;

/**
 *
 * 因为StaticData已经实现了Comparable接口，所以这个缓存使用TreeSet，实现自动排序
 *
 * Created by Vin on 14-4-12.
 */
public class StaticDataCacheLoader implements CacheDataLoader {

    @Override
    public Map<String, Object> loadData() throws Exception {

        Map<String, Object> cache = new HashMap<String, Object>();

        StaticDataDAO dao = ServiceFactory.getService(StaticDataDAO.class);
        List<StaticData> staticDatas = dao.getAllStaticData();

        if(null == staticDatas || staticDatas.isEmpty()){
            return null;
        }
        Map<String, Set<StaticData>> data = new HashMap<String, Set<StaticData>>();
        Set<StaticData> list;
        for(StaticData sd : staticDatas){
            if(null == (list = data.get(sd.getDataType()))){
                list = new TreeSet<StaticData>();
                data.put(sd.getDataType(), list);
            }
            list.add(sd);
        }
        cache.putAll(data);
        return cache;
    }

    @Override
    public Object load(Object key) {
        return null;
    }

    @Override
    public Map loadAll(Collection keys) {
        return null;
    }
}
