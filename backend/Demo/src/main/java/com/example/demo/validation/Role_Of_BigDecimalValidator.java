package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class Role_Of_BigDecimalValidator implements ConstraintValidator<Role_Of_BigDecimal, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) return true; // Để @NotNull xử lý nếu cần

        // remainder với 100 → nếu khác 0 thì không hợp lệ
        return value.remainder(new BigDecimal("100")).compareTo(BigDecimal.ZERO) == 0;
    }
}