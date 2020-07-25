package com.ex.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * model
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    private Long id;

    private String title;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Long thumbUp;

    private Long visits;


}
