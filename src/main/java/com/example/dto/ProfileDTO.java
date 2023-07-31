package com.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
}
