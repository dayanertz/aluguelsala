package com.aluguelsala.validation

import com.aluguelsala.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValidator(var customerService: CustomerService)  : ConstraintValidator<EmailAvailable, String> {
  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
    if(value.isNullOrEmpty()){
      return false
    }
    return  customerService.emailAvailable(value)
  }

}
