import com.vint.iblog.plugins.github.GitHubApiTool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 *
 * Created by Vin on 14-2-13.
 */
public class TestMain {

    private transient static Log log = LogFactory.getLog(TestMain.class);

//    public static void main(String[] args) throws Exception{
//        log.info("12312");
//        log.info(CacheManager.getCacheElement());
//        CacheManager.load();
//        //Object obj = CacheManager.getCache(TestCacheDataLoader.class.getName());
//        //log.info(obj);
//        //log.info(CacheManager.getCache());
//    }

    @Test
    public void testInsertCatalog() throws Exception{
        String s = "Hello World.";
        log.error(s.substring(0, s.lastIndexOf("W")));
        System.out.println(GitHubApiTool.pull(new String[]{"vintsie", "notebook", "_2014/_2014_01_06_wget_java_using_sh.md"}));
    }

}
