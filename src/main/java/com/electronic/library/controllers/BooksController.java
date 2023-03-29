package com.electronic.library.controllers;


import com.electronic.library.models.Book;
import com.electronic.library.models.Person;
import com.electronic.library.services.BookService;
import com.electronic.library.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BooksController {
    private final PeopleService peopleService;
    private final BookService bookService;



    @Autowired
    public BooksController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookService.findAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {

        model.addAttribute("book", bookService.findOne(id));

        Optional<Person> bookOwner = bookService.getBookOwner(id);
        if(bookOwner.isPresent())
            model.addAttribute("owner", bookOwner.get());
        else
            model.addAttribute("people",peopleService.findAll());


        return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/new";

       bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";

        bookService.update(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson){
        bookService.assign(id, selectedPerson);
        return "redirect:/book/" + id;
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.release(id);
        return "redirect:/book/" + id;
    }
}
