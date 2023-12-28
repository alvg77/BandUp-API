package com.bandup.api.mapper;

import com.bandup.api.dto.advertisement.AdvertisementRequest;
import com.bandup.api.dto.advertisement.AdvertisementResponse;
import com.bandup.api.entity.Advertisement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AdvertisementMapper {
    AdvertisementMapper MAPPER = Mappers.getMapper(AdvertisementMapper.class);

    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "searched", ignore = true)
    Advertisement advertisementRequestToAdvertisement(AdvertisementRequest request);

    @Mapping(target = "creator.id", source = "advertisement.user.id")
    @Mapping(target = "creator.username", source = "advertisement.user.username")
    @Mapping(target = "creator.profilePicture", source = "advertisement.user.profilePicture")
    AdvertisementResponse advertisementToAdvertisementResponse(Advertisement advertisement);

    List<AdvertisementResponse> advertisementsToAdvertisementResponses(List<Advertisement> advertisements);
}
