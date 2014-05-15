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

    public static String formatDefaultRefUrl(String owner, String repo, String path) {
        return GH_API_URL
                .replace(":owner", owner)
                .replace(":repo", repo)
                .replace(":filePath", path)
                .replace(":ref", "master");
    }

    public static String formatUrl(String owner, String repo, String path, String ref) {
        return GH_API_URL
                .replace(":owner", owner)
                .replace(":repo", repo)
                .replace(":filePath", path)
                .replace(":ref", ref);
    }

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
            HttpRequestFactory hrf = new NetHttpTransport().createRequestFactory();
            HttpRequest request = hrf.buildGetRequest(new GenericUrl(new URI(url)));
            HttpResponse response = request.execute();
            InputStream is = response.getContent();

            int count = 0;
            while (count == 0) {
                count = is.available();
            }
            byte[] buffer = new byte[128];

            int len;
            while ((len = is.read(buffer)) != -1) {
                content.append(new String(buffer, 0, len));
            }
            response.disconnect();
        } catch (Exception e) {
            log.error("Pull file failed.", e);
            return content.toString();
        }
        return content.toString();
    }

    public static JSONObject pull(String[] params) throws Exception {
        JSONObject rtn;
        String natureStr = request(formatUrl(params));
        if(natureStr.trim().startsWith("[")){
            rtn = new JSONObject();
            rtn.put(IS_ARRAY, IS_ARRAY_YES);
            rtn.put(ARRAY_CONTENT, new JSONArray(natureStr));
        }else{
            rtn = new JSONObject(natureStr);
            rtn.put(IS_ARRAY, IS_ARRAY_NO);
            String content = rtn.getString("content");
            rtn.put("content",
                    new String(Base64.decodeBase64(content.replaceAll(Pattern.quote("\\n"), StringUtils.EMPTY))));
        }
        return rtn;
    }
}
