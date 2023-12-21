package service.impl;


import model.Book;
import service.BookService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final EntityManager entityManager;

    public BookServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Book createBook(Book book) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Book getBookById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Book existingBook = entityManager.find(Book.class, id);
            if (existingBook != null) {

                existingBook.setTitle(updatedBook.getTitle());
                existingBook.setISBN(updatedBook.getISBN());
                existingBook.setAuthor(updatedBook.getAuthor());

                transaction.commit();
                return existingBook;
            } else {
                transaction.rollback();
                return null;
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteBook(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                entityManager.remove(book);
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
    public List<Book> searchBooksByTitle(String title) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.title) LIKE :title", Book.class);
        query.setParameter("title", "%" + title.toLowerCase() + "%");
        return query.getResultList();
    }
}
