package com.team.shop.service;

import com.team.shop.bean.ArticleComment;
import com.team.shop.bean.Commodity;
import com.team.shop.mapper.CommodityMapper;
import com.team.shop.pojo.ArticleAndCommentAndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommodityService {

    private CommodityMapper commodityMapper;

    @Autowired
    public void setCommodityMapper(CommodityMapper commodityMapper) {
        this.commodityMapper = commodityMapper;
    }

    /**
     *  添加 发布信息
     * @param commodity 发布信息
     */
    public void addCommdity(Commodity commodity){
        commodityMapper.addCommodity(commodity);
    }

    /**
     *  添加评论
     * @param articleComment 用户评论
     */
    public void addComment(ArticleComment articleComment){
        articleComment.setArticleCommentDate(new Date());
        commodityMapper.addComment(articleComment);
    }

    /**
     * 获取所有帖子 及 评论 及 所属用户信息
     * @return 所有帖子
     */
    public List<ArticleAndCommentAndUser> getAllArticleAndCommentAndUser(){
        return commodityMapper.getAllArticleAndCommentAndUser();
    }

    public Commodity getCommodityById(Integer id){
        return commodityMapper.getCommodityById(id);
    }
}
