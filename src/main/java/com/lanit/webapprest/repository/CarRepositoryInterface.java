package com.lanit.webapprest.repository;

import com.lanit.webapprest.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepositoryInterface extends JpaRepository<Car, Long> {
    @Query("SELECT COUNT(DISTINCT car.vendor) FROM Car car")
    long countUniqueVendors();
}