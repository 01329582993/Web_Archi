/**
 * LibraryController.java - The CONTROLLER layer.
 * It connects the View and the Model:
 * - Receives user actions from the View
 * - Calls the Model to get or change data
 * - Tells the View what to display
 *
 * The Controller NEVER prints to the console directly.
 * The Controller NEVER stores data itself.
 */
public class LibraryController {

    // References to Model and View
    private final Library model;
    private final LibraryView view;

    /** Constructor: receives both dependencies. */
    public LibraryController(Library model,
            LibraryView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Main application loop.
     * Keeps showing the menu until the user exits.
     */
    public void run() {
        boolean running = true;
        while (running) {
            // Step 1: View shows menu, returns choice
            int choice = view.showMenu();

            // Step 2: Controller decides what to do
            switch (choice) {
                case 1:
                    handleViewAll();
                    break;
                case 2:
                    handleSearchByIsbn();
                    break;
                case 3:
                    handleCheckOut();
                    break;
                case 4:
                    handleAvailability();
                    break;
                case 5:
                    running = false;
                    view.showMessage("Goodbye!");
                    break;
                default:
                    view.showMessage(
                            "Invalid choice. Try again.");
            }
        }
    }

    // ── Private handler methods ──

    /** Gets all books from Model, sends to View. */
    private void handleViewAll() {
        view.showBookList(model.getAllBooks());
    }

    /** Asks View for ISBN, queries Model, shows result. */
    private void handleSearchByIsbn() {
        String isbn = view.askIsbn();
        Book book = model.findByIsbn(isbn);
        if (book != null) {
            view.showBookDetails(book);
        } else {
            view.showMessage(
                    "No book found with ISBN: " + isbn);
        }
    }

    /** Asks for ISBN, checks out if available. */
    private void handleCheckOut() {
        String isbn = view.askIsbn();
        Book book = model.findByIsbn(isbn);
        if (book == null) {
            view.showMessage(
                    "No book found with ISBN: " + isbn);
        } else if (book.isCheckedOut()) {
            view.showMessage(
                    "Sorry, that book is already checked out.");
        } else {
            book.checkOut();
            view.showMessage("Successfully checked out: "
                    + book.getTitle());
        }
    }

    /** Gets counts from Model, sends to View. */
    private void handleAvailability() {
        int available = model.getAvailableCount();
        int total = model.getTotalCount();
        view.showAvailability(available, total);
    }
}