package com.bandup.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContactsDTO {
    private Long id;
    private String phoneNumber;
    private String contactEmail;
    private String website;
}
