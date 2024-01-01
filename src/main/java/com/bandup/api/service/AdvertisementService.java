package com.bandup.api.service;

import com.bandup.api.dto.LocationDTO;
import com.bandup.api.dto.advertisement.AdvertisementRequest;
import com.bandup.api.dto.advertisement.AdvertisementResponse;
import com.bandup.api.entity.Location;

import java.util.List;

public interface AdvertisementService {
    List<AdvertisementResponse> findAll(
            String postalCode,
            String city,
            String country,
            Long[] genreIds,
            Long[] artistTypeIds,
            Long userId
    );
    AdvertisementResponse findById(Long id);
    AdvertisementResponse create(AdvertisementRequest request);
    AdvertisementResponse update(Long id, AdvertisementRequest request);
    void deleteById(Long id);
}
