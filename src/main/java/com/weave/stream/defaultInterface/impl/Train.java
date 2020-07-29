package com.weave.stream.defaultInterface.impl;

import com.weave.stream.defaultInterface.Machine;

/**
 * Train
 * =============================================================
 * Topic : 인터페이스를 구현한 구현체
 * @author BAEKJH
 * @since 2020-07-26
 * =============================================================
 */
public class Train implements Machine {

    private String name;

    public Train(String name) {
        this.name = name;
    }

    @Override
    public void changeParts() {
        System.out.println(getName() + " : 부품 교체");
    }

    @Override
    public String getName() {
        return name;
    }

}
