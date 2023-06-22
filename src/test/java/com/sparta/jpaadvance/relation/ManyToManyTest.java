package com.sparta.jpaadvance.relation;

import com.sparta.jpaadvance.entity.Food;
import com.sparta.jpaadvance.entity.User;
import com.sparta.jpaadvance.repository.FoodRepository;
import com.sparta.jpaadvance.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
public class ManyToManyTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    FoodRepository foodRepository;

    @Test
    @Rollback(value = false)
    @DisplayName("N대M 단방향 테스트")
    void test1() {

        User user = new User();
        user.setName("Robbie");

        User user2 = new User();
        user2.setName("Robbert");

        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);
//        food.getUserList().add(user);
//        food.getUserList().add(user2);

        userRepository.save(user);
        userRepository.save(user2);
        foodRepository.save(food);
        
        // 자동으로 중간 테이블 order가 create되고 insert 되는 것을 확인할 수 있다
    }

    @Test
    @Rollback(value = false)
    @DisplayName("N대M 양방향 테스트 : 외래 키 저장 실패")
    void test2() {
        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);

        Food food2 = new Food();
        food2.setName("양념 치킨");
        food2.setPrice(20000);

        // 외래 키의 주인이 아닌 User에서 Food를 저장해보자
        User user = new User();
        user.setName("Robbie");
//        user.getFoodList().add(food);
//        user.getFoodList().add(food2);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);

        // 확인하면 orders 테이블에 user_id도 food_id도 들어가지 않은 것을 알 수 있다.
    }

    @Test
    @Rollback(value = false)
    @DisplayName("N대M 양방향 테스트 : 외래 키 저장 실패 -> 성공")
    void test3() {
        Food food = new Food();
        food.setName("후라이드 치킨");
        food.setPrice(15000);

        Food food2 = new Food();
        food2.setName("양념 치킨");
        food2.setPrice(20000);

        // 외래 키의 주인이 아닌 User에서 Food를 쉽게 저장하기 위해 addFoodList()라는 메서드를 생성해 사용
        // 외래 키(연관 관계) 설정을 위해 Food에서 userList를 호출해 user객체 자신을 add함
        User user = new User();
        user.setName("Robbie");
//        user.addFoodList(food);
//        user.addFoodList(food2);

        userRepository.save(user);
        foodRepository.save(food);
        foodRepository.save(food2);
    }

    @Test
    @Rollback(value = false)
    @DisplayName("N대M 양방향 테스트")
    void test4() {
        User user = new User();
        user.setName("Robbie");

        User user2 = new User();
        user2.setName("Robbert");

        Food food = new Food();
        food.setName("아보카도 피자");
        food.setPrice(50000);
//        food.getUserList().add(user);   // 외래 키(연관 관계) 설정
//        food.getUserList().add(user2);  // 외래 키(연관 관계) 설정

        Food food2 = new Food();
        food2.setName("고구마 피자");
        food2.setPrice(30000);
//        food2.getUserList().add(user);   // 외래 키(연관 관계) 설정

        userRepository.save(user);
        userRepository.save(user2);
        foodRepository.save(food);
        foodRepository.save(food2);
        
        // User를 통해 food의 정보 조회
        System.out.println("user.getName() = " + user.getName());
        
//        List<Food> foodList = user.getFoodList();
//        for(Food f: foodList) {
//            System.out.println("f.getName() = " + f.getName());
//            System.out.println("f.getPrice() = " + f.getPrice());
//        }
        
        // 외래 키의 주인이 아닌 User 객체에 Food의 정보를 넣어주지 않아도 DB 저장에는 문제가 없지만
        // User 를 사용해 food의 정보를 조회할 수는 없다
    }

    @Test
    @Rollback(value = false)
    @DisplayName("N대M 양방향 테스트 : 객체와 양방향의 장점 활용")
    void test5(){

        User user = new User();
        user.setName("Robbie");

        User user2 = new User();
        user2.setName("Robbert");

        Food food = new Food();
        food.setName("아보카도 피자");
        food.setPrice(50000);
//        food.addUserList(user);   // 외래 키(연관 관계) 설정
//        food.addUserList(user2);  // 외래 키(연관 관계) 설정

        Food food2 = new Food();
        food2.setName("고구마 피자");
        food2.setPrice(30000);
//        food2.addUserList(user);   // 외래 키(연관 관계) 설정

        userRepository.save(user);
        userRepository.save(user2);
        foodRepository.save(food);
        foodRepository.save(food2);

        // User를 통해 food의 정보 조회
        System.out.println("user.getName() = " + user.getName());

//        List<Food> foodList = user.getFoodList();
//        for(Food f: foodList) {
//            System.out.println("f.getName() = " + f.getName());
//            System.out.println("f.getPrice() = " + f.getPrice());
//        }

    } // test5()

    @Test
    @DisplayName("N대M 조회 : Food 기준 user 정보 조회")
    void test6(){
        Food food = foodRepository.findById(1L).orElseThrow(NullPointerException::new);
        // 음식 정보 조회
        System.out.println("food.getName() = " + food.getName());

        // 음식을 주문한 고객 정보 조회
//        List<User> userList = food.getUserList();
//        for (User user : userList) {
//            System.out.println("user.getName() = " + user.getName());
//        }
    }

    @Test
    @DisplayName("N대M 조회 : User 기준 food 정보 조회")
    void test7(){
        User user = userRepository.findById(1L).orElseThrow(NullPointerException::new);
        // 고객 정보 조회
        System.out.println("user.getName() = " + user.getName());

        // 해당 고객이 주문한 음식 정보 조회
//        List<Food> foodList = user.getFoodList();
//        for (Food food : foodList) {
//            System.out.println("food.getName() = " + food.getName());
//        }
    }

}
