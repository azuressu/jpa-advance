package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Order를 통해서 Food를 조회할 생각이 없다면 필요없음
    @OneToMany(mappedBy = "user")
    private List<Order> orderList = new ArrayList<>();
} 
