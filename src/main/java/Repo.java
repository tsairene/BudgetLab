import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {

    List<Budget> budges = new ArrayList<>();

    @Override
    public List<Budget> getAll() {
        return budges;
    }
}
