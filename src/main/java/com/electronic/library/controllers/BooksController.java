package com.electronic.library.controllers;


import com.electronic.library.dao.BookDAO;
import com.electronic.library.models.Book;
import com.electronic.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/book")
public class BooksController {

    BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book")Book book) {
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "book/new";

       bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/edit";

        bookDAO.update(id, book);
        return "redirect:/book";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }
}
