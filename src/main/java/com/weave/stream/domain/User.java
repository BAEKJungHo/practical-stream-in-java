package com.weave.stream.domain;

/**
 * User
 * =============================================================
 * Topic : Method Reference 테스트를 위한 클래스
 * @author BAEKJH
 * @since 2020-07-29
 * =============================================================
 */
public class User {

    private String name;

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getName(String name) {
        return name;
    }

    public static String printNameUpperCase(String name) {
        return name.toUpperCase();
    }

}
