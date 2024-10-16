package an.kondratev.Bookshelf.service;

import an.kondratev.Bookshelf.dto.BookDTO;
import an.kondratev.Bookshelf.model.Author;
import an.kondratev.Bookshelf.model.Book;
import an.kondratev.Bookshelf.repository.AuthorRepository;
import an.kondratev.Bookshelf.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService implements BookServiceInterface {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Page<Book> getUsersWithPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(BookDTO bookDTO) {
        Author author;
        if (bookDTO.getAuthor().getId() != null) {
            author = authorRepository.findById(bookDTO.getAuthor().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Автор не найден"));
        } else {
            author = new Author();
            author.setFullName(bookDTO.getAuthor().getFullName());
            author = authorRepository.save(author);
        }

        Book book = Book.builder()
                .author(author)
                .title(bookDTO.getTitle())
                .genre(bookDTO.getGenre())
                .build();

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
