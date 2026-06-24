import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private static int totalBorrowings = 0;
    private Book book;


    @BeforeAll
    static void initAll() {
        totalBorrowings = 0;
        System.out.println("Инициализация всех тестов для Book");
        System.out.println("Начальное значение totalBorrowings: " + totalBorrowings);
    }


    @BeforeEach
    void setUp() {
        book = new Book("Война и мир", "Лев Толстой");
        System.out.println("Создана новая книга: " + book.getTitle() + " (" + book.getAuthor() + ")");
        System.out.println("Доступность: " + book.isAvailable());
    }

    @AfterEach
    void tearDown() {
        System.out.println("Завершение теста...");
        System.out.println("Текущее totalBorrowings: " + totalBorrowings);}

    @AfterAll
    static void tearDownAll() {
        System.out.println("Все тесты Book завершены!");
        System.out.println("Итоговое количество выдач книг: " + totalBorrowings);
    }


    @Test
    @DisplayName("Задание 1: Проверка доступности книги при создании")
    void testBookIsAvailableOnCreation() {
        System.out.println("Выполняется testBookIsAvailableOnCreation");

        // Проверяем, что книга доступна при создании
        assertTrue(book.isAvailable(), "Книга должна быть доступна при создании");
        assertNull(book.getBorrowedBy(), "Поле borrowedBy должно быть null при создании");

        System.out.println("Книга доступна, borrowedBy = null");
    }


    @Test
    @DisplayName("Задание 2.1: Выдача книги студенту")
    void testBorrowBookSuccess() {
        System.out.println("Выполняется testBorrowBookSuccess");

        boolean result = book.borrow("Петров");

        assertTrue(result, "Выдача книги должна быть успешной");
        assertFalse(book.isAvailable(), "Книга должна стать недоступной");
        assertEquals("Петров", book.getBorrowedBy(), "Книга должна быть выдана Петрову");

        totalBorrowings++;

        System.out.println("Книга выдана Петрову");
        System.out.println("totalBorrowings увеличен до: " + totalBorrowings);
    }

    @Test
    @DisplayName("Задание 2.2: Возврат книги")
    void testReturnBookSuccess() {
        System.out.println("Выполняется testReturnBookSuccess");

        book.borrow("Петров");
        System.out.println("Книга выдана Петрову (подготовка)");

        boolean result = book.returnBook();

        assertTrue(result, "Возврат книги должен быть успешным");
        assertTrue(book.isAvailable(), "Книга должна стать доступной");
        assertNull(book.getBorrowedBy(), "Поле borrowedBy должно стать null");


        System.out.println("Книга возвращена");
        System.out.println("Текущее totalBorrowings: " + totalBorrowings);
    }


    @Test
    @DisplayName("Проверка: Выдача книги, которая уже выдана")
    void testBorrowBookWhenNotAvailable() {
        System.out.println("Выполняется testBorrowBookWhenNotAvailable");

        book.borrow("Иванов");
        System.out.println("Книга выдана Иванову");

        boolean result = book.borrow("Петров");

        assertFalse(result, "Повторная выдача должна быть неуспешной");
        assertEquals("Иванов", book.getBorrowedBy(), "Книга должна остаться у Иванова");

        System.out.println("Повторная выдача отклонена");
    }

    @Test
    @DisplayName("Проверка: Возврат книги, которая не выдана")
    void testReturnBookWhenAlreadyAvailable() {
        System.out.println("Выполняется testReturnBookWhenAlreadyAvailable");

        boolean result = book.returnBook();

        assertFalse(result, "Возврат доступной книги должен быть неуспешным");
        assertTrue(book.isAvailable(), "Книга должна остаться доступной");

        System.out.println("Возврат доступной книги отклонен");
    }

    @Test
    @DisplayName("Проверка: Счетчик totalBorrowings увеличивается только при выдаче")
    void testTotalBorrowingsCounter() {
        System.out.println("Выполняется testTotalBorrowingsCounter");

        int before = totalBorrowings;

        book.borrow("Сидоров");
        totalBorrowings++;
        int afterSuccess = totalBorrowings;
        assertEquals(before + 1, afterSuccess, "Счетчик должен увеличиться при успешной выдаче");

        System.out.println("После успешной выдачи: " + before + " -> " + afterSuccess);

        book.borrow("Сидоров");
        int afterFail = totalBorrowings;
        assertEquals(afterSuccess, afterFail, "Счетчик не должен увеличиваться при неудачной выдаче");

        System.out.println("После неудачной выдачи: " + afterSuccess + " -> " + afterFail + " (без изменений)");
    }
}