package cn.org.bjca.footstone.usercenter.api.valid;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import cn.org.bjca.footstone.usercenter.api.enmus.LoginChannelEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * @author lvyong1985@gmail.com (LvYong) 2018/10/25
 * @since 1.0
 **/
@Documented
@Constraint(validatedBy = {LoginChannel.LoginChannelValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface LoginChannel {

  String message() default "登录渠道非法";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  public class LoginChannelValidator implements ConstraintValidator<LoginChannel, String> {

    private HashSet<String> typeIds;

    @Override
    public void initialize(LoginChannel constraintAnnotation) {
      typeIds = LoginChannelEnum.hashSet;
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
