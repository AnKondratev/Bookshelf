package an.kondratev.Bookshelf.service;

import an.kondratev.Bookshelf.dto.BookDTO;
import an.kondratev.Bookshelf.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookServiceInterface {
    List<Book> getBooks();
    Book getBook(Long id);
    Book addBook(BookDTO bookDTO);
    Book updateBook(Book book);
    void deleteBook(Long id);
    Page<Book> getUsersWithPage(Pageable pageable);
}
