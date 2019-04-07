package com.team.shop.bean;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleComment {

    private Integer articleCommentId;
    private Date articleCommentDate;
    private String articleCommentContent;
    private Integer fkArticleId;
    private Integer fkUserId;
}
