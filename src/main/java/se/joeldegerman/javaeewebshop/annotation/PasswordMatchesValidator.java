package se.joeldegerman.javaeewebshop.annotation;

import org.codehaus.groovy.ast.tools.BeanUtils;
import se.joeldegerman.javaeewebshop.models.dto.UserDto;
import se.joeldegerman.javaeewebshop.models.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserDto user = (UserDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }

}
