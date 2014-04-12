package com.vint.iblog.common.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by Vin on 14-4-12.
 */
public class TestCacheDataLoader implements CacheDataLoader {

    @Override
    public Map<String, Object> loadData() throws Exception {
        //CacheFactory cacheFactory = net.sf.jsr107cache.CacheManager.getInstance().getCacheFactory();
        //Cache cache = cacheFactory.createCache(Collections.emptyMap());

        //Map<String, Object> data = new HashMap<String, Object>();
        //data.put("blog_name", "iBlog");
        //cache.put("key", "value");
        //cache.put("tmpww", "11");
        //cache.putAll(data);
        return new HashMap<String, Object>();
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
