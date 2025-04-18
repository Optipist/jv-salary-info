package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static StringBuilder builder;
    private static final DateTimeFormatter DOT_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 1;
    private static final int DATE_INDEX = 0;
    private static final int SALARY_INDEX = 3;
    private static final int HOUR_INDEX = 2;
    private static LocalDate date;

    private String[] splited;
    private int salarySum;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DOT_FORMAT);
        LocalDate toDate = LocalDate.parse(dateTo, DOT_FORMAT);
        builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String namesRunner: names) {
            for (String dataRunner: data) {
                splited = dataRunner.split(" ");
                if (namesRunner.equals(splited[NAME_INDEX])) {
                    date = date.parse(splited[DATE_INDEX], DOT_FORMAT);
                    if ((fromDate.isEqual(date) || fromDate.isBefore(date))
                            && (toDate.isEqual(date) || toDate.isAfter(date))) {
                        salarySum += Integer.parseInt(splited[SALARY_INDEX])
                                * Integer.parseInt(splited[HOUR_INDEX]);
                    }
                }
            }
            builder.append(String.format("\n%s - %d", namesRunner, salarySum));
            salarySum = 0;
        }
        return builder.toString();
    }
}
