package com.team.shop.bean;

import com.team.shop.mapper.CommodityMapper;
import com.team.shop.pojo.ArticleAndCommentAndUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
// 在配置完websocket时,只有加上这个注解才能进行单元测试
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommodityTest {

    @Autowired
    CommodityMapper commodityMapper;

    @Test
    public void add(){
        Commodity commodity = new Commodity();
        commodity.setPublishContent("这是内容");
        commodity.setPublishDate(new Date());
        commodity.setFkUserId(32);
        commodity.setPublishKinds(3);
        commodity.setPublishTitle("这是标题");
        commodityMapper.addCommodity(commodity);
    }


    @Test
    public void addComment(){
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleCommentDate(new Date());
        articleComment.setArticleCommentContent("我是评论内容");
        articleComment.setFkUserId(32);
        articleComment.setFkArticleId(10);

        commodityMapper.addComment(articleComment);
        System.out.println(articleComment.getArticleCommentId());
    }


    @Test
    public void ArticleAndCommentAndUser(){

        List<ArticleAndCommentAndUser> allArticleAndCommentAndUser = commodityMapper.getAllArticleAndCommentAndUser();
        System.out.println(allArticleAndCommentAndUser.get(0).getArticleComments());
    }

    @Test
    public void getCommodityById(){
        Commodity c = commodityMapper.getCommodityById(20);
        System.out.println(c);
    }

}
