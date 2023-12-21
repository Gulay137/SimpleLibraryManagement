import model.Author;
import model.Book;
import model.Library;
import service.AuthorService;
import service.LibraryService;
import service.impl.AuthorServiceImpl;
import service.impl.LibraryServiceImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LibraryPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        AuthorService authorService = new AuthorServiceImpl(entityManager);
        LibraryService libraryService = new LibraryServiceImpl(entityManager);

        entityManager.getTransaction().begin();

        try {

            Author author1 = new Author();
            author1.setName("Jack");
            author1.setSurname("London");

            Author author2 = new Author();
            author2.setName("");
            author2.setSurname("");

            Book book1 = new Book();
            book1.setTitle("Martin Eden");
            book1.setISBN("123456789");
            book1.setAuthor(author1);

            Book book2 = new Book();
            book2.setTitle("");
            book2.setISBN("987654321");
            book2.setAuthor(author2);

            Library library = new Library();
            library.setName(" Library");
            library.setAddress("address");
            library.setContact("Contact Info");
            libraryService.createLibrary(library);

            libraryService.addBookToLibrary(library.getId(), book1.getId(), 5);
            libraryService.addBookToLibrary(library.getId(), book2.getId(), 3);

            entityManager.getTransaction().commit();

            System.out.println("All Authors:");
            authorService.getAllAuthors().forEach(System.out::println);

        } catch (Exception e) {

            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
