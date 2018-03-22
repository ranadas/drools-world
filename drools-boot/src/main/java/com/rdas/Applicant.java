package com.rdas;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Applicant {
    private String name;
    private int age;
    private boolean valid = true;

    Applicant(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
