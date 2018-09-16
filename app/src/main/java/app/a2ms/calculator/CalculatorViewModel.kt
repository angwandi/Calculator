package app.a2ms.calculator

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    //move all sates not related to the UI

    //    Variable to hold the operands and type of calculation
    private var operand1: Double? = null
    private var pendingOperation = "="

    val result = MutableLiveData<String>()
    val newNumber = MutableLiveData<String>()

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> operand1 = if (value == 0.0) {
                    Double.NaN //Handle attempt to divide by zero
                } else {
                    operand1!! / value
                }
                "*" -> operand1 = operand1!! * value  //!! bang bang operation ☺
                "-" -> operand1 = operand1!! - value
                "+" -> operand1 = operand1!! + value
            }
        }
        result.value = (operand1.toString())
        newNumber.value = ""
    }
}
