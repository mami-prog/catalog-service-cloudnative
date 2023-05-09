package catalogservice.demo;

import catalogservice.domain.Book;
import catalogservice.domain.BookRepository;
import java.util.List;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("testdata")
public class BookDataLoader {

    private final BookRepository bookRepository;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadBookTestData(){
        bookRepository.deleteAll();
        var book1 = Book.of("12345678901", "NOrth", "Lyra", 9.90, null);
        var book2 = Book.of("12345672901", "East", "Polar", 12.90, null);

        bookRepository.saveAll(List.of(book1, book2));
    }
}
