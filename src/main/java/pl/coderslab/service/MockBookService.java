package pl.coderslab.service;

import org.springframework.stereotype.Component;
import pl.coderslab.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MockBookService implements BookService{
    private List<Book> list;

    public MockBookService(){
        this.list = new ArrayList<>();
        this.list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce	Eckel", "Helion", "programming"));
        this.list.add(new Book(2L,"9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        this.list.add(new Book(3L,"9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
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
        book.setId(list.stream().mapToLong(Book::getId).max().orElse(0) + 1);
        list.add(book);
    }

    public void delete(Long id){
        if(get(id).isPresent()){
            list.remove(get(id).get());
        }
    }

    public void update(Book book){
        if(get(book.getId()).isPresent()){
            int indexOf = list.indexOf(this.get(book.getId()).get());
            list.set(indexOf, book);
        }
    }


}
