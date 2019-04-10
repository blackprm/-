package com.team.shop.bean;

import lombok.*;

import java.util.Date;

/**
 * 商品实体
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    private Integer publishId;
    // 发布标题
    private String publishTitle;
    // 发布价格
    private Float publishPrice;
    // 发布日期
    private Date publishDate;
    // 发布内容
    private String publishContent;
    // 是那个用户发布的
    private Integer fkUserId;
    /**
     * 1:发布闲置
     * 2:发布贴纸
     * 3:失物招领
     * 4:其他
     */
    // 发布的种类
    private Integer publishKinds;


    private String publishStyle;

    private String[] pics;


    // 发布所属类目
    private String tag;



}
