package an.kondratev.Bookshelf.service;

import an.kondratev.Bookshelf.model.Author;
import an.kondratev.Bookshelf.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthorService implements AuthorServiceInterface {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAuthors() {
        return List.of();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }
}
