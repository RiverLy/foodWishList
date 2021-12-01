package com.gram.foodList.wishlist.repository;

import com.gram.foodList.wishlist.domain.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

}
