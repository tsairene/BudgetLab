import java.util.Arrays;
import java.util.List;

public class Repo implements IRepo {
    @Override
    public List<Budget> getAll() {
        return Arrays.asList(
                new Budget("201808", 310)
        );
    }
}
