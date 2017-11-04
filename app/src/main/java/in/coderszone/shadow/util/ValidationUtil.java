package in.coderszone.shadow.util;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Vivek on 10/29/2017.
 */

public class ValidationUtil {

    public static void validate(Validation... args) {
        for (Validation validation : args) {
            validation.validate();
        }
    }

    public static void pruneInvalid(List<? extends Validation> validations) {
        Iterator<? extends Validation> itr = validations.iterator();
        while (itr.hasNext()) {
            try {
                Validation next = itr.next();
                next.validate();
            } catch (ValidationFailedException ex) {
                itr.remove();
            }
        }
    }
}
