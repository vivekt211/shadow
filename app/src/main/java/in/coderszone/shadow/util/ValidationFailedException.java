package in.coderszone.shadow.util;

/**
 * Created by Vivek on 10/29/2017.
 */

public class ValidationFailedException extends RuntimeException {

    public ValidationFailedException() {
    }

    public ValidationFailedException(String detailMessage) {
        super(detailMessage);
    }
}