package com.weave.stream.app;

import com.weave.stream.functionalInterface.impl.PlusRandom;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * FunctionApplication
 * =============================================================
 * Topic : Function 함수형 인터페이스에 대해서 알아보자.
 * @author BAEKJH
 * @since 2020-07-26
 * =============================================================
 */
public class FunctionApplication {

    /**
     * Function<T, R> : T 를 input 받아서 R 을 return 한다.
     * 만약 T 와 R 의 타입이 동일한 경우 UnaryOperator<T> 를 사용할 수 있다.
     * UnaryOperator<T> T 를 input 받아서 T 를 return 한다.
     * @param args
     */
    public static void main(String[] args) {
        // 함수형 인터페이스 구현체를 만들어 사용
        PlusRandom plusRandom = new PlusRandom();
        System.out.println(plusRandom.apply(10));

        // 익명 클래스로 사용
        Function<Integer, Integer> plus10 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer + 10;
            }
        };
        System.out.println(plus10.apply(10));

        // 람다로 사용
        Function<Integer, Integer> plus8 = (i) -> i + 8;
        System.out.println(plus8.apply(10));

        // UnaryOperator
        UnaryOperator<Integer> multiply20 = (i) -> i * 20;
        System.out.println(multiply20.apply(10));

        /**
         * A.compose(B) A 를 수행하기 전에 B 를 수행하고 B 의 결과를 A 의 파라미터로 사용
         */
        UnaryOperator<Integer> multiplyAndPlus = (UnaryOperator<Integer>) plus8.compose(multiply20);
        System.out.println(multiplyAndPlus.apply(10));

        /**
         * A.andThen(B) A 를 수행하고 그 결과를 B 의 파라미터로 사용
         */
        UnaryOperator<Integer> plusAndMultiply = (UnaryOperator<Integer>) plus8.andThen(multiply20);
        System.out.println(plusAndMultiply.apply(10));
    }

}
