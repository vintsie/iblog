package com.vint.iblog.plugins.github;

import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.Base64;
import com.vint.iblog.common.exception.LException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.regex.Pattern;

/**
 * GitHub
 * <p/>
 * Created by Vin on 14-4-27.
 */
public class GitHubApiTool {

    static String GH_API_URL = "https://api.github.com/repos/:owner/:repo/contents/:filePath?ref=:ref";

    static transient Log log = LogFactory.getLog(GitHubApiTool.class);

    public static String IS_ARRAY = "IsArray";
    public static String IS_ARRAY_YES = "Y";
    public static String IS_ARRAY_NO = "N";
    public static String ARRAY_CONTENT = "_AC";

    public static String NAME = "name";
    public static String CONTENT = "content";
    public static String SHA = "sha";

    public static String CHARSET = "UTF-8";

    private static String formatDefaultRefUrl(String owner, String repo, String path) {
        return GH_API_URL
                .replace(":owner", owner)
                .replace(":repo", repo)
                .replace(":filePath", path)
                .replace(":ref", "master");
    }

    private static String formatUrl(String owner, String repo, String path, String ref) {
        return GH_API_URL
                .replace(":owner", owner)
                .replace(":repo", repo)
                .replace(":filePath", path)
                .replace(":ref", ref);
    }

    /**
     * 格式化GitHub API的URL。参数params是一个String型的数组。第一个参数代表
     * 代码仓库的拥有者，第二个参数代表代码仓库的名称，第三个参数表示代码仓库中文件
     * 或者目录的路径。
     *
     * @param params    参数数据，数组长度只能是3或者4
     * @return  拼接参数后的URL
     * @throws Exception
     */
    public static String formatUrl(String[] params) throws Exception {
        if (null == params || params.length < 3 || params.length > 4) {
            throw new LException("param is empty or param-length is less than 3 or param-length is greater than 4.");
        }
        if (params.length == 3) {
            return formatDefaultRefUrl(params[0], params[1], params[2]);
        } else {
            return formatUrl(params[0], params[1], params[2], params[3]);
        }
    }

    public static String request(String url) {
        StringBuilder content = new StringBuilder();
        try {
            if(log.isInfoEnabled()){
                log.info("Start to request remote server, Url:" + url);
            }
            HttpRequestFactory hrf = new NetHttpTransport().createRequestFactory();
            HttpRequest request = hrf.buildGetRequest(new GenericUrl(new URI(url)));

            HttpResponse response = request.execute();
            if(log.isInfoEnabled()){
                log.info("Server response code:" + response.getStatusCode());
            }
            InputStream is = response.getContent();
            InputStreamReader isr = new InputStreamReader(is, response.getContentCharset());

            int count = 0;
            while (count == 0) {
                count = is.available();
            }

            if(isr.ready()){
                char[] buffer = new char[128];
                int len;
                while((len = isr.read(buffer))!= -1){
                    content.append(new String(buffer, 0, len));
                }
            }
            response.disconnect();
        } catch (Exception e) {
            log.error("Pull file failed.", e);
            return StringUtils.EMPTY;
        }
        return content.toString();
    }

    public static JSONObject pull(String[] params) throws Exception {
        JSONObject rtn;
        String natureStr = request(formatUrl(params));
        if(StringUtils.isEmpty(natureStr)){
            throw new LException("读取远程文件返回数据为空。");
        }
        if(natureStr.trim().startsWith("[")){
            rtn = new JSONObject();
            rtn.put(IS_ARRAY, IS_ARRAY_YES);
            rtn.put(ARRAY_CONTENT, new JSONArray(natureStr));
        }else{
            rtn = new JSONObject(natureStr);
            rtn.put(IS_ARRAY, IS_ARRAY_NO);
            String content = rtn.getString("content");
            rtn.put("content",
                    new String(
                            Base64.decodeBase64(content.replaceAll(Pattern.quote("\\n"), StringUtils.EMPTY)),
                            CHARSET)
            );
        }
        return rtn;
    }

    /**
     * 判断JSON对象是不是内容数据。根据JSON对象中的isArray标识符来判断。
     *
     * @param object json对象
     * @return true/false
     * @throws Exception
     */
    public static boolean isArray(JSONObject object) throws Exception {
        if (!object.has(IS_ARRAY)) {
            throw new LException("JSON对象里不存在标志符[" + IS_ARRAY + "].");
        }
        return StringUtils.equals(object.getString(IS_ARRAY), IS_ARRAY_YES)
                ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 判断JSON对象是不是内容数据。根据JSON对象中的isArray标识符来判断。
     *
     * @param object json对象
     * @return true/false
     * @throws Exception
     */
    public static boolean isNotArray(JSONObject object) throws Exception {
        return !isArray(object);
    }
}
