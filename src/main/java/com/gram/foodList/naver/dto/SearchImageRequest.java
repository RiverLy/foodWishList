package com.gram.foodList.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRequest {

    private String query = "";
    private int display = 1;
    private int start = 1;
    private String sort = "sim";
    private String filter = "all";

    public LinkedMultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();
        map.add("query", query);
        map.add("display", Integer.toString(display));
        map.add("start", Integer.toString(start));
        map.add("sort", sort);
        map.add("filter", filter);
        return map;
    }

}
