import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;


public class BudgetService {
    Repo repo = new Repo();

    public float queryBudget(LocalDate startDate, LocalDate endDate) {
        int dayOfMonth = startDate.getDayOfMonth();

        int monthDiff = getMonthDiff(startDate, endDate);
        if (monthDiff == 0) {
            return queryBudgetInSameMonth(startDate, endDate);
        } else if (monthDiff == 1) {
            return queryBudgetAcrossOneMonth(startDate, endDate);
        } else if (monthDiff >= 2) {
            return queryBudgetMoreThanOneMonth(startDate, endDate);
        }

        return 0;
    }

    private float queryBudgetMoreThanOneMonth(LocalDate startDate, LocalDate endDate) {
        return queryBudgeOfWholeMonthInBetween(startDate, endDate)
                + queryBudgeInStartMonth(startDate)
                + queryBudgeInEndMonth(endDate);
    }

    private float queryBudgeOfWholeMonthInBetween(LocalDate startDate, LocalDate endDate) {
        float result = 0;

        // Same year
        for (int i = startDate.getMonthValue() + 1; i <= endDate.getMonthValue(); i++) {
            LocalDate date = LocalDate.of(startDate.getYear(), i,1); // the 1st day of that month
            result += getBudgetByDate(date);
        }

        // TODO: Cross year

        return result;
    }

    private float queryBudgeInStartMonth(LocalDate startDate) {
        return 0;
    }

    private float queryBudgeInEndMonth(LocalDate endDate) {
        return 0;
    }

    private float queryBudgetAcrossOneMonth(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

    private float queryBudgetInSameMonth(LocalDate startDate, LocalDate endDate) {
        int amount = getBudgetByDate(startDate);
        int days = (int) DAYS.between(startDate, endDate) + 1;
        return Math.round((amount * days / startDate.lengthOfMonth()) * 100.0) / 100.0f;
    }

    // Return Month Budget
    public int getBudgetByDate(LocalDate month) {
        final String monthString = month.format(DateTimeFormatter.ofPattern("yyyyMM"));
        final Repo repo = new Repo();
        return repo.getAll().stream().filter(
                b -> b.getYearMonth().equals(monthString)
        ).findFirst().get().getAmount();
    }

    private int getMonthDiff(LocalDate startDate, LocalDate endDate) {
        return endDate.getMonthValue() - startDate.getMonthValue();
    }
}
