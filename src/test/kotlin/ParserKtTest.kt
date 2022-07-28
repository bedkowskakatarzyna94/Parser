import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ParserKtTest {

    @Test
    fun shouldCorrectlyAddTwoNumbers() {
        // GIVEN
        val expression = "1 + 2"
        // WHEN
        val result = solveExpression(expression)
        // THEN
        assertEquals(3, result)
    }

    @Test
    fun shouldCorrectlySubtractTwoNumbers(){
        // GIVEN
        val expression = "10 - 8"
        // WHEN
        val result = solveExpression(expression)
        // THEN
        assertEquals(2, result)
    }

    @Test
    fun shouldCorrectlyMultiplyTwoNumbers(){
        // GIVEN
        val expression = "5 * 3"
        // WHEN
        val result = solveExpression(expression)
        // THEN
        assertEquals(15, result)
    }

    @Test
    fun shouldCorrectlyDivideTwoNumbers(){
        // GIVEN
        val expression = "20 / 4"
        // WHEN
        val result = solveExpression(expression)
        // THEN
        assertEquals(5, result)
    }

    @Test
    fun shouldCorrectlyCalculateExpressionWithParenthesis() {
        // GIVEN
        val expression = "( 1 + 2 ) * 3"
        val expression2 = "12 * ( 5 - 4 ) * ( ( 2 - 3 * 8 - 2 ) + 3 * 4 )"
        val expression3 = "( 34 * ( 3 - ( 4 * -32 ) ) * 82 ) * ( 2 - 3 * 21 ) + 42"
        val expression4 = ""
        // WHEN
        val result = solveExpression(expression)
        val result2 = solveExpression(expression2)
        val result3 = solveExpression(expression3)
        // THEN
        assertEquals(9, result)
        assertEquals(-144, result2)
        assertEquals(-22278866, result3)
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenInvalidInput() {
        // GIVEN
        val expression = "( 1 + 2 * 3"
        val expression2 = "4 * a + y / 3"
        // WHEN & THEN
        assertThrowsExactly(IllegalArgumentException::class.java) { solveExpression(expression) }
        assertThrowsExactly(IllegalArgumentException::class.java) {solveExpression(expression2)}
    }




}