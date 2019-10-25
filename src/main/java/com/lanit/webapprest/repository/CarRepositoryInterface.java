package com.lanit.webapprest.repository;

import com.lanit.webapprest.entity.Car;
import com.lanit.webapprest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CarRepositoryInterface extends JpaRepository<Car, Long> {
    @Query("SELECT COUNT(DISTINCT car.vendor) FROM Car car")
    long countUniqueVendors();

    @Query("SELECT COUNT(car.id) FROM Car car WHERE car.owner = ?1")
    long countPersonCars(Person person);
}