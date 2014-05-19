package com.vint.iblog.service.interfaces;

import org.vint.iblog.common.bean.nor.CBNArticle;

import java.util.List;

/**
 * 文章管理服务类
 * <p/>
 * Created by Vin on 14-2-13.
 */
public interface ArticleSV {

    /**
     * 查询文章信息
     *
     * @param hCode 文章唯一标识
     * @return  CBN
     * @throws Exception
     */
    public CBNArticle getArticle(String hCode) throws Exception;

    /**
     * 分页查询文章
     * @param pageNum   页码
     * @param pageSize  每页数据大小
     * @return  文章对象集合
     * @throws Exception
     */
    public List<CBNArticle> getArticles(int pageNum, int pageSize) throws Exception;

    /**
     * 修改文章
     * @param article   文章对象
     * @throws Exception
     */
    public void modifyArticle(CBNArticle article) throws Exception;

    /**
     * 发布新的博文
     *
     * @param title  博文标题
     * @param writer 作者
     * @throws Exception
     */
    public String postNewArticle(String title, String writer) throws Exception;

    /**
     * 读取文章阅读次数
     *
     * @param articleId 文章编号
     * @return the Integer
     * @throws Exception
     */
    public int readArticleViewCount(int articleId) throws Exception;

    public String saveArticle(CBNArticle article) throws Exception;

    public void saveArticles(List<CBNArticle> articles) throws Exception;
}
