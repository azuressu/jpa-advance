package com.sparta.jpaadvance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "orders") // 테이블 명 틀림..
@EntityListeners(AuditingEntityListener.class) // 현재 날짜 저장하는거 (Jpa Auditing 하려면 필요)
public class Order {
    //일반적으로 외래키의 주인이 외래키를 소유하고 있음 (중간테이블이 갖고있음)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가
    private Long id;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate // 주문일
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;
}
