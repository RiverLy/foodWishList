package com.gram.foodList.wishlist.controller;

import com.gram.foodList.wishlist.dto.FoodDto;
import com.gram.foodList.wishlist.service.FoodWishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/food")
@RestController
public class ApiController {

    private final FoodWishListService foodWishListService;

    @GetMapping("/search")
    public List<FoodDto> search(@RequestParam String query){
        return foodWishListService.search(query);
    }

    @PostMapping("")
    public FoodDto addToList(@RequestBody FoodDto foodDto){
        log.info("Request : {}", foodDto);
        var addedDto = foodWishListService.add(foodDto);
        return addedDto;
    }

    @GetMapping("/all")
    public List<FoodDto> showAll(){
        return foodWishListService.showAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        foodWishListService.delete(id);
    }

    @PutMapping("/{id}")
    public FoodDto update(@PathVariable Long id){
        return foodWishListService.updateVisit(id);
    }


}
