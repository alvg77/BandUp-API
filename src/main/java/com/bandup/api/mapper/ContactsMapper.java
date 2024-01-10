package com.bandup.api.mapper;

import com.bandup.api.dto.ContactsDTO;
import com.bandup.api.entity.Contacts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactsMapper {
    ContactsMapper MAPPER = Mappers.getMapper(ContactsMapper.class);

    Contacts fromContactsDTO(ContactsDTO contactsDTO);
    ContactsDTO toContactsDTO(Contacts contacts);
}
