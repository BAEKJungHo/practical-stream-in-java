package com.weave.stream.app;

import com.weave.stream.domain.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ShadowingApplication
 * =============================================================
 * Topic : Stream 의 특징에 대해서 알아보자(Lazy, Short circuit, Loop-fusion 등)
 * @author BAEKJH
 * @since 2020-08-03
 * =============================================================
 */
public class StreamApplication {

    /**
     * Stream 은 데이터를 담고 있는 저장소가 아니라, 데이터 처리 과정을 의미
     * Stream 은 크게 중간 연산(중개 오퍼레이션) 과 최종 연산(종료 오퍼레이션) 으로 구성된다.
     *
     * 스트림 파이프라인(Stream pipeline)
     * - 스트림 파이프라인은 중간 연산과 최종 연산으로 이루어진다.
     *
     * 중간 연산(Intermediate Operation)
     * - 스트림을 리턴한다.
     * - Lazy : 필요할 때만 값을 계산한다. (필요할 때 : 최종 연산을 수행하는 시점)
     *
     * 최종 연산(Terminal Operation)
     * - 스트림이 아닌 다른 타입을 리턴한다.
     * - 스트림 파이프라인에서 결과를 도출한다.
     *
     * Loop Fusion(루프 퓨전)
     * - ex) filter 나  map 은 서로 다른 연산이지만 하나의 과정으로 병합되는 현상
     *
     * Lazy
     * - 스트림의 게으른 특성 때문에 몇 가지 최적화 효과를 얻을 수 있다.
     * - Lazy, Short circuit
     *
     * Parallel Stream
     * - 스트림은 병렬 처리가 쉽다.
     * - stream() 을 parallelStream() 으로 바꾸면 이 코드를 멀티코어 아키텍처에서 병렬로 실행할 수 있다.
     * - filter(map, sorted, collect) fmsc 같은 연산은 고수준 빌딩 블록(high-level building block)으로 이루어져 있어서
     * - 특정 스레딩 모델에 제한되지 자유롭게 어떤 상황에서든 사용할수 있다.
     * - 또한 이들은 내부적으로 단일 스레드 모델에 사용할 수 있지만 멀티 코어 아키텍처를 최대한 투명하게 활용할 수 있게 구현되어있다.
     * - 결과적으로 우리는 데이터 처리 과정을 병렬화하면서 스레드와 락을 걱정할 필요가 없다.
     * - 스레드와 락을 걱정할 필요가 없다는 것은 각각의 서로 다른 스레드로 처리한다는 것이다.
     * - 즉, Parallel Stream 로 처리하는 연산 하나하나가 스레드가 다르다.
     *
     * Collection vs Stream
     * - 컬렉션의 주제는 데이터이고, 스트림은 계산이다.
     * - 컬렉션은 Iterator 와 같은 외부 반복자를 직접 이용해야하며, 스트림은 내부 반복자를 사용한다.
     * @param args
     */
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("weave");

        // Lazy : 현재 이 부분은 중간 연산으로만 이루어져 있기 때문에, sysout 이 실행되지 않는다.
        names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        });

        List<Employee> employees = List.of(
                new Employee("weave", "Developer", "Java", 80000000),
                new Employee("john", "Designer", "UX", 47000000),
                new Employee("rochelle", "Publisher", "Javascript", 25000000),
                new Employee("bill", "Designer", "UI", 50000000),
                new Employee("may", "Publisher", "Javascript", 35000000),
                new Employee("joy", "Developer", "Python", 62000000),
                new Employee("ellis", "Developer", "C", 58000000)
        );

        // 개발자 중에서 금액이 가장 높은 사람
        Employee topDeveloper = employees.stream()
                .filter(employee -> "Developer".equals(employee.getPosition())) // stream 반환
                .max(Comparator.comparingInt(Employee::getAnnualIncome)) // optional 반환
                .get(); // vo 를 반환

        System.out.println("name : " + topDeveloper.getName() + " annual Income : " + topDeveloper.getAnnualIncome());

        // stream : 연봉이 5천만원 이상인 사람의 이름 리스트
        /**
         * result
         * weave main
         * bill main
         * joy main
         * ellis main
         */
        List<String> employeeNames = employees.stream()
                .filter(employee -> employee.getAnnualIncome() >= 50000000) // stream 반환
                .map(employee -> {
                    System.out.println(employee.getName() + " " + Thread.currentThread().getName());
                    return employee.getName().toUpperCase();
                })
                .collect(Collectors.toList()); // list 반환

        // 이런 처리를 병렬로 하려면 상당히 어렵다.
        // 하지만 스트림에서는 parallelStream() 을 제공해서 손쉽게 병렬 처리를 할 수 있어서, 스레드와 락을 걱정할 필요가 없다.
        for(String name : employeeNames) {
            System.out.print("name : " + name + " ");
        }

        // parallel stream
        // parallel stream 을 쓴다고 해서 무조건 빨라지는게 아니다. 오히려 느려질 수도 있다.
        // parallel stream 을 써서 빨라지는 경우는 데이터가 정말 방대한 경우이다. 그때만 유용하고 나머지는 stream 이 더 빠르다.
        // 그리고 parallel stream 을 사용할 때 성능 측정을 해서 더 빠른경우 사용하는게 좋다.
        /**
         * result : ForkJoinPool 을 사용하여 병렬로 처리한다. (스레드가 다른 것을 볼 수 있다.)
         * ellis ForkJoinPool.commonPool-worker-19
         * joy ForkJoinPool.commonPool-worker-13
         * bill ForkJoinPool.commonPool-worker-23
         * john ForkJoinPool.commonPool-worker-5
         * weave ForkJoinPool.commonPool-worker-9
         * rochelle ForkJoinPool.commonPool-worker-27
         * may main
         */
        List<String> parallelNames = employees.parallelStream()
                .map(employee -> {
                    System.out.println(employee.getName() + " " + Thread.currentThread().getName());
                    return employee.getName().toUpperCase();
                })
                .collect(Collectors.toList());

        // 병렬 처리
        parallelNames.forEach(System.out::println);

    }

}
