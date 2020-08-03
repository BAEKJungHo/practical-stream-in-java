package com.weave.stream.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Employee
 * =============================================================
 * Topic : Employee domain class
 * @author BAEKJH
 * @since 2020-08-03
 * =============================================================
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private String name;
    private String position;
    private String skill;
    private int annualIncome;

}
