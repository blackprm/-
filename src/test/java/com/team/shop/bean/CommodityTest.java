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
    public void add() {
        Commodity commodity = new Commodity();
        commodity.setPublishContent("这是内容");
        commodity.setPublishDate(new Date());
        commodity.setFkUserId(32);
        commodity.setPublishKinds(3);
        commodity.setPublishTitle("这是标题");
        commodityMapper.addCommodity(commodity);
    }


    @Test
    public void addComment() {
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticleCommentDate(new Date());
        articleComment.setArticleCommentContent("我是评论内容");
        articleComment.setFkUserId(32);
        articleComment.setFkArticleId(10);

        commodityMapper.addComment(articleComment);
        System.out.println(articleComment.getArticleCommentId());
    }


    @Test
    public void ArticleAndCommentAndUser() {

        List<ArticleAndCommentAndUser> allArticleAndCommentAndUser = commodityMapper.getAllArticleAndCommentAndUser();
        System.out.println(allArticleAndCommentAndUser.get(0).getCommodityPhotos());
    }

    @Test
    public void getCommodityById() {
        Commodity c = commodityMapper.getCommodityById(33);
        System.out.println(c);
    }

    @Test
    public void addCommodityPhoto() {
        commodityMapper.addArticlePhotoPath(26,"1.jpg");
    }

    @Test
    public void updateCommodityDetail(){

        Commodity c = new Commodity();
        c.setPublishId(86);
        c.setPublishPrice(123.0f);
        c.setPublishTitle("mapper xiugai");
        c.setPublishStyle("网上交易");
        c.setPublishTitle("aaaa");
        c.setPublishContent("asdsdsd");
        commodityMapper.updateCommodityDetail(c);


        System.out.println(c);
    }

}
