package com.team.shop.pojo;

import com.team.shop.bean.ArticleComment;
import com.team.shop.bean.Commodity;
import com.team.shop.bean.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleAndCommentAndUser extends  Commodity{
    private User user;
    private List<ArticleComment> articleComments;
}
