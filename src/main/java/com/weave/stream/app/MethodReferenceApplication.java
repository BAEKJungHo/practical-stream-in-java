package com.weave.stream.app;

import com.weave.stream.domain.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * MethodReferenceApplication
 * =============================================================
 * Topic : 메서드 참조 테스트
 * @author BAEKJH
 * @since 2020-07-26
 * =============================================================
 */
public class MethodReferenceApplication {

    public static void main(String[] args) {

        // static method 참조 >> className::staticMethod
        // UnaryOperator<String> name = (s) -> s.toUpperCase();
        UnaryOperator<String> name = User::printNameUpperCase;

        // 인스턴스 메서드 참조 >> instanceName::instanceMethod
        User user = new User();
        UnaryOperator<String> name2 = user::getName;

        // 생성자 참조 >> className::new
        Supplier<User> userSupplier = User::new;
        // 객체 생성
        User newUser1 = userSupplier.get();

        // 문자열을 받는 생성자 참조
        Function<String, User> userFunction = User::new;
        User newUser2 = userFunction.apply("weave");

        String[] names = {"weave", "john", "cache"};
        Arrays.sort(names, new Comparator<String>() { // Comparator 가 자바 8 부터는 FunctionalInterface 로 바뀌었다.
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        Arrays.sort(names, String::compareToIgnoreCase);
    }

}
