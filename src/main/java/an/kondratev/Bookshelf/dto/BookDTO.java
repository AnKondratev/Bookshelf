package an.kondratev.Bookshelf.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private AuthorDTO author;
    private String title;
    private String genre;
}
