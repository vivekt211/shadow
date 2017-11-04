package in.coderszone.shadow.job;

/**
 * Created by Vivek on 10/29/2017.
 */

public class NetworkException extends RuntimeException {

    private final int mErrorCode;

    public NetworkException(int errorCode) {
        mErrorCode = errorCode;
    }

    public boolean shouldRetry() {
        return mErrorCode < 400 || mErrorCode > 499;
    }
}