package pl.memexurer.goldenheads.util;

public class TimeUtil {
    public static String convertTime(final long inputs) {
        final int input = (int) inputs;
        final int numberOfDays = input / 86400;
        final int numberOfHours = input % 86400 / 3600;
        final int numberOfMinutes = input % 86400 % 3600 / 60;
        final int numberOfSeconds = input % 86400 % 3600 % 60;
        String output = "";
        if (numberOfDays > 0) {
            output = numberOfDays + "d. ";
        }
        if (numberOfHours > 0) {
            output = output + numberOfHours + "h. ";
        }
        if (numberOfMinutes > 0) {
            output = output + numberOfMinutes + "m. ";
        }
        output = output + numberOfSeconds + "s ";
        return output;
    }
}
