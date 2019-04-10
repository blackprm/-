package com.team.shop.controller;

import com.team.shop.annotation.PassToken;
import com.team.shop.bean.ArticleComment;
import com.team.shop.bean.Commodity;
import com.team.shop.bean.User;
import com.team.shop.pojo.ArticleAndCommentAndUser;
import com.team.shop.service.CommodityService;
import com.team.shop.service.UserService;
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
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
             Integer publishId = commodity.getPublishId();
             String[] photoPaths = commodity.getPics();
             if(photoPaths != null){
                 for(String photoPath:photoPaths){
                     commodityService.addArticlePath(publishId,photoPath);
                 }
             }


             return res;
         }
    }


    /**
     * 添加评论
     * @param articleComment
     * @return
     */
    @PostMapping("/addComment")
    public Map<String,Object> addComment(@RequestBody ArticleComment articleComment){
        Map<String,Object> res = new HashMap<>();
        User user = userService.getUser(articleComment.getFkUserId());


        articleComment.setCommentUserNike(user.getUserNike());
        articleComment.setCommentUserPhonePath(user.getUserPhotoPath());

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

    /**
     *  获取所有商品细节
     * @return
     */
    @GetMapping("/getAllCommodity")
    public Map<String,Object> getAllCommodityAndCommentAndUser(){
        Map<String,Object> res = new HashMap<>();
        List<ArticleAndCommentAndUser> allArticleAndCommentAndUser = commodityService.getAllArticleAndCommentAndUser();
        res.put("list",allArticleAndCommentAndUser);
        return res;
    }

    /**
     *  根据Id获取商品细节
     * @param id
     * @return
     */
    @GetMapping("/getCommodityById/{id}")
    public Commodity getCommodityById(@PathVariable("id") Integer id){
        return commodityService.getCommodityById(id);
    }

    @GetMapping("/getCommoditysByUserId/{id}")
    public Object getCommoditysByUserId(@PathVariable("id") Integer id){
        return commodityService.getCommoditysByUseId(id);
    }


    @GetMapping("/deleteCommodityByCommodityId/{id}")
    public void deleteCommodityByCommodityId(@PathVariable("id") Integer id){
        commodityService.deleteCommodityByCommodityId(id);
    }

    @PostMapping("/updateCommodityDetailById")
    @PassToken
    public Map<String,Object> updateCommodityDetail(@RequestBody Commodity c){
        Map<String,Object> res = new HashMap<>();
        Integer affRow = commodityService.updateCommodityDetail(c);
        if(affRow == 1){
            // 修改成功
            res.put("code",1);
            Commodity newCom = commodityService.getCommodityById(c.getPublishId());
            res.put("newCommodity",newCom);
            return res;
        }else{
            res.put("code",-1);
            return res;
        }
    }
}
