package com.gram.foodList.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalRequest {

    private String query = "";
    private int display = 10;
    private int start = 1;
    private String sort = "random";

    public LinkedMultiValueMap<String, String> toMultiValueMap(){
        var map = new LinkedMultiValueMap<String, String>();
        map.add("query", query);
        map.add("display", Integer.toString(display));
        map.add("start", Integer.toString(start));
        map.add("sort", sort);
        return map;
    }

}
