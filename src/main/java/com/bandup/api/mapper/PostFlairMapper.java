package com.bandup.api.mapper;

import com.bandup.api.dto.PostFlairDTO;
import com.bandup.api.entity.PostFlair;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostFlairMapper {
    PostFlairMapper MAPPER = Mappers.getMapper(PostFlairMapper.class);

    PostFlairDTO toPostFlairDTO(PostFlair postFlair);
    List<PostFlairDTO> toPostFlairDTOs(List<PostFlair> postFlairs);
}
