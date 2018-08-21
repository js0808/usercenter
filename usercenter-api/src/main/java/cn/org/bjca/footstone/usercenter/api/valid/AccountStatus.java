package cn.org.bjca.footstone.usercenter.api.valid;


import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import cn.org.bjca.footstone.usercenter.api.enmus.AccountStatusEnum;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashSet;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {AccountStatus.AccountStatusValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface AccountStatus {

  String message() default "帐号状态非法";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  public class AccountStatusValidator implements ConstraintValidator<AccountStatus, String> {

    private HashSet<String> typeIds;

    @Override
    public void initialize(AccountStatus constraintAnnotation) {
      typeIds = AccountStatusEnum.hashSet;
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
