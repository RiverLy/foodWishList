package com.gram.foodList.naver;

import com.gram.foodList.naver.dto.SearchImageRequest;
import com.gram.foodList.naver.dto.SearchImageResponse;
import com.gram.foodList.naver.dto.SearchLocalRequest;
import com.gram.foodList.naver.dto.SearchLocalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageSearchUrl;

    public SearchLocalResponse searchLocal(SearchLocalRequest request){

        URI uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(request.toMultiValueMap())
                .encode()
                .build()
                .toUri();
        var headers = new HttpHeaders();

        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalResponse>(){};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();

    }

    public SearchImageResponse searchImage(SearchImageRequest request){

        URI uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(request.toMultiValueMap())
                .encode()
                .build()
                .toUri();
        var headers = new HttpHeaders();

        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImageResponse>(){};

        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();


    }
}
