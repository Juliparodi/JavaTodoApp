package com.juliparodi.rest.webservices.restfulwebservices.repository;

import com.juliparodi.rest.webservices.restfulwebservices.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByDescription(String description);



}
