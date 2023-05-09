package catalogservice.domain;


import catalogservice.config.DataConfig;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

// this class is using TestCOntainers.. spring.datasource.url
@DataJdbcTest // will trigger SPring Boot to include all SPring Data JDBC entities so this class identifies a test class that focuses on SPring Data JDBC
@Import(DataConfig.class) // needed to enable auditing
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // disable the default behavior of relying on an embedded test database
        // since we want to use Testcontainers
@ActiveProfiles("integration")
public class BookRepositoryJdbcTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate; // used to prepare the data

    @Test //test method run in transaction and rolls it back at ends
    void findBookByIsbnWhenExisting(){
        var bookIsbn = "1234561237";
        var book = Book.of(bookIsbn, "Title", "AUthor", 12.90, null);
        jdbcAggregateTemplate.insert(book);
        Optional<Book> actualBook = bookRepository.findByIsbn(bookIsbn);

        assertThat(actualBook).isPresent();
        assertThat(actualBook.get().isbn()).isEqualTo(book.isbn());
    }
}
