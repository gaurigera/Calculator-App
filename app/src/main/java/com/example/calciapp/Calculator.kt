package com.example.calciapp
class Calculator {
    private val stack = mutableListOf<Double>()
    private val operators = mutableListOf<String>()
    private var currentNumber: Double = 0.0

    // Function to handle digit button press
    fun digitPressed(digit: Int) {
        currentNumber = currentNumber * 10 + digit
    }

    // Function to handle operator button press
    fun operatorPressed(operator: String) {
        operators.add(operator)
        stack.add(currentNumber)
        currentNumber = 0.0 // Reset the current number
    }

    // Function to calculate the result
    fun calculate(): Double {
        stack.add(currentNumber)
        currentNumber = 0.0

        // Evaluate the expression in stack
        while (operators.isNotEmpty()) {
            val operator = operators.removeAt(operators.size - 1)
            if (operator in listOf("+", "-", "*", "/")) {
                val operand2 = stack.removeAt(stack.size - 1)
                val operand1 = stack.removeAt(stack.size - 1)
                val result = when (operator) {
                    "+" -> operand1 + operand2
                    "-" -> operand1 - operand2
                    "*" -> operand1 * operand2
                    "/" -> operand1 / operand2
                    else -> throw IllegalArgumentException("Invalid operator: $operator")
                }
                stack.add(result)
            } else {
                throw IllegalArgumentException("Invalid operator: $operator")
            }
        }

//        if (stack.size != 1) {
//            calculate()
//        }

        return stack[stack.size - 1]
    }
}
