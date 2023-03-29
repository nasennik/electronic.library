package com.electronic.library.repositories;


import com.electronic.library.models.Book;
import com.electronic.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
     Optional<Person> findByName(String name);
}
