package com.practice.pyeonhan_sesang.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private final String genderType;

    Gender(String genderType) {
        this.genderType = genderType;
    }

    @JsonValue
    public String getGenderType() {
        return genderType;
    }

    @JsonCreator
    public static Gender from(String name) {
        for (Gender value : Gender.values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("이름 값이 올바르지 않습니다. : " + name);
    }

}
