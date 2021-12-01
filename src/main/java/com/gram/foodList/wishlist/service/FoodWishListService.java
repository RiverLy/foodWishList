package com.gram.foodList.wishlist.service;

import com.gram.foodList.naver.NaverClient;
import com.gram.foodList.naver.dto.SearchImageRequest;
import com.gram.foodList.naver.dto.SearchLocalRequest;
import com.gram.foodList.wishlist.domain.FoodEntity;
import com.gram.foodList.wishlist.dto.FoodDto;
import com.gram.foodList.wishlist.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FoodWishListService {

    private final NaverClient naverClient;
    private final FoodRepository repository;

    public List<FoodDto> search(String query) {

        List<FoodDto> dtoList = new ArrayList<>();

        //지역검색
        var searchLocalReq = new SearchLocalRequest();
        searchLocalReq.setQuery(query);
        var searchLocalResp = naverClient.searchLocal(searchLocalReq);

        searchLocalResp.getItems().forEach(item -> {

            //이미지 검색
            String imageQuery = item.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageRequest();
            searchImageReq.setQuery(imageQuery);
            var searchImageResp = naverClient.searchImage(searchImageReq);

            //DTO 생성
            FoodDto dto = FoodDto.builder()
                    .title(item.getTitle())
                    .category(item.getCategory())
                    .address(item.getAddress())
                    .roadAddress(item.getRoadAddress())
                    .homePageLink(item.getLink())
                    .build();

            if (searchImageResp.getTotal() > 0) {
                dto.setImageLink(searchImageResp.getItems().stream().findFirst().get().getLink());
            }

            dtoList.add(dto);

        });

        return dtoList;

    }

    public FoodDto add(FoodDto foodDto) {
        FoodEntity added = repository.save(dtoToEntity(foodDto));
        return entityToDto(added);
    }

    private FoodDto entityToDto(FoodEntity foodEntity) {

        FoodDto dto = FoodDto.builder()
                .id(foodEntity.getId())
                .title(foodEntity.getTitle())
                .category(foodEntity.getCategory())
                .address(foodEntity.getAddress())
                .roadAddress(foodEntity.getRoadAddress())
                .homePageLink(foodEntity.getHomePageLink())
                .imageLink(foodEntity.getImageLink())
                .isVisited(foodEntity.isVisited())
                .visitCount(foodEntity.getVisitCount())
                .lastVisitDate(foodEntity.getLastVisitDate())
                .build();
        return dto;
    }

    private FoodEntity dtoToEntity(FoodDto dto) {

        FoodEntity foodEntity = FoodEntity.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .category(dto.getCategory())
                .address(dto.getAddress())
                .roadAddress(dto.getRoadAddress())
                .homePageLink(dto.getHomePageLink())
                .imageLink(dto.getImageLink())
                .isVisited(dto.isVisited())
                .visitCount(dto.getVisitCount())
                .lastVisitDate(dto.getLastVisitDate())
                .build();

        return foodEntity;

    }

    public List<FoodDto> showAll() {

        return repository.findAll()
                .stream().map(item -> entityToDto(item))
                .collect(Collectors.toList());

    }

    public void delete(Long id) {

        repository.deleteById(id);

    }

    public FoodDto updateVisit(Long id) {

        var foodEntity = repository.findById(id);
        if (foodEntity.isPresent()) {
            var entity = foodEntity.get();

            entity.setVisited(true);
            entity.setVisitCount(entity.getVisitCount() + 1);
            entity.setLastVisitDate(LocalDateTime.now());

            return entityToDto(repository.save(entity));
        }
        return null;
    }

}
