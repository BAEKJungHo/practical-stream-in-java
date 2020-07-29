package com.weave.stream.defaultInterface;

/**
 * Machine Interface
 * =============================================================
 * Topic : Default Inteface Test
 * @author BAEKJH
 * @since 2020-07-26
 * =============================================================
 */
public interface Machine {

    /**
     * 부품 교체
     */
    void changeParts();


    /**
     * 만약 여기서 printNameUpperCase() 라는 추상메서드가 필요해서 만들게되면
     * Foo 인터페이스를 구현한 구현체에서, 새롭게 추가한 추상메서드를 모두 구현해야한다.
     * 이런 문제를 해결하기 위한 것이 디폴트 메서드이다.
     */

    /**
     * @implSpec
     * 이 구현체는 getName() 으로 가져온 문자열을 대문자로 바꿔 출력한다.
     *
     * 디폴트 메서드의 경우에는 해당 메서드가 어떠한 역할을 하는지 명시해줘야하는데 보통 @impleSpec 이라는 어노테이션을 붙이고
     * 주석으로 디폴트 메서드가 하는 역할에 대해서 서술하면 된다.
     * @implSpec 어노테이션을 붙이면, 디폴트 메서드를 사용하는 곳에서 그 메서드에 마우스를 올려 놓으면 그 메서드에 적힌 주석이 나타난다.
     *
     * 디폴트 메서드를 쓰면서 얻는 이점
     * https://github.com/BAEKJungHo/the-java-8/tree/master/markdown#%EB%94%94%ED%8F%B4%ED%8A%B8-%EB%A9%94%EC%84%9C%EB%93%9C%EB%A5%BC-%EC%93%B0%EB%A9%B4%EC%84%9C-%EC%96%BB%EB%8A%94-%EC%9D%B4%EC%A0%90
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();

}
