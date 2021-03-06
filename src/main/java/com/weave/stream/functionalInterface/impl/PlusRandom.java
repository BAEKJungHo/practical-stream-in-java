package com.weave.stream.functionalInterface.impl;

import java.util.function.Function;

/**
 * PlusRandom implements Functional Interface
 * =============================================================
 * Topic : Function 함수형 인터페이스를 구현한 구현체
 * @author BAEKJH
 * @since 2020-07-26
 * =============================================================
 */
public class PlusRandom implements Function<Integer, Double> {
    @Override
    public Double apply(Integer integer) {
        return integer + Math.random();
    }
}
