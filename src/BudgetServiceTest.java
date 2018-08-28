import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BudgetServiceTest {

    @Test
    public void same_date() {
        BudgetService budgetService = new BudgetService();
        LocalDate startDate = LocalDate.of(2018, 8, 1);
        assertEquals(19.03, budgetService.queryBudget(startDate, startDate), 0.001);
    }

    @Test
    public void same_month() {
        BudgetService budgetService = new BudgetService();
        LocalDate startDate = LocalDate.of(2018, 8, 2);
        LocalDate endDate = LocalDate.of(2018, 8, 3);
        assertEquals(38.06, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void budget_return_with_decimal_in_same_month() {
        BudgetService budgetService = new BudgetService();
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2018, 3, 1);
        assertEquals(31.61, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void budget_return_with_decimal_in_2_month() {
        BudgetService budgetService = new BudgetService();
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2018, 4, 1);
        assertEquals(1002.67, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void get_budget_by_date() {
        final BudgetService budgetService = new BudgetService();
        final LocalDate testDate = LocalDate.of(2018, 8, 1);
        assertEquals(590, budgetService.getMonthBudgetOfDate(testDate), 0.001);
    }
}