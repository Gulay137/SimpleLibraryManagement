package service.impl;

import model.Author;
import model.Book;
import service.AuthorService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class AuthorServiceImpl implements AuthorService {

    private final EntityManager entityManager;

    public AuthorServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Author createAuthor(Author author) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            entityManager.persist(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Author getAuthorById(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Author existingAuthor = entityManager.find(Author.class, id);
            if (existingAuthor != null) {
                existingAuthor.setName(updatedAuthor.getName());
                existingAuthor.setSurname(updatedAuthor.getSurname());

                transaction.commit();
                return existingAuthor;
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
    public void deleteAuthor(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        try {
            Author author = entityManager.find(Author.class, id);
            if (author != null) {
                entityManager.remove(author);
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
    public List<Book> getAllBooksByAuthor(Long authorId) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT b FROM Book b WHERE b.author.id = :authorId", Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }
}
