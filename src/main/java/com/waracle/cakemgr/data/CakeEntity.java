package com.waracle.cakemgr.data;

import lombok.*;

/**
 * JPA Entity fpr Cake
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CakeEntity {
    private Long id;
    private String title;
    private String desc;
    private String image;
}
