package com.team.shop.controller;

import com.team.shop.annotation.PassToken;
import com.team.shop.bean.ArticleComment;
import com.team.shop.bean.Commodity;
import com.team.shop.pojo.ArticleAndCommentAndUser;
import com.team.shop.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommodityController {

    private CommodityService commodityService;

    @Autowired
    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    /**
     *  添加发布信息
     * @param commodity
     * @return
     */
    @PostMapping("/addCommodity")
    public Map<String,Object> addCommodity(@RequestBody Commodity commodity){

         Map<String,Object> res = new HashMap<>();
         commodity.setPublishDate(new Date());
         commodityService.addCommdity(commodity);
         if(commodity.getPublishId() == null){
             res.put("code",-1);
             res.put("message","发布失败");
             return res;
         }else{
             res.put("code",1);
             res.put("message","发布成功");
             return res;
         }
    }

    @PostMapping("/addComment")
    public Map<String,Object> addComment(@RequestBody ArticleComment articleComment){
        Map<String,Object> res = new HashMap<>();
        commodityService.addComment(articleComment);
        if(articleComment.getArticleCommentId() == null){
            res.put("code",-1);
            res.put("message","评论失败");
            return res;
        }else{
            res.put("code",1);
            res.put("message","发布成功");
            return res;
        }
    }

    @GetMapping("/getAllCommodity")
    public Map<String,Object> getAllCommodityAndCommentAndUser(){
        Map<String,Object> res = new HashMap<>();
        List<ArticleAndCommentAndUser> allArticleAndCommentAndUser = commodityService.getAllArticleAndCommentAndUser();
        res.put("list",allArticleAndCommentAndUser);
        return res;
    }

    @GetMapping("/getCommodityById/{id}")

    public Commodity getCommodityById(@PathVariable("id") Integer id){
        return commodityService.getCommodityById(id);
    }
}
