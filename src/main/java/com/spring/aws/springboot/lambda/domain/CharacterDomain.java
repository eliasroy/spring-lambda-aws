package com.spring.aws.springboot.lambda.domain;

import lombok.Data;

@Data
public class CharacterDomain {
    private Long id;
    private String name;
    private String healthPoints;
    private String[] skills;
}
