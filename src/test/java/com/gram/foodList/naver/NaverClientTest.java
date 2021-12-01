package com.gram.foodList.naver;

import com.gram.foodList.naver.dto.SearchImageRequest;
import com.gram.foodList.naver.dto.SearchLocalRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void localSearchTest(){
        var search = new SearchLocalRequest();
        search.setQuery("순대");

        var expected = naverClient.searchLocal(search);

        System.out.println(expected);
    }

    @Test
    public void imageSearchTest(){

        var search = new SearchImageRequest();
        search.setQuery("순대");

        var expected = naverClient.searchImage(search);

        System.out.println(expected);
    }

}
