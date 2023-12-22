import service.AuthorService;
import service.BookService;
import service.LibraryService;
import service.impl.AuthorServiceImpl;
import service.impl.BookServiceImpl;
import service.impl.LibraryServiceImpl;
import util.MenuUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            AuthorService authorService = new AuthorServiceImpl(entityManager);
            BookService bookService = new BookServiceImpl(entityManager);
            LibraryService libraryService = new LibraryServiceImpl(entityManager);

            MenuUtil.handleMainMenu(authorService, bookService, libraryService);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
