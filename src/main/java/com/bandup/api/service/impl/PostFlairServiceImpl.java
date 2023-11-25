package com.bandup.api.service.impl;

import com.bandup.api.dto.PostFlairDTO;
import com.bandup.api.mapper.PostFlairMapper;
import com.bandup.api.repository.PostFlairRepository;
import com.bandup.api.service.PostFlairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFlairServiceImpl implements PostFlairService {
    private final PostFlairRepository postFlairRepository;

    @Override
    public List<PostFlairDTO> getAll() {
        return PostFlairMapper.MAPPER.toPostFlairDTOs(
                postFlairRepository.findAll()
        );
    }
}
