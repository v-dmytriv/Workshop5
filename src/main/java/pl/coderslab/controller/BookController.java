package pl.coderslab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.model.Book;
import pl.coderslab.service.BookService;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private BookService service;

    public BookController(BookService service){
        this.service = service;
    }

    @GetMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L,"9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return service.getBooks();
    }

    @GetMapping("/{id:\\d+}")
    public Book getBookById(@PathVariable long id){
        return this.service.get(id).orElse(null);
    }

    @PostMapping
    public void addBook(@RequestBody Book book){
        service.add(book);
    }

    @PutMapping
    public void updateBook(@RequestBody Book book){
        service.update(book);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteBook(@PathVariable long id){
        service.delete(id);
    }
}

