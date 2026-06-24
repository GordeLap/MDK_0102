import java.util.Objects;

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    private String borrowedBy;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.borrowedBy = null;
    }

    public boolean borrow(String studentName) {
        if (isAvailable) {
            isAvailable = false;
            borrowedBy = studentName;
            return true;
        }
        return false;
    }

    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            borrowedBy = null;
            return true;
        }
        return false;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isAvailable=" + isAvailable +
                ", borrowedBy='" + borrowedBy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return isAvailable == book.isAvailable &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(borrowedBy, book.borrowedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, isAvailable, borrowedBy);
    }
}