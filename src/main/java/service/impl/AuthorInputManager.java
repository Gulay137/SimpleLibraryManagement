package service.impl;

import model.Author;
import service.AuthorService;
import service.InputManager;
import util.InputUtil;

import java.util.List;
import java.util.stream.IntStream;

public class AuthorInputManager implements InputManager {
    private final AuthorService authorService;

    public AuthorInputManager(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public void input() {
        System.out.println("___ Updating Authors___");
        IntStream.range(0, 3)
                .mapToObj(i -> {
                    Author author = new Author();
                    author.setName(InputUtil.inputRequiredString("Please enter author name"));
                    author.setSurname(InputUtil.inputRequiredString("Please enter author surname"));
                    return author;
                })
                .forEach(authorService::createAuthor);
        System.out.println("Authors updated successfully.\n");
    }
}