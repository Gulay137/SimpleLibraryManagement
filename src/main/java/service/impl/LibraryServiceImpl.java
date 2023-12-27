package service.impl;

import model.Book;
import model.Library;
import service.LibraryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {

    private final EntityManager entityManager;

    public LibraryServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Library createLibrary(Library library) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            entityManager.persist(library);
            transaction.commit();
            return library;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Library getLibraryById(Long id) {
        return entityManager.find(Library.class, id);
    }

    @Override
    public List<Library> getAllLibraries() {
        TypedQuery<Library> query = entityManager.createQuery("SELECT l FROM Library l", Library.class);
        return query.getResultList();
    }

    @Override
    public Library updateLibrary(Long id, Library updatedLibrary) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Library existingLibrary = entityManager.find(Library.class, id);
            if (existingLibrary != null) {
                existingLibrary.setName(updatedLibrary.getName());
                existingLibrary.setAddress(updatedLibrary.getAddress());
                existingLibrary.setContact(updatedLibrary.getContact());
                transaction.commit();
                return existingLibrary;
            }
            transaction.rollback();
            return null;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteLibrary(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Library library = entityManager.find(Library.class, id);
            if (library != null) {
                entityManager.remove(library);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void addBookToLibrary(Long libraryId, Long bookId, int numberOfCopies) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            Library library = entityManager.find(Library.class, libraryId);
            Book book = entityManager.find(Book.class, bookId);
            if (library != null && book != null) {
                library.addBook(book, numberOfCopies);
                transaction.commit();
            } else {
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }
}
