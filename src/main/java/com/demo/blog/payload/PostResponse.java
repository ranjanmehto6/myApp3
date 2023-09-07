package com.demo.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PostResponse {
        private List<PostDto> postDto;
        private int pageNo;
        private int pageSize;
        private long totalElement;
        private int totalPages;
        private boolean lasts;
    }

