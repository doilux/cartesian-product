package work.doilux.cartesian_product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cartesian {

    private final List<Object> l;

    private Cartesian(List<Object> l) {
        this.l = l;
    }

    public static Cartesian of(List<Object> l) {
        Objects.requireNonNull(l);
        return new Cartesian(l);
    }

    public List<Object> toList() {
        return l;
    }

    public Cartesian multiply(List<Object> s2) {
        Objects.requireNonNull(s2);
        return new Cartesian(l.stream().map(s -> resolveOne(s, s2)).flatMap(Collection::stream).collect(Collectors.toList()));
    }

    private List<ArrayList<Object>> resolveOne(Object n, List<Object> s2) {
        return s2.stream().map(s -> {
            if (n instanceof ArrayList) {
                ArrayList<Object> l = (ArrayList<Object>) ((ArrayList<Object>) n).clone();
                l.add(s);
                return l;
            } else {
                return new ArrayList<Object>() {
                    {
                        add(n);
                        add(s);
                    }
                };
            }
        }).collect(Collectors.toList());
    }
}
