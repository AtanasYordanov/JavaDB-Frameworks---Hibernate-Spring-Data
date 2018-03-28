package app.annotations;

import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Component
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Invalid password format";
    int minLength() default 6;
    int maxLength() default 30;
    boolean containsDigit() default false;
    boolean containsLowerCase() default false;
    boolean containsUpperCase() default false;
    boolean containsSpecialSymbols() default false;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
