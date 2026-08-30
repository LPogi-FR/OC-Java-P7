package com.nnk.springboot.utils;

import com.nnk.springboot.service.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {


        private final UserService userService;

        public UniqueUsernameValidator(UserService userService ) {
            this.userService = userService;
        }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
            return userService.isUsernameUnique(value);
    }
}

