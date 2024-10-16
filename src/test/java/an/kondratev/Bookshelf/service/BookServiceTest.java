package an.kondratev.Bookshelf.service;

import an.kondratev.Bookshelf.dto.AuthorDTO;
import an.kondratev.Bookshelf.dto.BookDTO;
import an.kondratev.Bookshelf.model.Author;
import an.kondratev.Bookshelf.model.Book;
import an.kondratev.Bookshelf.repository.AuthorRepository;
import an.kondratev.Bookshelf.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    private Author author;
    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        author = new Author(1L, "J.K. Rowling", null);
        book = new Book(author, 1L, "Harry Potter", "Fantasy");
        bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setAuthor(new AuthorDTO());
        bookDTO.getAuthor().setId(author.getId());
        bookDTO.getAuthor().setFullName(author.getFullName());
    }

    @Test
    void testAddBook_Success() {
        when(authorRepository.findById(any())).thenReturn(Optional.of(author));
        when(bookRepository.save(any())).thenReturn(book);

        Book savedBook = bookService.addBook(bookDTO);

        assertNotNull(savedBook);
        assertEquals("Harry Potter", savedBook.getTitle());
        verify(authorRepository, times(1)).findById(any());
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void testAddBook_NewAuthor() {
        bookDTO.getAuthor().setId(null);

        when(authorRepository.save(any())).thenReturn(author);
        when(bookRepository.save(any())).thenReturn(book);

        Book savedBook = bookService.addBook(bookDTO);

        assertNotNull(savedBook);
        assertEquals("Harry Potter", savedBook.getTitle());
        verify(authorRepository, times(1)).save(any());
        verify(bookRepository, times(1)).save(any());
    }

    @Test
    void testGetBook_Success() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        Book foundBook = bookService.getBook(book.getId());

        assertNotNull(foundBook);
        assertEquals(book.getId(), foundBook.getId());
        verify(bookRepository, times(1)).findById(book.getId());
    }

    @Test
    void testGetBook_NotFound() {
        when(bookRepository.findById(book.getId())).thenReturn(Optional.empty());

        Book foundBook = bookService.getBook(book.getId());

        assertNull(foundBook);
        verify(bookRepository, times(1)).findById(book.getId());
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.save(any())).thenReturn(book);

        Book updatedBook = bookService.updateBook(book);

        assertNotNull(updatedBook);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testDeleteBook() {
        Long bookId = book.getId();
        doNothing().when(bookRepository).deleteById(bookId); // this mock is usually not necessary

        bookService.deleteBook(bookId);

        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testGetBooks() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        List<Book> books = bookService.getBooks();

        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testGetUsersWithPage() {
        Pageable pageable = Pageable.ofSize(1);
        Page<Book> mockPage = new PageImpl<>(Collections.singletonList(book), pageable, 1);

        when(bookRepository.findAll(pageable)).thenReturn(mockPage);

        Page<Book> bookPage = bookService.getUsersWithPage(pageable);

        assertNotNull(bookPage);
        assertEquals(1, bookPage.getContent().size());
        verify(bookRepository, times(1)).findAll(pageable);
    }
}

