package se.joeldegerman.javaeewebshop.annotation;

import se.joeldegerman.javaeewebshop.models.dto.UserAuthDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserAuthDto user = (UserAuthDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
