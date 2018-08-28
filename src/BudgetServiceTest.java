import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BudgetServiceTest {

    private MockBudgetService budgetService = new MockBudgetService();

    @Test
    public void same_date() {
        LocalDate startDate = LocalDate.of(2018, 8, 1);
        assertEquals(19.03, budgetService.queryBudget(startDate, startDate), 0.001);
    }

    @Test
    public void same_month() {
        LocalDate startDate = LocalDate.of(2018, 8, 2);
        LocalDate endDate = LocalDate.of(2018, 8, 3);
        assertEquals(38.06, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void budget_return_with_decimal_in_same_month() {
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2018, 3, 1);
        assertEquals(31.61, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void budget_return_with_decimal_in_2_month() {
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2018, 4, 1);
        assertEquals(1002.67, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void budget_return_with_decimal_in_2_year() {
        LocalDate startDate = LocalDate.of(2018, 3, 1);
        LocalDate endDate = LocalDate.of(2019, 2, 28);
        assertEquals(8183, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void budget_return_with_decimal_in_2_year2() {
        LocalDate startDate = LocalDate.of(2018, 12, 31);
        LocalDate endDate = LocalDate.of(2019, 2, 28);
        assertEquals(1156, budgetService.queryBudget(startDate, endDate), 0.001);
    }

    @Test
    public void get_budget_by_date() {
        final LocalDate testDate = LocalDate.of(2018, 8, 1);
        assertEquals(590, budgetService.getMonthBudgetOfDate(testDate), 0.001);
    }

    class MockBudgetService extends BudgetService {
        @Override
        protected List<Budget> getAllBudget() {
            List<Budget> budges = new ArrayList<>();
            budges.add(new Budget("201801", 310));
            budges.add(new Budget("201802", 870));
            budges.add(new Budget("201803", 980));
            budges.add(new Budget("201804", 680));
            budges.add(new Budget("201805", 445));
            budges.add(new Budget("201806", 700));
            budges.add(new Budget("201807", 1050));
            budges.add(new Budget("201808", 590));
            budges.add(new Budget("201809", 835));
            budges.add(new Budget("201810", 777));
            budges.add(new Budget("201811", 670));
            budges.add(new Budget("201812", 310));
            budges.add(new Budget("201901", 656));
            budges.add(new Budget("201902", 490));
            budges.add(new Budget("201903", 900));
            return budges;
        }
    }
}