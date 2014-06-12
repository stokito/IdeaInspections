package annotation;

import java.lang.annotation.*;

/**
 * User: stokito
 * Date: 3/31/11
 * Time: 10:11 PM
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
@Documented
public @interface Do {
    String value();
}
