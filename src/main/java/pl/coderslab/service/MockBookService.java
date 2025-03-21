package pl.coderslab.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class MockBookService implements BookService{
    private List<Book> list;

    public MockBookService(){
        this.list = new ArrayList<>();
        this.list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce	Eckel", "Helion", "programming"));
        this.list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        this.list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
    }

    public List<Book> getBooks(){
        return list;
    }

    public Optional<Book> get(Long id){
        for(Book book: list){
            if(book.getId() == id){
                return Optional.of(book);
            }
        }

        return Optional.empty();
    }

    public void add(Book book){
        list.add(book);
    }

    public void delete(Long id){
        list.removeIf(book -> book.getId() == id);
    }

    public void update(Book book){
        Book searchedBook = list.stream().filter(o -> o.getId() == book.getId()).findFirst().orElse(null);
        list.set(list.indexOf(searchedBook), book);
    }
}
