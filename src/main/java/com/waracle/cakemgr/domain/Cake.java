package com.waracle.cakemgr.domain;

import lombok.*;

/**
 * Domain object for cake!
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Cake {
    private Long id;
    private String title;
    private String desc;
    private String image;
}
