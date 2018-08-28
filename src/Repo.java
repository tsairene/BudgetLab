import java.util.ArrayList;
import java.util.List;

public class Repo implements IRepo {

    List<Budget> budges = new ArrayList<>();

    public Repo() {
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
    }

    @Override
    public List<Budget> getAll() {
        return budges;
    }
}
