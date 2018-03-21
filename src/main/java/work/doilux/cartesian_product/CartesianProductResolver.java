package work.doilux.cartesian_product;

import java.util.*;
import java.util.stream.Collectors;

public class CartesianProductResolver {

    public static Set<Object> resolve(Set<Object> s1, Set<Object> s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);
        if (s1.size() == 0) return new LinkedHashSet<Object>() {{
            add(new ArrayList<Object>() {{
                addAll(s2);
            }});
        }};
        if (s2.size() == 0) return new LinkedHashSet<Object>() {{
            add(new ArrayList<Object>() {{
                addAll(s1);
            }});
        }};
        return s1.stream().map(s -> resolveOne(s, s2)).flatMap(Collection::stream).collect(Collectors.toSet());
    }

    private static Set<ArrayList<Object>> resolveOne(Object n, Set<Object> s2) {
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
