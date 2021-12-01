package com.gram.foodList.wishlist.repository;

import com.gram.foodList.wishlist.domain.FoodEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodRepositoryTest {

    @Autowired
    private FoodRepository foodRepository;

    @BeforeEach
    public void clear(){
        foodRepository.deleteAll();
    }

    private FoodEntity create(){

        FoodEntity foodEntity = FoodEntity.builder()
                .title("Title")
                .category("Category")
                .address("Address")
                .roadAddress("RoadAddress")
                .homePageLink("Homepage-Link")
                .imageLink("Image-Link")
                .isVisited(false)
                .visitCount(0)
                .lastVisitDate(null)
                .build();
        return foodEntity;
    }

    @Test
    public void saveTest(){

        FoodEntity foodEntity = create();

        var expected = foodRepository.save(foodEntity);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, expected.getId());

    }

    @Test
    public void updateTest(){

        FoodEntity foodEntity = create();
        var original = foodRepository.save(foodEntity);

        original.setTitle("Updated Title");
        var updated = foodRepository.save(original);

        Assertions.assertEquals("Updated Title", updated.getTitle());
        Assertions.assertEquals(1, foodRepository.findAll().size());
        Assertions.assertEquals(original.getId(), updated.getId());

    }

    @Test
    public void findByIdTest(){

        FoodEntity foodEntity = create();
        Long id = foodRepository.save(foodEntity).getId();

        var expected = foodRepository.findById(id);

        Assertions.assertEquals(true, expected.isPresent());
        Assertions.assertEquals(id, expected.get().getId());

    }

    @Test
    public void deleteTest(){

        FoodEntity foodEntity = create();
        Long id = foodRepository.save(foodEntity).getId();

        foodRepository.deleteById(id);
        int count = foodRepository.findAll().size();

        Assertions.assertEquals(0, count);

    }

    @Test
    public void findAllTest(){

        FoodEntity foodEntity1 = create();
        foodRepository.save(foodEntity1);
        FoodEntity foodEntity2 = create();
        foodRepository.save(foodEntity2);

        int count = foodRepository.findAll().size();

        Assertions.assertEquals(2, count);

    }

}
