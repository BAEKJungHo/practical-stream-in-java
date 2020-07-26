package com.weave.stream.app;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

/**
 * =============================================================
 * Topic : Effective final 과 Shadowing 에 대해서 알아보자.
 * @author BAEKJH
 * @since 2020-07-26
 * =============================================================
 */
public class ShadowingApplication {

    public static void main(String[] args) {
        ShadowingApplication shadowingApplication = new ShadowingApplication();
        shadowingApplication.run();
    }

    /**
     * 자바 8 부터는 final 을 생략해도 되는 경우가 있는데
     * 변수가 사실상 final 인 경우를 의미한다. 즉, 어디서도 변경이 안되는 경우를 의미한다.
     * 이런 final 을 Effective final 이라고 한다.
     * Effective final 인 경우 로컬 클래스, 익명 클래스, 람다에서 모두 참조가 가능하다.
     * 람다는 로컬 클래스, 중첩클래스, 익명 클래스와 달리 Shadowing 을 하지 않는다.
     *
     * Shadowing 이란 ?
     * 스코프가 다른 두 변수가 있는데, 우선순위가 높은 스코프에 선언된 변수가 우선순위가 낮은 스코프에 선언된 변수를 덮어씌우는 것을 의미한다.
     * */
    private void run() {
        // effective final variable
        int baseNumber = 10;

        // 로컬 클래스 : 로컬 클래스를 선언한 메서드(run)의 Scope 와 로컬 클래스 내부의 Scope 는 다르다.
        class LocalClass {
            void printNumber() {
                int baseNumber = 8;
                System.out.println(baseNumber); // 결과 : 8 ... Shadowing 이 일어난다.
            }
        }

        // 익명 클래스 : 익명 클래스를 선언한 메서드(run)의 Scope 와 익명 클래스 내부 메서드의 Scope 는 다르다.
        Consumer<Integer> integerConsumer1 = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 8;
                System.out.println(baseNumber); // 결과 : 8 ... Shadowing 이 일어난다.
            }
        };

        Consumer<Integer> integerConsumer2 = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber); // 매개변수의 baseNumber 를 사용한다.
            }
        };

        // 람다 : 람다는 람다를 선언한 메서드(run)의 Scope 와 람다 바디의 Scope 가 동일하다.
        IntConsumer printInt = (i) -> {
            /**
             * int baseNumber = 8; 선언하려고 하면 아래와 같은 에러가 발생한다.
             * Variable 'baseNumber' is already defined in the scope
             */
            System.out.println(baseNumber); // 지역변수인 baseNumber 를 사용한다. Shadowing 이 일어나지 않는다.
        };

        // baseNumber++;  error 발생

        /**
         * 만약 effective final 이 아닌경우 람다 바디 내에서 참조할 수 없다.
         * 즉, 위에 effective final 을 선언해놓고 아래에서 그 값을 변경 하게되면 더 이상 final 이 아니기 때문에
         * 람다에서 사용할 수 없다.
         *
         * Variable used in lambda expression should be final or effectively final 이런 에러 문구가 발생한다.
         */
    }

}
