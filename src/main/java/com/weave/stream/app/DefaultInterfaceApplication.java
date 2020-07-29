package com.weave.stream.app;

import com.weave.stream.defaultInterface.impl.Train;

/**
 * DefaultInterfaceApplication
 * =============================================================
 * Topic : Default Method 테스트
 * @author BAEKJH
 * @since 2020-07-29
 * =============================================================
 */
public class DefaultInterfaceApplication {

    public static void main(String[] args) {
        Train train = new Train("KTX");
        train.printNameUpperCase();
        train.changeParts();
    }

}
