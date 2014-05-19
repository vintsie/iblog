package com.vint.iblog.service.interfaces;

import org.json.JSONObject;
import org.vint.iblog.common.bean.nor.CBNArticle;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Vin on 14-5-17.
 */
public interface VersionControlSV {

    /**
     * 拉取GitHub上Master分支最新的文件内容。
     *
     * @param owner     GitHub中Repo的拥有者
     * @param repo      Repo的名称
     * @param filePath  文件路径
     * @return  返回Common域中定义的文章JavaBean
     * @throws Exception
     */
    public CBNArticle pullGitHubFile(String owner, String repo, String filePath) throws Exception;

    /**
     * 获取目录下所有数据清单
     * @param owner Repo拥有者
     * @param repo  Repo名称
     * @param path  Path
     * @return  file name list, key:[name, sha]
     * @throws Exception
     */
    public List<Map<String, String>> pullGitHubFiles(String owner, String repo, String path) throws Exception;
}
