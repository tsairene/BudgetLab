import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BudgetServiceTest {
    BudgetService budgetService = new BudgetService();

    @Test
    public void budget_in_same_date() {
        LocalDate startDate = LocalDate.of(2018, 8, 1);
        budgetShouldBe(19.03f, budgetService.queryBudget(startDate, startDate));
    }

    @Test
    public void budget_in_same_month() {
        LocalDate startDate = LocalDate.of(2018, 8, 2);
        LocalDate endDate = LocalDate.of(2018, 8, 3);
        budgetShouldBe(38.06f, budgetService.queryBudget(startDate, endDate));
    }

    @Test
    public void budget_return_with_decimal_in_same_month() {
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2018, 3, 1);
        budgetShouldBe(31.61f, budgetService.queryBudget(startDate, endDate));
    }

    @Test
    public void budget_return_with_decimal_in_2_month() {
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2018, 4, 1);
        budgetShouldBe(1002.67f, budgetService.queryBudget(startDate, endDate));
    }

    @Test
    public void budget_return_with_decimal_in_2_year() {
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2019, 2, 28);
        budgetShouldBe(8183, budgetService.queryBudget(startDate, endDate));
    }

    @Test
    public void budget_return_with_decimal_in_2_year2() {
        LocalDate startDate = LocalDate.of(2018, 12, 31);
        LocalDate endDate = LocalDate.of(2019, 2, 28);
        budgetShouldBe(1156, budgetService.queryBudget(startDate, endDate));
    }

    @Test
    public void get_budget_by_date() {
        final LocalDate testDate = LocalDate.of(2018, 8, 1);
        budgetShouldBe(590, budgetService.getMonthBudgetOfDate(testDate));
    }

    private void budgetShouldBe(float expected, float actual) {
        assertEquals(expected, actual, 0.001);
    }
}