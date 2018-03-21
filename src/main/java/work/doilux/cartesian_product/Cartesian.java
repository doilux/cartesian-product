package work.doilux.cartesian_product;

import java.util.*;
import java.util.stream.Collectors;

public class Cartesian {

    private final Set<Object> l;

    private Cartesian(Set<Object> l) {
        this.l = l;
    }

    public static Cartesian of(Set<Object> l) {
        return new Cartesian(l);
    }

    public Set<Object> toSet() {
        return l;
    }

    public Set<Object> resolve(Set<Object> s2) {
        Objects.requireNonNull(l);
        Objects.requireNonNull(s2);
        if (l.size() == 0) return new LinkedHashSet<Object>() {{
            add(new ArrayList<Object>() {{
                addAll(s2);
            }});
        }};
        if (s2.size() == 0) return new LinkedHashSet<Object>() {{
            add(new ArrayList<Object>() {{
                addAll(l);
            }});
        }};
        return l.stream().map(s -> resolveOne(s, s2)).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    private Set<ArrayList<Object>> resolveOne(Object n, Set<Object> s2) {
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
        }).collect(Collectors.toSet());
    }
}
