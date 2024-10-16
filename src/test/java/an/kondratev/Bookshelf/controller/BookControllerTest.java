package an.kondratev.Bookshelf.controller;

import an.kondratev.Bookshelf.dto.BookDTO;
import an.kondratev.Bookshelf.model.Book;
import an.kondratev.Bookshelf.service.BookServiceInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookServiceInterface service;

    @InjectMocks
    private BookController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getBooksWithPage() {
        Pageable pageable = Pageable.ofSize(10);
        List<Book> books = new ArrayList<>();
        Page<Book> bookPage = new PageImpl<>(books, pageable, 0);

        when(service.getUsersWithPage(pageable)).thenReturn(bookPage);

        ResponseEntity<?> response = controller.getBooksWithPage(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bookPage, response.getBody());
    }

    @Test
    void newBook() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test Title");
        bookDTO.setAuthor(null);

        Book addedBook = new Book();
        addedBook.setId(1L);
        when(service.addBook(bookDTO)).thenReturn(addedBook);

        ResponseEntity<?> response = controller.newBook(bookDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(addedBook, response.getBody());
    }

    @Test
    void getBooks() {
        List<Book> books = new ArrayList<>();
        when(service.getBooks()).thenReturn(books);

        ResponseEntity<?> response = controller.getBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(books, response.getBody());
    }

    @Test
    void getBook() {
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);

        when(service.getBook(bookId)).thenReturn(book);

        ResponseEntity<?> response = controller.getBook(bookId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void updateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Updated Title");

        when(service.updateBook(book)).thenReturn(book);

        ResponseEntity<?> response = controller.updateBook(book);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(book, response.getBody());
    }

    @Test
    void deleteBook() {
        Long bookId = 1L;

        doNothing().when(service).deleteBook(bookId);

        HttpStatus status = controller.deleteBook(bookId);

        assertEquals(HttpStatus.OK, status);
        verify(service, times(1)).deleteBook(bookId);
    }
}
