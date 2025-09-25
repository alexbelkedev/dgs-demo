package com.example.dgs.demo.books.infrastructure.graphql;

import com.example.dgs.demo.books.model.Book;
import com.netflix.graphql.dgs.*;
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@DgsComponent
public class BookDataFetcher {

    private final Map<String, Book> db = new ConcurrentHashMap<>();

    public BookDataFetcher() {
        // Demo-Daten
        var b1 = new Book(UUID.randomUUID().toString(), "Effective Java", "Joshua Bloch", 2018);
        var b2 = new Book(UUID.randomUUID().toString(), "Clean Code", "Robert C. Martin", 2008);
        var b3 = new Book(UUID.randomUUID().toString(), "Domain-Driven Design", "Eric Evans", 2003);
        Arrays.asList(b1, b2, b3).forEach(b -> db.put(b.id(), b));
    }

    @DgsQuery
    public List<Book> books(@InputArgument String author) {
        return db.values().stream()
                .filter(b -> author == null || b.author().toLowerCase().contains(author.toLowerCase()))
                .toList();
    }

    @DgsQuery
    public Book bookById(@InputArgument String id) {
        return Optional.ofNullable(db.get(id))
                .orElse(null);
    }

    public record BookInput(String title, String author, Integer year) {}

    @DgsMutation
    public Book addBook(@InputArgument BookInput input) {
        if (input == null || input.title() == null || input.author() == null) {
            // Einfaches Fehlerbeispiel
            throw new DgsEntityNotFoundException("Invalid input");
        }
        var book = new Book(UUID.randomUUID().toString(), input.title(), input.author(), input.year());
        db.put(book.id(), book);
        return book;
    }
}
