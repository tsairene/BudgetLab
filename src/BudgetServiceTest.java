import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BudgetServiceTest {

    private MockBudgetService budgetService = new MockBudgetService();

    @Test
    public void budget_in_same_date() {
        MockBudgetService sut = new MockBudgetService();
        sut.stubBudges = Arrays.asList(
                new Budget("201808", 310)
        );
        LocalDate startDate = LocalDate.of(2018, 8, 1);
        budgetShouldBe(10, sut.queryBudget(startDate, startDate));
    }

    @Test
    public void budget_in_same_month() {
        MockBudgetService sut = new MockBudgetService();
        sut.stubBudges = Arrays.asList(
                new Budget("201808", 31)
        );
        LocalDate startDate = LocalDate.of(2018, 8, 2);
        LocalDate endDate = LocalDate.of(2018, 8, 3);
        budgetShouldBe(2, sut.queryBudget(startDate, endDate));
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
    public void testMonthBudget() {
        MockBudgetService sut = new MockBudgetService();
        sut.stubBudges = Arrays.asList(
                new Budget("201801", 5566)
        );
        final LocalDate testDate = LocalDate.of(2018, 1, 1);
        budgetShouldBe(5566, sut.getMonthBudgetOfDate(testDate));
    }

    private void budgetShouldBe(float expected, float actual) {
        assertEquals(expected, actual, 0.001);
    }

    class MockBudgetService extends BudgetService {

        List<Budget> stubBudges;

        @Override
        protected List<Budget> getAllBudget() {
            if (stubBudges != null) {
                return stubBudges;
            }

            return Arrays.asList(
                    new Budget("201801", 310),
                    new Budget("201802", 870),
                    new Budget("201803", 980),
                    new Budget("201804", 680),
                    new Budget("201805", 445),
                    new Budget("201806", 700),
                    new Budget("201807", 1050),
                    new Budget("201808", 590),
                    new Budget("201809", 835),
                    new Budget("201810", 777),
                    new Budget("201811", 670),
                    new Budget("201812", 310),
                    new Budget("201901", 656),
                    new Budget("201902", 490),
                    new Budget("201903", 900)
            );
        }
    }
}