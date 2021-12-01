package com.gram.foodList.wishlist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private FoodWishListService foodWishListService;

    @Test
    public void searchTest(){
        var result = foodWishListService.search("만두");
        Assertions.assertNotNull(result);
        result.forEach(item -> System.out.println(item));
    }

}
