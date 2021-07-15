package edu.miu.cs.cs425.webapps.elibrary.controller;

import edu.miu.cs.cs425.webapps.elibrary.model.Book;
import edu.miu.cs.cs425.webapps.elibrary.model.Publisher;
import edu.miu.cs.cs425.webapps.elibrary.service.BookService;
import edu.miu.cs.cs425.webapps.elibrary.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/elibrary/book")
public class BookController {

    @Autowired
    private BookService bookService;

   @Autowired
   private PublisherService publisherService;

    @GetMapping(value = {"/list"})
    public ModelAndView listBooks() {
        var modelAndView = new ModelAndView();
        List<Book> books = bookService.getBooks();
        modelAndView.addObject("books", books);
        modelAndView.setViewName("book/list");
        return modelAndView;
    }

    @GetMapping(value = {"/new"})
    public String displayNewBookForm(Model model) {
        List<Publisher> publishers = publisherService.getAllPublishers();
        model.addAttribute("book", new Book());
        model.addAttribute("publishers", publishers);//
        return "book/new";
    }

    @PostMapping(value = {"/new"})
    public String addNewBook(@Valid
                             @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            List<Publisher> publishers = publisherService.getAllPublishers();
            model.addAttribute("publisher", publishers);
            return "book/new";
        }
        book = bookService.saveBook(book);
        return "redirect:/elibrary/book/list";
    }

    @GetMapping(value = {"/edit/{bookId}"})
    public String editBook(@PathVariable Long bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        if(book != null) {
            model.addAttribute("book", book);
            return "book/edit";
        } else {
            // TODO -- may do nothing
        }
        return "book/list";
    }

    @PostMapping(value={"/edit"})
    public String updateBook(@Valid @ModelAttribute("book") Book book,
                             BindingResult bindingResult,
                             Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "book/edit";
        }
        book = bookService.saveBook(book);
        return "redirect:/book/list";
    }

    @GetMapping(value = {"/delete/{bookId}"})
    public String deleteBook(@PathVariable Long bookId) {
        bookService.deleteBookById(bookId);
        return "redirect:/book/list";
    }

    @GetMapping(value = "/search")
    public ModelAndView searchBooks(@RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        List<Book> books = bookService.searchBooks(searchString);
        modelAndView.addObject("books", books);
        modelAndView.addObject("searchString", searchString);
        modelAndView.setViewName("book/list");
        return modelAndView;
    }

}
