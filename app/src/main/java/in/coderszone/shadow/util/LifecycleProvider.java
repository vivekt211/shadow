package in.coderszone.shadow.util;

/**
 * Created by Vivek on 10/29/2017.
 */

public interface LifecycleProvider {

    void addLifecycleListener(LifecycleListener listener);

    void removeLifecycleListener(LifecycleListener listener);
}