package be.kdg.prog3.controllers.controller;

import be.kdg.prog3.controllers.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private final List<Book> books = new ArrayList<>();

    @PostMapping("/books")
    public ModelAndView addBook(@RequestParam String title, @RequestParam String author) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("bookAdded");
        Book newBook = new Book(title, author);
        this.books.add(newBook);
        modelAndView.getModel().put("book", newBook);
        return modelAndView;
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute Book book) {
        this.books.add(book);
        return "bookAdded";
    }

    @PostMapping("/books")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult errors) {
        if (errors.hasErrors()) {
            for (ObjectError e : errors.getAllErrors()) {
                System.err.println(e.getDefaultMessage());
            }
        }
        return "bookAdded";
    }
}
