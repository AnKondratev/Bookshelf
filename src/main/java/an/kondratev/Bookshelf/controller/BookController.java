package an.kondratev.Bookshelf.controller;

import an.kondratev.Bookshelf.dto.BookDTO;
import an.kondratev.Bookshelf.model.Book;
import an.kondratev.Bookshelf.service.BookServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/")
public class BookController {

    private final BookServiceInterface service;

    @GetMapping("my-books")
    public ResponseEntity<Page<Book>> getBooksWithPage(Pageable pageable) {
        Page<Book> bookPage = service.getUsersWithPage(pageable);
        return new ResponseEntity<>(bookPage, HttpStatus.OK);
    }

    @PostMapping("new_book")
    public ResponseEntity<Book> newBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(service.addBook(bookDTO), HttpStatus.CREATED);
    }

    @GetMapping("all_books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(service.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getBook(id), HttpStatus.OK);
    }

    @PutMapping("update_book")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {
        return new ResponseEntity<>(service.updateBook(book), HttpStatus.OK);
    }

    @DeleteMapping("delete_book")
    public HttpStatus deleteBook(@RequestParam("id") Long id) {
        service.deleteBook(id);
        return HttpStatus.OK;
    }
}


