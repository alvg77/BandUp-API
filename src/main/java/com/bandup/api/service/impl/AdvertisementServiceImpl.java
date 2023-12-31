package com.bandup.api.service.impl;

import com.bandup.api.dto.advertisement.AdvertisementRequest;
import com.bandup.api.dto.advertisement.AdvertisementResponse;
import com.bandup.api.entity.Advertisement;
import com.bandup.api.mapper.AdvertisementMapper;
import com.bandup.api.repository.AdvertisementRepository;
import com.bandup.api.repository.ArtistTypeRepository;
import com.bandup.api.repository.GenreRepository;
import com.bandup.api.service.AdvertisementService;
import com.bandup.api.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AuthService authService;
    private final AdvertisementRepository advertisementRepository;
    private final GenreRepository genreRepository;
    private final ArtistTypeRepository artistTypeRepository;

    @Override
    public List<AdvertisementResponse> findAll() {
        return AdvertisementMapper.MAPPER.advertisementsToAdvertisementResponses(
                advertisementRepository.findAll()
        );
    }

    @Override
    public AdvertisementResponse findById(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Advertisement not found")
        );
        advertisement.setViewCount(advertisement.getViewCount() + 1);
        return AdvertisementMapper.MAPPER.advertisementToAdvertisementResponse(
                advertisementRepository.save(advertisement)
        );
    }

    @Override
    public AdvertisementResponse create(AdvertisementRequest request) {
        Advertisement advertisement = AdvertisementMapper.MAPPER.advertisementRequestToAdvertisement(request);

        advertisement.setViewCount(0L);
        advertisement.setGenres(genreRepository.getGenresByIdIsIn(request.getGenreIds()));
        advertisement.setSearched(artistTypeRepository.getArtistTypesByIdIsIn(request.getSearchedArtistTypeIds()));
        advertisement.setUser(authService.getCurrentUser());

        return AdvertisementMapper.MAPPER.advertisementToAdvertisementResponse(
                advertisementRepository.save(advertisement)
        );
    }

    @Override
    public AdvertisementResponse update(Long id, AdvertisementRequest request) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Advertisement not found")
        );

        advertisement.setTitle(request.getTitle());
        advertisement.setDescription(request.getDescription());
        advertisement.setGenres(genreRepository.getGenresByIdIsIn(request.getGenreIds()));
        advertisement.setSearched(artistTypeRepository.getArtistTypesByIdIsIn(request.getSearchedArtistTypeIds()));

        return AdvertisementMapper.MAPPER.advertisementToAdvertisementResponse(
                advertisementRepository.save(advertisement)
        );
    }

    @Override
    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }
}
