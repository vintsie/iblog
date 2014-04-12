package com.vint.iblog.common.cache;

import com.vint.iblog.datastore.define.StaticDataDAO;
import org.vint.iblog.common.bean.config.StaticData;
import org.vintsie.jcobweb.proxy.ServiceFactory;

import java.util.*;

/**
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
        Map<String, List<StaticData>> data = new HashMap<String, List<StaticData>>();
        List<StaticData> list;
        for(StaticData sd : staticDatas){
            if(null == (list = data.get(sd.getDataType()))){
                list = new ArrayList<StaticData>();
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
