private fun calculateExpression(expression: MutableList<String>): String {

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
    return result.toString()
}

private fun solveExpressionRec(expression: List<String>): String {
    if (expression.none { it == "(" || it == ")" }) {
        return calculateExpression(expression.toMutableList())
    }
    val expressionWithoutParenthesis = expression.toMutableList()
    while (true) {
        val openingParenthesisIndex = expressionWithoutParenthesis.indexOfFirst { it == "(" }
        if (openingParenthesisIndex == -1) {
            break
        }
        val firstClosingParenthesisIndex = expressionWithoutParenthesis.indexOfFirst { it == ")" }
        val numberOfOpeningParenthesis =
            expressionWithoutParenthesis.slice(openingParenthesisIndex until firstClosingParenthesisIndex)
                .count { it == "(" }
        val closingParenthesisIndex =
            findClosingParenthesisIndex(expressionWithoutParenthesis, numberOfOpeningParenthesis)
        val subExpression =
            expressionWithoutParenthesis.subList(openingParenthesisIndex + 1, closingParenthesisIndex).toList()
        val subListForRemoval = expressionWithoutParenthesis.subList(openingParenthesisIndex, closingParenthesisIndex)
        subListForRemoval.clear()
        expressionWithoutParenthesis[openingParenthesisIndex] = solveExpressionRec(subExpression)
    }
    return calculateExpression(expressionWithoutParenthesis)
}

fun solveExpressionRecursively(input: String?): Int {
    val expression: MutableList<String> = input?.split(' ')?.toMutableList() ?: mutableListOf()

    if (!validateExpression(expression)) {
        throw IllegalArgumentException(input)
    }
    return solveExpressionRec(expression.toList()).toInt()
}

private fun findClosingParenthesisIndex(expression: List<String>, numberOfClosingParenthesis: Int): Int {
    var i = 0
    for (index in expression.indices) {
        if (expression[index] == ")") {
            i += 1
        }
        if (i == numberOfClosingParenthesis) {
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