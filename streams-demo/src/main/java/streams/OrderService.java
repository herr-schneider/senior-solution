package streams;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {

    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }

    public long countOrdersByStatus(String status) {
        return orders.stream()
                .filter(s -> s.getStatus().equals(status))
                .count();
    }

    public List<Order> collectOrdersWithProductCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts()
                        .stream()
                        .anyMatch(p -> p.getCategory().equals(category)))
                .collect(Collectors.toList());
    }

    public List<Product> productsOverAmountPrice(int ammount) {
        return orders.stream()
                .flatMap(order -> order.getProducts()
                        .stream())
                .filter(product -> product.getPrice() > ammount)
                .collect(Collectors.toList());

    }

    public List<Product> productsOverAmountPriceOnce(int ammount) {
        return orders.stream()
                .flatMap(order -> order.getProducts()
                        .stream())
                .filter(product -> product.getPrice() > ammount)
                .distinct()
                .collect(Collectors.toList());
    }

    public double sumBetweenDate(LocalDate start, LocalDate end){
        return orders.stream()
                .filter(o -> o.getOrderDate().isAfter(start))
                .filter(o -> o.getOrderDate().isBefore(end))
                .flatMap(o -> o.getProducts().stream())
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public Optional<Product> findByName(String name){
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getName().equals(name))
                .findFirst();  //findAny();
    }

    public Optional<Order> findTheMaxValue(){
        return orders.stream()
                .max( (o1, o2) -> (int)
                        (o1.getProducts().stream().mapToDouble(Product::getPrice).sum()-o1.getProducts().stream().mapToDouble(Product::getPrice).sum())
                );
    }

    public Optional<Order> findTheMaxValue2(){
        return orders.stream()
                .max( (o1, o2) ->  (int)
                        (o1.getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble()-
                                o2.getProducts().stream().mapToDouble(Product::getPrice).max().getAsDouble())
                );
    }
}
