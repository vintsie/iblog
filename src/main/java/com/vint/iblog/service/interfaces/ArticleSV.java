package com.vint.iblog.service.interfaces;

import org.vint.iblog.common.bean.nor.CBNArticle;

/**
 * 文章管理服务类
 * <p/>
 * Created by Vin on 14-2-13.
 */
public interface ArticleSV {

    /**
     * 读取文章阅读次数
     *
     * @param articleId 文章编号
     * @return the Integer
     * @throws Exception
     */
    public int readArticleViewCount(int articleId) throws Exception;

    /**
     * 发布新的博文
     *
     * @param title  博文标题
     * @param writer 作者
     * @throws Exception
     */
    public String postNewArticle(String title, String writer) throws Exception;

    /**
     * 查询文章信息
     *
     * @param hCode 文章唯一标识
     * @return  CBN
     * @throws Exception
     */
    public CBNArticle getArticle(String hCode) throws Exception;
}
