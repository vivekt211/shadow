package in.coderszone.shadow.util;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Created by Vivek on 10/29/2017.
 */

public class DateUtil {

    private static final DateTimeFormatter parser = ISODateTimeFormat.dateTime();

    public static long parseDate(String input) {
        return parser.parseDateTime(input).getMillis();
    }
}
