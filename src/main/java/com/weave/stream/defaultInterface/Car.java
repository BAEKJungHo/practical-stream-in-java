package com.weave.stream.defaultInterface;

public interface Car extends Machine {

    /**
     * Car 에서 Machine 에서 제공한 디폴트 메서드를 제공하고싶지 않은 경우
     * 추상메서드로 선언하면 된다.
     */
    void printNameUpperCase();

    /**
     * 만약, Car 에서 Machine 에서 제공하는 똑같은 디폴트 메서드를 구현한 경우
     * 그리고 다른 클래스에서 implements Machine, CAr 로 사용하는 경우 컴파일 에러가 발생한다.
     */
}
