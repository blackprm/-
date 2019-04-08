package com.team.shop.mapper;

import com.team.shop.bean.ArticleComment;
import com.team.shop.bean.Commodity;
import com.team.shop.pojo.ArticleAndCommentAndUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper {

    /**
     *  添加发布类内容实体
     * @param commodity 发布类
     */
    public void addCommodity(Commodity commodity);


    /**
     * 添加评论
     * @param comment 评论
     */
    public void addComment(ArticleComment comment);

    /**
     * 获取所有的商品和商品的用户和评论信息
     * @return
     */
    public List<ArticleAndCommentAndUser> getAllArticleAndCommentAndUser();

    /**
     *  根据id获取指定商品详细信息
     * @param id 商品id
     * @return
     */
    public ArticleAndCommentAndUser getCommodityById(Integer id);

    /**
     * 添加帖子照片路径
     */
    public void addArticlePhotoPath(@Param("fkArticle") Integer fkArticle,@Param("photoPath") String photoPath);

    /**
     *  根据用户id 获取其发布的商品
     * @param userId
     */
    public List<ArticleAndCommentAndUser> getCommoditysByUseId(@Param("userId") Integer userId);

    /**
     * 根据帖子id删除帖子
     * @param commodityId 帖子id
     */
    public void deleteCommodityByCommodityId(@Param("id") Integer commodityId);
}
