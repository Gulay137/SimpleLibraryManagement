package service.impl;
import model.Book;
import model.Library;
import service.BookService;
import service.InputManager;
import service.LibraryService;
import util.InputUtil;

import java.util.List;
import java.util.stream.IntStream;

public class LibraryInputManager implements InputManager {
    private final LibraryService libraryService;
    private final BookService bookService;

    public LibraryInputManager(LibraryService libraryService, BookService bookService) {
        this.libraryService = libraryService;
        this.bookService = bookService;
    }

    @Override
    public void input() {
        System.out.println("_ Filling Libraries_");
        List<Book> books = bookService.getAllBooks();
        IntStream.range(0, 1)
                .mapToObj(i -> {
                    Library library = new Library();
                    library.setName(InputUtil.inputRequiredString("Enter library name"));
                    library.setAddress(InputUtil.inputRequiredString("Enter library address"));
                    library.setContact(InputUtil.inputRequiredString("Enter library contact"));
                    library.setYearFounded(InputUtil.inputRequiredInt("Enter the year library was founded"));


                    System.out.println("Select books to add to the library:");
                    IntStream.range(0, books.size())
                            .forEach(j -> System.out.println((j + 1) + ". " + books.get(j).getTitle()));

                    int bookIndex = InputUtil.inputRequiredInt("Enter the number for the book") - 1;
                    if (bookIndex >= 0 && bookIndex < books.size()) {
                        libraryService.addBookToLibrary(library.getId(), books.get(bookIndex).getId(), 3);
                    } else {
                        System.out.println("Invalid book selection. Failed to create library");
                    }

                    libraryService.createLibrary(library);
                    return library;
                })
                .count();
        System.out.println("Libraries updated successfully.");
    }
}