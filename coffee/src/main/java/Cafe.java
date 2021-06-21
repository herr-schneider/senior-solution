import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Cafe {

    private List<CoffeeOrder> orders = new ArrayList<>();

    public Cafe(List<CoffeeOrder> orders) {
        this.orders = orders;
    }

    void addOrder(CoffeeOrder order) {
        orders.add(order);
    }

    double getTotalIncome() { //az eddigi összes bevétel
//    return orders.stream()
//            .map(CoffeeOrder::getCoffeeList)
//            .flatMap(l->l.stream())
//            .mapToDouble(Coffee::getPrice)
//            .sum();

        return orders.stream()
                .flatMap(l -> l.getCoffeeList().stream())
                .mapToDouble(Coffee::getPrice)
                .sum();
    }

    double getTotalIncome(LocalDate date) { //adott napi teljes bevétel
        return orders.stream()
                .filter(co -> co.getDateTime().equals(date))
                .flatMap(l -> l.getCoffeeList().stream())
                .mapToDouble(Coffee::getPrice)
                .sum();
    }

    double getNumberOfCoffee(CoffeeType type) {  //az adott típusú kávéból eladott összmennyiség
        return orders.stream()
                .flatMap(l -> l.getCoffeeList().stream())
                .filter(c -> c.getType().equals(type))
                .count();
    }

    double getOrdersAfter(LocalDateTime from) { // a megadott időpont utáni rendelések listája
        return orders.stream()
                .filter(co -> co.getDateTime().isBefore(from))
                .flatMap(l -> l.getCoffeeList().stream())
                .mapToDouble(Coffee::getPrice)
                .sum();
    }

    List<Coffee> getFirstFiveOrder(LocalDate date) { // adott napon az első 5 vásárlásban lévő kávék listája
        return orders.stream()
                .sorted(Comparator.comparing(CoffeeOrder::getDateTime))
                .limit(5)
                .flatMap(l -> l.getCoffeeList().stream())
                .collect(Collectors.toList());
    }
}
