fun calculateExpression(expression: MutableList<String>): Int {

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

fun findClosingParenthesisIndex(openingBraceIndex: Int, expression: MutableList<String>): Int {
    if (openingBraceIndex != -1) {
        for (index in openingBraceIndex until expression.size)
            if (expression[index] == ")") {
                return index
            }
    }
    return -1
}


//fun validateExpression(expression: MutableList<String>): Boolean{
//
//}


fun main() {

    print("Enter a mathematical expression (separate each number and operator with a space): ")
    val expression: MutableList<String> = readLine()!!.split(' ').toMutableList()

    while (true) {
        val openingParenthesisIndex = expression.indexOfLast { it == "(" }
        val closingParenthesisIndex = findClosingParenthesisIndex(openingParenthesisIndex, expression)

        if ((openingParenthesisIndex != -1) and (closingParenthesisIndex != -1)) {
            val partialExpression = expression.slice((openingParenthesisIndex + 1) until closingParenthesisIndex)
            val resultOfPartialExpression = calculateExpression(partialExpression as MutableList<String>)
            expression.subList(openingParenthesisIndex, closingParenthesisIndex).clear()
            expression[openingParenthesisIndex] = resultOfPartialExpression.toString()
        } else {
            break
        }
    }
    val result = calculateExpression(expression)
    println("Result: $result")

}