package an.kondratev.Bookshelf.service;

import an.kondratev.Bookshelf.model.Author;

import java.util.List;

public interface AuthorServiceInterface {
    List<Author> getAuthors();
    Author getAuthor(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Author author);
}
