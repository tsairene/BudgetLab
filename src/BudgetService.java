import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {

    public float queryBudget(LocalDate startDate, LocalDate endDate) {
        int monthDiff = getMonthDiff(startDate, endDate);
        int amount = getMonthBudgetOfDate(startDate);

        if (monthDiff == 0) {
            int days = (int) DAYS.between(startDate, endDate) + 1;
            return Math.round(((float) amount * (float) days / (float) startDate.lengthOfMonth()) * 100.0) / 100.0f;
        } else if (monthDiff == 1) {
            final LocalDate endDate1 = startDate.with(TemporalAdjusters.lastDayOfMonth());
            final LocalDate startDate1 = endDate.with(TemporalAdjusters.firstDayOfMonth());
            int days = (int) DAYS.between(startDate, endDate1) + 1;
            int amount1 = getMonthBudgetOfDate(startDate1);
            int days1 = (int) DAYS.between(startDate1, endDate) + 1;
            return Math.round(((float) amount * (float) days / (float) startDate.lengthOfMonth()) * 100.0) / 100.0f + Math.round(((float) amount1 * (float) days1 / (float) startDate1.lengthOfMonth()) * 100.0) / 100.0f;
        } else if (monthDiff >= 2) {
            float result = 0;
            final LocalDate endDate1 = startDate.with(TemporalAdjusters.lastDayOfMonth());
            int amount2 = getMonthBudgetOfDate(startDate);
            int days1 = (int) DAYS.between(startDate, endDate1) + 1;
            result += Math.round(((float) amount2 * (float) days1 / (float) startDate.lengthOfMonth()) * 100.0) / 100.0f;
            int length = getMonthDiff(startDate.withDayOfMonth(1), endDate.minusMonths(1));
            LocalDate date = startDate;
            for (int m = 0; m < length; m++) {
                date = date.withDayOfMonth(1).plusMonths(1); // go to next month
                result += getMonthBudgetOfDate(date);
            }
            final LocalDate startDate1 = endDate.with(TemporalAdjusters.firstDayOfMonth());
            int amount1 = getMonthBudgetOfDate(startDate1);
            int days = (int) DAYS.between(startDate1, endDate) + 1;
            result += Math.round(((float) amount1 * (float) days / (float) startDate1.lengthOfMonth()) * 100.0) / 100.0f;
            return result;
        } else {
            // monthDiff negative
            return 0;
        }
    }

    public int getMonthBudgetOfDate(LocalDate month) {
        final String monthString = month.format(DateTimeFormatter.ofPattern("yyyyMM"));
        return getAllBudget().stream().filter(
                b -> b.getYearMonth().equals(monthString)
        ).findFirst().orElse(new Budget(monthString, 0)).getAmount();
    }

    protected List<Budget> getAllBudget() {
        return new Repo().getAll();
    }

    private int getMonthDiff(LocalDate startDate, LocalDate endDate) {
        Period p = Period.between(startDate, endDate.plusDays(1));
        return 12 * p.getYears() + p.getMonths() ;
    }
}
