package com.team.shop.bean;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommodityPhoto {
    private Integer      articlePhotoId;
    private String       articlePhotoPath;
    private Integer      fkArticlPublishId;
}
