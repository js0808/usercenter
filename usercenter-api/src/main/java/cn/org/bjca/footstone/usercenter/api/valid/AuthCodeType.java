package cn.org.bjca.footstone.usercenter.api.valid;


import cn.org.bjca.footstone.usercenter.api.enmus.AuthCodeTypeEnum;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {AuthCodeType.AuthCodeTypeValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface AuthCodeType {

  String message() default "验证码类型非法";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  public class AuthCodeTypeValidator implements ConstraintValidator<AuthCodeType, String> {

    private HashSet<String> typeIds;

    @Override
    public void initialize(AuthCodeType constraintAnnotation) {
      typeIds = AuthCodeTypeEnum.hashSet;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      for (String type : typeIds) {
        if (type.equals(value)) {
          return true;
        }
      }
      return false;
    }
  }
}
