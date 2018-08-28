import java.time.LocalDate;

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
        return 0;
    }

    private float queryBudgetAcrossOneMonth(LocalDate startDate, LocalDate endDate) {
        return 0;
    }

    private float queryBudgetInSameMonth(LocalDate startDate, LocalDate endDate) {
        int amount = getAmountOfMonth(startDate);
        int days = (int) DAYS.between(startDate, endDate) + 1;
        return Math.round((amount * days / startDate.lengthOfMonth()) * 100.0) / 100.0f;
    }

    private int getAmountOfMonth(LocalDate date) {
        return 0;
    }

    private int getMonthDiff(LocalDate startDate, LocalDate endDate) {
        return endDate.getMonthValue() - startDate.getMonthValue();
    }
}
