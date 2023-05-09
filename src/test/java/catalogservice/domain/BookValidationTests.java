package catalogservice.domain;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp(){
        final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void whenALlFieldsCorrectThenValidationSucceeds(){
        var book = Book.of("1234567890", "Title", "Author", 9.90, null);
        final Set<ConstraintViolation<Book>> validate = validator.validate(book);
        assertThat(validate).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails(){
        var book = Book.of("a234567890", "Title", "Author", 9.9, null);
        final Set<ConstraintViolation<Book>> validate = validator.validate(book);
        assertThat(validate).hasSize(1);
        assertThat(validate.iterator().next().getMessage())
                .isEqualTo("The ISBN format must be valid");
    }
}
