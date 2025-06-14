package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Role_Of_BigDecimalValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Role_Of_BigDecimal {
	String message() default "Giá tiền phải là bội số của 100 (ví dụ: 10.500, 603.100)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
