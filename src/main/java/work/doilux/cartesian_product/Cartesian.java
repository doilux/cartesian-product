package work.doilux.cartesian_product;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cartesian {

    private final List<List<Object>> l;

    private Cartesian(List<List<Object>> l) {
        this.l = l;
    }

    public static Cartesian of(List<Object> l) {
        Objects.requireNonNull(l);
        return new Cartesian(l.stream().map(Collections::singletonList).collect(Collectors.toList()));

    }

    public List<List<Object>> toList() {
        return l;
    }

    public Cartesian multiply(List<Object> l2) {
        Objects.requireNonNull(l2);
        return new Cartesian(
                l.stream()
                        .flatMap(s -> l2.stream().map(t -> add(s, t)))
                        .collect(Collectors.toList()));
    }


    private List<Object> add(List<Object> l, Object o) {
        return Stream.concat(l.stream(), Stream.of(o)).collect(Collectors.toList());
    }
}
