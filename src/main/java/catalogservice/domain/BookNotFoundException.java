package catalogservice.domain;

public class BookNotFoundException  extends RuntimeException {

    public BookNotFoundException(String isbn){
        super("THe book with ISBC " + isbn + " was not found");
    }
}
