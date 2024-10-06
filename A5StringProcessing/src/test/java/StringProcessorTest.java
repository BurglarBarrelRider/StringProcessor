import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class StringProcessorTest {

    StringProcessor stringProcessor = new StringProcessor();

    @Test
    void testIsStrongPassword() {
        assertTrue(stringProcessor.isStrongPassword("StrongPassword123!"), "вернет true для надежного пароля");
        assertFalse(stringProcessor.isStrongPassword("weakpassword"), "вернет false без заглавных букв || цифр || спец символов.");
        assertFalse(stringProcessor.isStrongPassword("123456"), "вернет false без букв или специальных символов.");
        assertFalse(stringProcessor.isStrongPassword("WeakPassword1"), "вернет false тк без специальных символов.");
        assertFalse(stringProcessor.isStrongPassword("!WeakerPassword"), "вернет false тк без цифр.");
    }

    @Test
    void testCalculateDigits() {
        assertEquals(2, stringProcessor.calculateDigits("There are 2 cats and 0 dog in Alatoo"), "Должен подсчитать 2 цифры.");
        assertEquals(0, stringProcessor.calculateDigits("No digits so null"), "Должен вернуть 0 для отсутствия цифр.");
        assertEquals(5, stringProcessor.calculateDigits("five nums 12345"), "подсчет 5 цифр"); // Изменено на 5
        assertEquals(5, stringProcessor.calculateDigits("1 2 3 4 5"), "подсчет 5 цифр."); // Изменено на 5
        assertEquals(1, stringProcessor.calculateDigits("One num 1"), "подсчет 1 цифры.");
    }

    @Test
    void testCalculateWords() {
        assertEquals(6, stringProcessor.calculateWords("This is a test sentence"), "Должен подсчитать 6 слов.");
        assertEquals(0, stringProcessor.calculateWords("     "), "возврат 0 для пробелов.");
        assertEquals(1, stringProcessor.calculateWords("OneWord"), "1 слово.");
        assertEquals(4, stringProcessor.calculateWords("Four words in this sentence"), "Должен подсчитать 4 слова.");
        assertEquals(0, stringProcessor.calculateWords(""), "вернет 0 для пустой строки.");
    }

    @Test
    void testCalculateExpression() {
        assertEquals(9.0, stringProcessor.calculateExpression("6 + 3"), "Должен вычислить до 9");
        assertEquals(1.0, stringProcessor.calculateExpression("10 - 9"), "Должен вычислить до 1"); // Исправлено на 1
        assertEquals(25.0, stringProcessor.calculateExpression("5 * 5"), "Должен вычислить до 25.");
        assertEquals(6.0, stringProcessor.calculateExpression("12 / 2"), "Должен вычислить до 6.");
        assertEquals(10.0, stringProcessor.calculateExpression("(5 + 5) * 1"), "Должен вычислить до 10");
    }
}
