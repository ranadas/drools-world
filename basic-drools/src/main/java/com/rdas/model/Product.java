package com.rdas.model;

import lombok.*;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class Product {
    private String type;
    private int discount;
}
