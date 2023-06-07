package com.example.day6.repository;

import com.example.day6.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    public List<Car> findByBrand(String brand);
//    @Query("select c from Car c where c.brand like %?1%")
//    tìm xe theo tên hoặc mô tả
    @Query(value = "SELECT * FROM Car c WHERE c.name LIKE CONCAT('%',:query,'%') " +
            "OR c.description LIKE CONCAT('%',:query,'%');", nativeQuery = true)
    List<Car> searchCarV2(String query);
}
