package com.vint.iblog.service.impl;

import com.vint.iblog.common.exception.LException;
import com.vint.iblog.plugins.github.GitHubApiTool;
import com.vint.iblog.service.interfaces.VersionControlSV;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.vint.iblog.common.bean.nor.CBNArticle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * Created by Vin on 14-5-17.
 */
public class VersionControlSVImpl implements VersionControlSV {

    @Override
    public CBNArticle pullGitHubFile(String owner, String repo, String filePath) throws Exception {
        JSONObject object = GitHubApiTool.pull(new String[]{owner, repo, filePath});
        if(GitHubApiTool.isArray(object)){
            throw new LException("GitHub返回的数据是数组，请核对GitHub参数。");
        }
        CBNArticle article = new CBNArticle();
        article.setTitle(object.getString(GitHubApiTool.NAME));
        article.setMarkdownContent(object.getString(GitHubApiTool.CONTENT));
        article.setContent(
                new PegDownProcessor(Extensions.FENCED_CODE_BLOCKS).markdownToHtml(article.getMarkdownContent()));
        article.setSha(object.getString(GitHubApiTool.SHA));
        article.setRepoInfo(owner+ "^" + repo + "^" + filePath.substring(0, filePath.lastIndexOf("/" + article.getTitle())));
        return article;
    }

    @Override
    public List<Map<String, String>> pullGitHubFiles(String owner, String repo, String path) throws Exception {
        if(StringUtils.isEmpty(path) || path.equals("/")){
            throw new LException("暂不支持根目录扫描。");
        }
        JSONObject object = GitHubApiTool.pull(new String[]{owner, repo, path});
        if(GitHubApiTool.isNotArray(object)){
            throw new LException("远程返回的数据不是JSON数组。");
        }
        JSONArray objArray = object.getJSONArray(GitHubApiTool.ARRAY_CONTENT);
        List<Map<String, String>> filesInfo = new ArrayList<Map<String, String>>();
        if(null != objArray && objArray.length() > 0){
            for(int i=0; i<objArray.length();i++){
                JSONObject objInfo = objArray.getJSONObject(i);
                Map<String, String> temp = new HashMap<String, String>();
                temp.put(GitHubApiTool.NAME, objInfo.getString(GitHubApiTool.NAME));
                temp.put(GitHubApiTool.SHA, objInfo.getString(GitHubApiTool.SHA));
                filesInfo.add(temp);
            }
        }
        return filesInfo;
    }
}
