import java.util.ArrayList;
import java.util.List;

/**
 * Library.java - Manages a collection of Books.
 * This is the main MODEL class. It stores data and
 * provides methods to query and modify it.
 * It has NO knowledge of how data is displayed.
 */
public class Library {

    // The list that holds all book records
    private final List<Book> books = new ArrayList<>();

    // ── Constructor: pre-load some sample data ──
    public Library() {
        books.add(new Book("978-0134685991",
                "Effective Java", "Joshua Bloch"));
        books.add(new Book("978-0596009205",
                "Head First Design Patterns",
                "Eric Freeman"));
        books.add(new Book("978-0132350884",
                "Clean Code", "Robert C. Martin"));
        books.add(new Book("978-0201633610",
                "Design Patterns", "Gang of Four"));
    }

    /** Returns a copy of the full book list. */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Searches for a book by ISBN.
     * Returns null if not found.
     */
    public Book findByIsbn(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                return b;
            }
        }
        return null; // not found
    }

    /** Adds a new book to the library. */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * Counts the number of available books.
     * Returns 0 if no books exist.
     */
    public int getAvailableCount() {
        int count = 0;
        for (Book b : books) {
            if (!b.isCheckedOut()) {
                count++;
            }
        }
        return count;
    }

    /** Returns the total number of books. */
    public int getTotalCount() {
        return books.size();
    }
}