import com.vint.iblog.common.CacheManager;
import com.vint.iblog.common.cache.TestCacheDataLoader;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * Created by Vin on 14-2-13.
 */
public class TestMain {

    private transient static Log log = LogFactory.getLog(TestMain.class);

    public static void main(String[] args) throws Exception{
        log.info("12312");
        log.info(CacheManager.getCacheElement());
        CacheManager.load();
        //Object obj = CacheManager.getCache(TestCacheDataLoader.class.getName());
        //log.info(obj);
        //log.info(CacheManager.getCache());
    }



}
