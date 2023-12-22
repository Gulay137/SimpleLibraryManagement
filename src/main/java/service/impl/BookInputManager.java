package service.impl;
import model.Author;
import model.Book;
import service.AuthorService;
import service.BookService;
import service.InputManager;
import util.InputUtil;

import java.util.List;
import java.util.stream.IntStream;

public class BookInputManager implements InputManager {
    private final AuthorService authorService;
    private final BookService bookService;

    public BookInputManager(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void input() {
        System.out.println("___Updating Books ___");
        List<Author> authors = authorService.getAllAuthors();
        IntStream.range(0, 2)
                .mapToObj(i -> {
                    Book book = new Book();
                    book.setTitle(InputUtil.inputRequiredString("Please enter book title"));
                    book.setISBN(InputUtil.inputRequiredString("Please enter book ISBN"));


                    System.out.println("Please select an author for the book:");
                    IntStream.range(0, authors.size())
                            .forEach(j -> System.out.println((j + 1) + ". " + authors.get(j).getName() + " " + authors.get(j).getSurname()));

                    int authorIndex = InputUtil.inputRequiredInt("Enter the number corresponding to the author") - 1;
                    if (authorIndex >= 0 && authorIndex < authors.size()) {
                        book.setAuthor(authors.get(authorIndex));
                        bookService.createBook(book);
                    } else {
                        System.out.println("Invalid author selection. Failed to create a book.");
                    }
                    return book;
                })
                .count();
        System.out.println("Books updated successfully.\n");
    }
}