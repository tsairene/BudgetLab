import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class BudgetService {
    Repo repo = new Repo();

    public float queryBudget(LocalDate startDate, LocalDate endDate) {
        int monthDiff = getMonthDiff(startDate, endDate);
        if (monthDiff == 0) {
            return queryBudgetInSameMonth(startDate, endDate);
        } else if (monthDiff == 1) {
            return queryBudgetAcrossOneMonth(startDate, endDate);
        } else if (monthDiff >= 2) {
            return queryBudgetMoreThanOneMonth(startDate, endDate);
        } else {
            // monthDiff negative
            return 0;
        }
    }

    private float queryBudgetMoreThanOneMonth(LocalDate startDate, LocalDate endDate) {
        float result = 0;
        result += queryBudgeInStartMonth(startDate);
        int length = getMonthDiff(startDate.withDayOfMonth(1), endDate.minusMonths(1));
        LocalDate date = startDate;
        for (int m = 0; m < length; m++) {
            date = date.withDayOfMonth(1).plusMonths(1); // go to next month
            result += getMonthBudgetOfDate(date);
        }
        result += queryBudgeInEndMonth(endDate);
        return result;
    }

    private float queryBudgeInStartMonth(LocalDate startDate) {
        final LocalDate endDate = startDate.with(TemporalAdjusters.lastDayOfMonth());
        return queryBudgetInSameMonth(startDate, endDate);
    }

    private float queryBudgeInEndMonth(LocalDate endDate) {
        final LocalDate startDate = endDate.with(TemporalAdjusters.firstDayOfMonth());
        return queryBudgetInSameMonth(startDate, endDate);
    }

    private float queryBudgetAcrossOneMonth(LocalDate startDate, LocalDate endDate) {
        return queryBudgeInStartMonth(startDate) + queryBudgeInEndMonth(endDate);
    }

    private float queryBudgetInSameMonth(LocalDate startDate, LocalDate endDate) {
        int amount = getMonthBudgetOfDate(startDate);
        int days = (int) DAYS.between(startDate, endDate) + 1;
        return Math.round(((float) amount * (float) days / (float) startDate.lengthOfMonth()) * 100.0) / 100.0f;
    }

    public int getMonthBudgetOfDate(LocalDate month) {
        final String monthString = month.format(DateTimeFormatter.ofPattern("yyyyMM"));
        return getAllBudget().stream().filter(
                b -> b.getYearMonth().equals(monthString)
        ).findFirst().orElse(new Budget(monthString, 0)).getAmount();
    }

    protected List<Budget> getAllBudget() {
        return repo.getAll();
    }

    private int getMonthDiff(LocalDate startDate, LocalDate endDate) {
        Period p = Period.between(startDate, endDate.plusDays(1));
        return 12 * p.getYears() + p.getMonths() ;
    }
}
