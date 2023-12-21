package service;

import model.Author;
import model.Book;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
    List<Book> getAllBooksByAuthor(Long authorId); 
}
