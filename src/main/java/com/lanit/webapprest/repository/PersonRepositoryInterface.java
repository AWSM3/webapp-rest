package com.lanit.webapprest.repository;

import com.lanit.webapprest.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepositoryInterface extends JpaRepository<Person, Long> {
}