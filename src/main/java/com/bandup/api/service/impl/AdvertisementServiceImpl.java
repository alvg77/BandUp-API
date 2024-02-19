package com.bandup.api.service.impl;

import com.bandup.api.dto.advertisement.AdvertisementRequest;
import com.bandup.api.dto.advertisement.AdvertisementResponse;
import com.bandup.api.entity.Advertisement;
import com.bandup.api.entity.User;
import com.bandup.api.mapper.AdvertisementMapper;
import com.bandup.api.mapper.ContactsMapper;
import com.bandup.api.mapper.LocationMapper;
import com.bandup.api.repository.AdvertisementRepository;
import com.bandup.api.repository.ArtistTypeRepository;
import com.bandup.api.repository.GenreRepository;
import com.bandup.api.repository.UserRepository;
import com.bandup.api.service.AdvertisementService;
import com.bandup.api.service.AuthService;
import com.bandup.api.specification.AdvertisementSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {
    private final AuthService authService;
    private final AdvertisementRepository advertisementRepository;
    private final GenreRepository genreRepository;
    private final ArtistTypeRepository artistTypeRepository;
    private final UserRepository userRepository;

    @Override
    public List<AdvertisementResponse> findAll(
            Integer pageNo,
            Integer pageSize,
            String administrativeArea,
            String city,
            String country,
            Long[] genreIds,
            Long[] artistTypeIds,
            Long userId
    ) {
        User user = authService.getCurrentUser();
        System.out.println(administrativeArea);
        System.out.println(city);
        System.out.println(country);
        Specification<Advertisement> spec = Specification.where(
                        administrativeArea != null && !administrativeArea.isEmpty() ? AdvertisementSpecification.hasAdministrativeAreaEqual(administrativeArea) : null
                ).and (
                        city != null && !city.isEmpty() ? AdvertisementSpecification.hasCityEqual(city) : null
                ).and(
                        country != null && !country.isEmpty() ? AdvertisementSpecification.hasCountryEqual(country) : null
                ).and(
                        genreIds != null ? AdvertisementSpecification.hasGenreIdsIn(genreIds) : null
                ).and(
                        artistTypeIds != null ? AdvertisementSpecification.hasArtistTypeIdsIn(artistTypeIds) : null
                ).and(
                        userId != null ? AdvertisementSpecification.hasUserIdEqual(userId) : null
                ).and(
                    AdvertisementSpecification.orderByCreatedAtDesc()
                );

        List<AdvertisementResponse> advertisementResponses = AdvertisementMapper.MAPPER.advertisementsToAdvertisementResponses(
                advertisementRepository.findAll(spec, PageRequest.of(pageNo, pageSize)).getContent()
        );

        advertisementResponses.forEach(advertisementResponse -> {
            User advertisementUser = userRepository.findById(advertisementResponse.getCreator().getId()).orElseThrow(
                    () -> new EntityNotFoundException("User not found")
            );
            advertisementResponse.setLocation(LocationMapper.MAPPER.toLocationDTO(advertisementUser.getLocation()));
            advertisementResponse.setContacts(ContactsMapper.MAPPER.toContactsDTO(advertisementUser.getContacts()));
        });

        return advertisementResponses;
    }

    @Override
    public AdvertisementResponse findById(Long id) {
        User user = authService.getCurrentUser();
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Advertisement not found")
        );

        AdvertisementResponse response = AdvertisementMapper.MAPPER.advertisementToAdvertisementResponse(
                advertisementRepository.save(advertisement)
        );

        response.setLocation(LocationMapper.MAPPER.toLocationDTO(user.getLocation()));
        response.setContacts(ContactsMapper.MAPPER.toContactsDTO(user.getContacts()));

        return response;
    }

    @Override
    public AdvertisementResponse create(AdvertisementRequest request) {
        User user = authService.getCurrentUser();
        Advertisement advertisement = AdvertisementMapper.MAPPER.advertisementRequestToAdvertisement(request);

        advertisement.setGenres(genreRepository.getGenresByIdIsIn(request.getGenreIds()));
        advertisement.setSearchedArtistTypes(artistTypeRepository.getArtistTypesByIdIsIn(request.getSearchedArtistTypeIds()));
        advertisement.setUser(user);

        AdvertisementResponse response = AdvertisementMapper.MAPPER.advertisementToAdvertisementResponse(
                advertisementRepository.save(advertisement)
        );

        response.setLocation(LocationMapper.MAPPER.toLocationDTO(user.getLocation()));
        response.setContacts(ContactsMapper.MAPPER.toContactsDTO(user.getContacts()));

        return response;
    }

    @Override
    public AdvertisementResponse update(Long id, AdvertisementRequest request) {
        User user = authService.getCurrentUser();
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Advertisement not found")
        );

        advertisement.setTitle(request.getTitle());
        advertisement.setDescription(request.getDescription());
        advertisement.setGenres(genreRepository.getGenresByIdIsIn(request.getGenreIds()));
        advertisement.setSearchedArtistTypes(artistTypeRepository.getArtistTypesByIdIsIn(request.getSearchedArtistTypeIds()));

        AdvertisementResponse response = AdvertisementMapper.MAPPER.advertisementToAdvertisementResponse(
                advertisementRepository.save(advertisement)
        );

        response.setLocation(LocationMapper.MAPPER.toLocationDTO(user.getLocation()));
        response.setContacts(ContactsMapper.MAPPER.toContactsDTO(user.getContacts()));

        return response;
    }

    @Override
    public void deleteById(Long id) {
        advertisementRepository.deleteById(id);
    }
}
