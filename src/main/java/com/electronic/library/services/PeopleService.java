package com.electronic.library.services;


import com.electronic.library.models.Book;
import com.electronic.library.models.Person;
import com.electronic.library.repositories.BooksRepository;
import com.electronic.library.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findOne(int id){
        Optional<Person> person = peopleRepository.findById(id);

        return person.get();
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPerson(Person owner){
       return booksRepository.findByOwner(owner);
    }

    public Optional<Person> findByName(String name){
        return peopleRepository.findByName(name);
    }
}
