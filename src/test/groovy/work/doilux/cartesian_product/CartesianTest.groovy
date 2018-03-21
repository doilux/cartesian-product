package work.doilux.cartesian_product

import spock.lang.Specification

class CartesianTest extends Specification {

    def "multiple size"() {
        expect:
        def act1 = Cartesian.of([1, 2]) * ["a", "b"]
        act1.toList() == [
                [1, "a"],
                [1, "b"],
                [2, "a"],
                [2, "b"]
        ]

        def act2 = Cartesian.of([1, 2]) * ["a", "b"] * ["C", "D"]
        act2.toList() == [
                [1, "a", "C"],
                [1, "a", "D"],
                [1, "b", "C"],
                [1, "b", "D"],
                [2, "a", "C"],
                [2, "a", "D"],
                [2, "b", "C"],
                [2, "b", "D"]
        ]

        def act3 = Cartesian.of([[1, "a"]]) * ["C"]
        act3.toList() == [[[1, "a"], "C"]]
    }

    def "s1 is one size"() {
        expect:
        def act = Cartesian.of([1]) * ["a", "b"]
        act.toList() == [
                [1, "a"],
                [1, "b"]
        ]
    }

    def "s2 is one size"() {
        expect:
        def act = Cartesian.of([1, 2]) * ["a"]
        act.toList() == [
                [1, "a"],
                [2, "a"]
        ]
    }

    def "s1, s2 is one size"() {
        expect:
        def act = Cartesian.of([1]) * ["a"]
        act.toList() == [
                [1, "a"]
        ]
    }

    def "s1 is zero size"() {
        expect:
        def act = Cartesian.of([]) * ["a"]
        act.toList() == []
    }

    def "s2 is zero size"() {
        expect:
        def act = Cartesian.of([1]) * []
        act.toList() == []
    }

    def "s1, s2 is zero size"() {
        expect:
        def act = Cartesian.of([]) * []
        act.toList() == []
    }

    def "s1 is null"() {
        when:
        Cartesian.of(null) * []

        then:
        thrown(NullPointerException)
    }

    def "s2 is null"() {
        when:
        Cartesian.of([]) * null

        then:
        thrown(NullPointerException)
    }
}
