package model;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model {
    private static final Model instance = new Model();

    private final List<Order> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new ArrayList<>();
    }

    public void add(Order order) {
        model.add(order);
    }

    /*public List<Order> list() {
        return model.stream()
                .map(Order::getName)
                .collect(Collectors.toList());
    }*/
}
