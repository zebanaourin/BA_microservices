# Case Study: Book and Author Management System

**Objective**: Create a microservices-based application with two services: BookService and AuthorService.
The BookService will use OpenFeign to interact with the AuthorService to retrieve author information for books.

Overview

    **BookService**: Manages book information and retrieves author details from AuthorService using OpenFeign.
    **AuthorService**: Provides author details.

## Architecture

BookService

    Endpoints:
        /books: Retrieve a list of all books.
        /books/{id}: Retrieve details of a specific book, including author information.

AuthorService

    Endpoints:
        /authors: Retrieve a list of all authors.
        /authors/{id}: Retrieve details of a specific author.

## Step-by-Step Implementation

### Set Up the Projects

Create a Spring Boot project using Spring Initializr for both `BookService` and `AuthorService`.
Include the following dependencies:

    Spring Web
    Lombok
    OpenFeign (Needed only for BookService)

### Implement the AuthorService

`Author` model class for AuthorService:

```java
public class Author {
    private Long id;
    private String name;
    // Getters and setters
}
```

`AuthorRepository` for AuthorService:

```java

@Repository
public class AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author(1L, "George Orwell"));
        authors.add(new Author(2L, "J.K. Rowling"));
        authors.add(new Author(3L, "Harper Lee"));
        authors.add(new Author(4L, "J.R.R. Tolkien"));
        authors.add(new Author(5L, "Jane Austen"));
    }

    public List<Author> findAll() {
    }

    public Author findById(Long id) {
    }
}
```

`AuthorController` for AuthorService:

```java

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return authorRepository.findById(id);
    }
}
```

### Implement the BookService

`Book` model class for BookService:

```java
public class Book {
    private Long id;
    private String title;
    private Long authorId;
    // Getters and setters
}
```

`BookRepository` for BookService:

```java

@Repository
public class BookRepository {

    private List<Book> books = new ArrayList<>();

    public BookRepository() {
        books.add(new Book(1L, "1984", 1L));
        books.add(new Book(2L, "Harry Potter", 2L));
        books.add(new Book(3L, "To Kill a Mockingbird", 3L));
        books.add(new Book(4L, "The Lord of the Rings", 4L));
        books.add(new Book(5L, "Pride and Prejudice", 5L));
    }

    public List<Book> findAll() {
    }

    public Book findById(Long id) {
    }
}
```

`BookController` for BookService:

```java

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorClient authorClient;

    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
    }

    @GetMapping("/books/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
    }
}
```

`BookDTO` class for BookService:

```java
public class BookDTO {
    private Long id;
    private String title;
    private Author author;
    // Getters and setters
}
```

### Implement the AuthorClient

`AuthorClient` will use OpenFeign to interact with the AuthorService:

```java 
public interface AuthorClient {
    public Author getAuthorById(@PathVariable Long id);
}
```

## Tasks

1. Fork the repository.
2. Create `AuhorService` and `BookService` projects with the specified dependencies.
2. Create the `Author` model class for AuthorService.
2. Implement the `AuthorRepository` class for AuthorService.
3. Implement the `AuthorController` class for AuthorService.
4. Create the `Book` model class for BookService.
5. Implement the `BookRepository` class for BookService.
6. Implement the `BookController` class for BookService.
7. Create the `BookDTO` class for BookService.
8. Create the `Author` class for BookService which will be used in the `BookDTO`.
8. Implement the `AuthorClient` interface for BookService.
9. Implement the `AuthorClient` interface using OpenFeign.
10. Update the `BookController` to use the `AuthorClient` to retrieve author information.
11. Test the application.
12. Implement error handling for cases where the author is not found.
13. Push the code to GitHub.

### Testing the Application

    Get All Books: 
    Access http://localhost:8080/books to get a list of all books with author details.

    Get Book by ID: 
    Access http://localhost:8080/books/1 to get details of a specific book, including author information.