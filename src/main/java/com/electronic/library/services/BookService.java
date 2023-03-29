package com.electronic.library.services;


import com.electronic.library.models.Book;
import com.electronic.library.models.Person;
import com.electronic.library.repositories.BooksRepository;
import com.electronic.library.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;

    @Autowired
    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(){
        return booksRepository.findAll();
    }

    public Book findOne(int id){
        return booksRepository.findById(id);
    }

    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id){
        Book book = booksRepository.findById(id);
        return book.getOwner();
    }

    @Transactional
    public void release(int id){
       booksRepository.findById(id).setOwner(null);
    }

    @Transactional
    public void assign(int id, Person selectedPerson){
        Book book = booksRepository.findById(id);
        book.setOwner(selectedPerson);
        selectedPerson.setBooks(Collections.singletonList(book));
        booksRepository.save(book);
    }
}

