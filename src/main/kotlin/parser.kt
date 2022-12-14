private fun calculateExpression(expression: MutableList<String>): Int {

    val operators = listOf("*", "/")

    for (operator in operators) {
        while (true) {

            val operatorIndex = expression.indexOfFirst { it == operator }

            if (operatorIndex != -1) {
                if (operator == "*") {
                    val operation = expression[operatorIndex - 1].toInt() * expression[operatorIndex + 1].toInt()
                    expression[operatorIndex] = operation.toString()
                    expression.removeAt(operatorIndex + 1)
                    expression.removeAt(operatorIndex - 1)
                }
                if (operator == "/") {
                    val operation = expression[operatorIndex - 1].toInt() / expression[operatorIndex + 1].toInt()
                    expression[operatorIndex] = operation.toString()
                    expression.removeAt(operatorIndex + 1)
                    expression.removeAt(operatorIndex - 1)
                }
            } else {
                break
            }
        }
    }

    var result = expression[0].toInt()
    for (index in 1 until expression.size - 1) {
        if (expression[index] == "+") {
            result += expression[index + 1].toInt()
        } else if (expression[index] == "-") {
            result -= expression[index + 1].toInt()
        }
    }
    return result
}

fun solveExpression(input: String?): Int {
    val expression: MutableList<String> = input?.split(' ')?.toMutableList() ?: mutableListOf()

    if (!validateExpression(expression)) {
        throw IllegalArgumentException(input)
    }

    while (true) {
        val openingParenthesisIndex = expression.indexOfLast { it == "(" }
        val closingParenthesisIndex = findClosingParenthesisIndex(openingParenthesisIndex, expression)

        if ((openingParenthesisIndex != -1) and (closingParenthesisIndex != -1)) {
            val subexpression = expression.slice((openingParenthesisIndex + 1) until closingParenthesisIndex)
            val resultOfPartialExpression = calculateExpression(subexpression.toMutableList())
            expression.subList(openingParenthesisIndex, closingParenthesisIndex).clear()
            expression[openingParenthesisIndex] = resultOfPartialExpression.toString()
        } else {
            break
        }
    }

    return calculateExpression(expression)
}

private fun findClosingParenthesisIndex(openingBraceIndex: Int, expression: MutableList<String>): Int {
    if (openingBraceIndex != -1) {
        for (index in openingBraceIndex until expression.size)
            if (expression[index] == ")") {
                return index
            }
    }
    return -1
}

private fun checkCorrectnessOfParentheses(expression: MutableList<String>): Boolean {
    var numberOfOpeningParentheses = 0;
    var numberOfClosingParentheses = 0;

    for (element in expression) {
        if (element == "(") {
            numberOfOpeningParentheses += 1
        }
        if (element == ")") {
            numberOfClosingParentheses += 1
        }
    }
    return numberOfOpeningParentheses == numberOfClosingParentheses
}

private fun checkCorrectnessOfOperandsAndOperators(expression: MutableList<String>): Boolean {
    expression.forEach {
        if (it.toIntOrNull() == null) {
            when (it) {
                "*" -> {}
                "/" -> {}
                "+" -> {}
                "-" -> {}
                "(" -> {}
                ")" -> {}
                else -> return false
            }
        }
    }
    return true
}

private fun validateExpression(expression: MutableList<String>): Boolean {
    return checkCorrectnessOfParentheses(expression) && checkCorrectnessOfOperandsAndOperators(expression)
}

fun main() {
    try {
        print("Enter a mathematical expression (separate each number, parenthesis and operator with a space): ")
        val result = solveExpression(readLine())
        println("Result: $result")
    } catch (e: IllegalArgumentException) {
        println("Invalid input")
        println(e.message)
    }
}