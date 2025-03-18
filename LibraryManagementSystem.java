public class LibraryManagementSystem {
    class Book {
        String title;
        String author;
        String genre;
        String bookId;
        boolean isAvailable;
        Book next, prev;

        public Book(String title, String author, String genre, String bookId, boolean isAvailable) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookId = bookId;
            this.isAvailable = isAvailable;
            this.next = this.prev = null;
        }
    }

    private Book head, tail;

    public void addBookBeginning(String title, String author, String genre, String bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            newBook.prev = null;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addBookEnding(String title, String author, String genre, String bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
        System.out.println("Book added at the end.");
    }

    public void addBookAtPosition(String title, String author, String genre, String bookId, boolean isAvailable, int position) {
        if (position <= 1) {
            addBookBeginning(title, author, genre, bookId, isAvailable);
            return;
        }

        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        int count = 1;

        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null || temp == tail) {
            addBookEnding(title, author, genre, bookId, isAvailable);
        } else {
            newBook.next = temp.next;
            newBook.prev = temp;
            if (temp.next != null) {
                temp.next.prev = newBook;
            }
            temp.next = newBook;
            System.out.println("Book added at position " + position + ".");
        }
    }

    public void removeBook(String id) {
        Book temp = head;
        while (temp != null && !temp.bookId.equals(id)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Book not found.");
            return;
        }

        if (temp == head) {
            head = temp.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = temp.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        System.out.println("Book removed: " + temp.title);
    }

    public void searchBook(String key) {
        Book temp = head;
        System.out.println("Searching for book: " + key);
        while (temp != null) {
            if (temp.title.equals(key) || temp.bookId.equals(key)) {  // Fixed: Use bookId as well
                System.out.println("Book Found -> ID: " + temp.bookId + ", Title: " + temp.title +
                        ", Author: " + temp.author + ", Genre: " + temp.genre +
                        ", Available: " + (temp.isAvailable ? "Yes" : "No"));
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void updateAvailabilityStatus(String key, boolean updatedStatus) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equals(key) || temp.bookId.equals(key)) {
                temp.isAvailable = updatedStatus;
                System.out.println("Availability updated for book: " + temp.title);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        Book temp = head;
        System.out.println("Library Books:");
        while (temp != null) {
            System.out.println("ID: " + temp.bookId + ", Title: " + temp.title +
                    ", Author: " + temp.author + ", Genre: " + temp.genre +
                    ", Available: " + (temp.isAvailable ? "Yes" : "No"));
            temp = temp.next;
        }
    }

    public void displayBackward() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }

        Book temp = tail;
        System.out.println("Library Books in reverse order:");
        while (temp != null) {
            System.out.println("ID: " + temp.bookId + ", Title: " + temp.title +
                    ", Author: " + temp.author + ", Genre: " + temp.genre +
                    ", Available: " + (temp.isAvailable ? "Yes" : "No"));
            temp = temp.prev;
        }
    }

    public void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        LibraryManagementSystem lib = new LibraryManagementSystem();

        lib.addBookEnding("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "101", true);
        lib.addBookBeginning("1984", "George Orwell", "Dystopian", "102", false);
        lib.addBookAtPosition("To Kill a Mockingbird", "Harper Lee", "Classic", "103", true, 2);

        System.out.println("\nBefore Removal:");
        lib.displayForward();

        lib.removeBook("101");

        System.out.println("\nAfter Removal:");
        lib.displayForward();

        lib.searchBook("1984");

        lib.updateAvailabilityStatus("103", false);

        System.out.println("\nAfter Updating Availability:");
        lib.displayForward();

        System.out.println("\nReverse Display:");
        lib.displayBackward();

        System.out.print("Total Books: ");
        lib.countBooks();
    }
}
