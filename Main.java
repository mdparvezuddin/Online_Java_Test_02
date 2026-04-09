import java.util.HashMap;
import java.util.Map;

// Custom Exception
class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}

// Book Class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(int bookId, String title, String author, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = available;
    }

    public Book(int bookId, String title, String author) {
        this(bookId, title, author, true);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId +
                ", Title: " + title +
                ", Author: " + author +
                ", Available: " + (available ? "Yes" : "No");
    }
}

// Library Class
class Library {
    private Map<Integer, Book> books;

    public Library() {
        books = new HashMap<>();

        // Initializing with 5 books
        books.put(101, new Book(101, "Java Programming", "Mota bhai"));
        books.put(102, new Book(102, "Python Programming", " Sallu bhai"));
        books.put(103, new Book(103, "Data Structures", " Allu bhai"));
        books.put(104, new Book(104, "Operating System Concepts", "Ajju bhai"));
        books.put(105, new Book(105, " C ", "Narendra bhai"));
    }

    public void addBook(Book book) throws LibraryException {
        if (books.containsKey(book.getBookId())) {
            throw new LibraryException("Book already exists with ID: " + book.getBookId());
        }
        books.put(book.getBookId(), book);
        System.out.println("Book added successfully.");
    }

    public void issueBook(int bookId) throws LibraryException {
        Book book = books.get(bookId);

        if (book == null) {
            throw new LibraryException("Book not found with ID: " + bookId);
        }

        if (!book.isAvailable()) {
            throw new LibraryException("Book is already issued: " + book.getTitle());
        }

        book.setAvailable(false);
        System.out.println("Book issued successfully: " + book.getTitle());
    }

    public void returnBook(int bookId) throws LibraryException {
        Book book = books.get(bookId);

        if (book == null) {
            throw new LibraryException("Book not found with ID: " + bookId);
        }

        if (book.isAvailable()) {
            throw new LibraryException("Invalid return. Book was not issued: " + book.getTitle());
        }

        book.setAvailable(true);
        System.out.println("Book returned successfully: " + book.getTitle());
    }

    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        boolean found = false;

        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books are currently available.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        try {
            library.displayAvailableBooks();

            library.issueBook(102);
            library.issueBook(103);

            library.displayAvailableBooks();

            library.returnBook(102);

            library.addBook(new Book(106, "Algorithms", "Thomas"));

            library.displayAvailableBooks();



        } catch (LibraryException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}