
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


fun main() {
    print("Enter a mathematical expression (separate each number and operator with a space): ")
    val expression: MutableList<String> = readLine()!!.split(' ').toMutableList()
    println(calculateExpression(expression))
}