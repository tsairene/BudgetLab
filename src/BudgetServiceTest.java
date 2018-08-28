import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BudgetServiceTest {

    @Test
    public void same_date() {
        BudgetService budgetService = new BudgetService();
        LocalDate startDate = LocalDate.of(2018,8,1);
        assertEquals(10, budgetService.queryBudget(startDate, startDate), 0.001);
    }

    @Test
    public void same_month() {
        BudgetService budgetService = new BudgetService();
        LocalDate startDate = LocalDate.of(2018,8,2);
        LocalDate endDate = LocalDate.of(2018,8,3);
        assertTrue(20 == budgetService.queryBudget(startDate, endDate));
    }

    class StubIRepo implements IRepo {
        public List<Budget> getAll() {
            return Arrays.asList(
                    new Budget("201808", 310)
            );
        }
    }
}