package work.doilux.cartesian_product

import spock.lang.Specification

class CartesianTest extends Specification {


    def "multiple size"() {
        expect:
        def act1 = Cartesian.of([1, 2]) * ["a", "b"]
        act1 == [
                [1, "a"],
                [1, "b"],
                [2, "a"],
                [2, "b"]
        ]

        def act2 = Cartesian.of(act1) * ["C", "D"]
        act2 == [
                [1, "a", "C"],
                [1, "a", "D"],
                [1, "b", "C"],
                [1, "b", "D"],
                [2, "a", "C"],
                [2, "a", "D"],
                [2, "b", "C"],
                [2, "b", "D"]
        ]
    }

    def "s1 is one size"() {
        expect:
        Cartesian.of([1]) * ["a", "b"] == [
                [1, "a"],
                [1, "b"]
        ]
    }

    def "s2 is one size"() {
        expect:
        Cartesian.of([1, 2]) * ["a"] == [
                [1, "a"],
                [2, "a"]
        ]
    }

    def "s1, s2 is one size"() {
        expect:
        Cartesian.of([1]) * ["a"] == [
                [1, "a"]
        ]
    }

    def "s1 is zero size"() {
        expect:
        Cartesian.of([]) * ["a"] == []
    }

    def "s2 is zero size"() {
        expect:
        Cartesian.of([1]) * [] == []
    }

    def "s1, s2 is zero size"() {
        expect:
        Cartesian.of([]) * [] == []
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
