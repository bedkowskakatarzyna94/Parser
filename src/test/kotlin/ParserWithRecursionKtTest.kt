import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ParserWithRecursionKtTest {
    @Test
    fun shouldCorrectlyAddTwoNumbers() {

        val expression = "1 + 2"

        val result = solveExpressionRecursively(expression)

        assertEquals(3, result)
    }

    @Test
    fun shouldCorrectlySubtractTwoNumbers() {

        val expression = "10 - 8"

        val result = solveExpressionRecursively(expression)

        assertEquals(2, result)
    }

    @Test
    fun shouldCorrectlyMultiplyTwoNumbers() {

        val expression = "5 * 3"

        val result = solveExpressionRecursively(expression)

        assertEquals(15, result)
    }

    @Test
    fun shouldCorrectlyDivideTwoNumbers() {

        val expression = "20 / 4"

        val result = solveExpressionRecursively(expression)

        assertEquals(5, result)
    }

    @Test
    fun checkCalculationWithDividing() {

        val expression = "20 / 2 / 5 / 2"

        val result = solveExpressionRecursively(expression)

        assertEquals(1, result)
    }

    @Test
    fun checkCalculationWithMultiplication() {

        val expression = "3 * 4 * 8 * 2"

        val result = solveExpressionRecursively(expression)

        assertEquals(192, result)
    }

    @Test
    fun checkCalculationWithAdding() {

        val expression = "20000 + 2122 + 1 + 2"

        val result = solveExpressionRecursively(expression)

        assertEquals(22125, result)
    }

    @Test
    fun checkCalculationWithSubstraction() {

        val expression = "6000 - 23 - 45 - 90001 - 27"

        val result = solveExpressionRecursively(expression)

        assertEquals(-84096, result)
    }

    @Test
    fun shouldCorrectlyCalculateExpressionWithParenthesis() {

        val expression = "( 1 + 2 ) * 3"
        val expression2 = "12 * ( 5 - 4 ) * ( ( 2 - 3 * 8 - 2 ) + 3 * 4 )"
        val expression3 = "( 34 * ( 3 - ( 4 * -32 ) ) * 82 ) * ( 2 - 3 * 21 ) + 42"


        val result = solveExpressionRecursively(expression)
        val result2 = solveExpressionRecursively(expression2)
        val result3 = solveExpressionRecursively(expression3)


        assertEquals(9, result)
        assertEquals(-144, result2)
        assertEquals(-22278866, result3)
    }

    @Test
    fun shouldThrowIllegalArgumentExceptionWhenInvalidInput() {

        val expression = "( 1 + 2 * 3"
        val expression2 = "4 * a + y / 3"

        assertThrowsExactly(IllegalArgumentException::class.java) { solveExpressionRecursively(expression) }
        assertThrowsExactly(IllegalArgumentException::class.java) { solveExpressionRecursively(expression2) }
    }
}