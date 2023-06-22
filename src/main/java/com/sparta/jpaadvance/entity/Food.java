package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    // Order를 통해서 User를 조회할 생각이 없다면 필요없음
    @OneToMany(mappedBy = "food") // 외래 키의 주인이 Order로 넘어감
    private List<Order> orderList = new ArrayList<>();

}
