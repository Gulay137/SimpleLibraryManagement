package util;

import service.AuthorService;
import service.BookService;
import service.LibraryService;
import service.impl.AuthorInputManager;
import service.impl.BookInputManager;
import service.impl.LibraryInputManager;

import java.util.Arrays;
import java.util.List;

public class MenuUtil {

    public static void handleMainMenu(AuthorService authorService, BookService bookService, LibraryService libraryService) {
        AuthorInputManager authorInputManager = new AuthorInputManager(authorService);
        BookInputManager bookInputManager = new BookInputManager(authorService, bookService);
        LibraryInputManager libraryInputManager = new LibraryInputManager(libraryService, bookService);

        boolean running = true;
        while (running) {
            List<String> mainMenuOptions = Arrays.asList(
                    "Enter Authors",
                    "Enter Books",
                    "Enter Libraries",
                    "See all Authors",
                    "Exit"
            );

            int choice = displayMenu("Main Menu", mainMenuOptions, false);

            switch (choice) {
                case 1:
                    authorInputManager.input();
                    break;
                case 2:
                    bookInputManager.input();
                    break;
                case 3:
                    libraryInputManager.input();
                    break;
                case 4:
                    authorService.getAllAuthors().forEach(System.out::println);
                    break;
                case 5:

                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }




    public static int displayMenu(String title, List<String> options, boolean showGoBackOption) {

        System.out.println("_" + title + "_");


        for (int index = 0; index < options.size(); index++) {
            System.out.println((index + 1) + ". " + options.get(index));
        }


        if (showGoBackOption) {
            System.out.println("0. Go back");
        }

        int choice;
        do {
            choice = InputUtil.inputRequiredInt("Please enter your choice");
        } while (choice < 0 || choice > options.size());

        return choice;
    }
}
