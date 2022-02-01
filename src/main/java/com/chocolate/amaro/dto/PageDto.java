package com.chocolate.amaro.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {

    private Integer totalPages;
    private Long totalElements;
    private String nextPage;
    private String previousPage;
    private List<T> list;
}
