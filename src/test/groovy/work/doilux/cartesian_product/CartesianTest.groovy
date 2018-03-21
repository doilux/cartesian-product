package work.doilux.cartesian_product

import spock.lang.Specification

class CartesianTest extends Specification {


    def "multiple size"() {
        expect:
        def act1 = Cartesian.of([1, 2] as Set).resolve(["a", "b"] as Set)

        act1 == [
                [1, "a"],
                [1, "b"],
                [2, "a"],
                [2, "b"]
        ] as Set

        def act2 = Cartesian.of(act1).resolve(["C", "D"] as Set)

        act2 == [
                [1, "a", "C"],
                [1, "b", "C"],
                [1, "a", "D"],
                [1, "b", "D"],
                [2, "a", "C"],
                [2, "b", "C"],
                [2, "a", "D"],
                [2, "b", "D"]
        ] as Set
    }

    def "s1 is one size"() {
        expect:
        Cartesian.of([1] as Set).resolve(["a", "b"] as Set) == [
                [1, "a"],
                [1, "b"]
        ] as Set
    }

    def "s2 is one size"() {
        expect:
        Cartesian.of([1, 2] as Set).resolve(["a"] as Set) == [
                [1, "a"],
                [2, "a"]
        ] as Set
    }

    def "s1, s2 is one size"() {
        expect:
        Cartesian.of([1] as Set).resolve(["a"] as Set) == [
                [1, "a"]
        ] as Set
    }

    def "s1 is zero size"() {
        expect:
        Cartesian.of([] as Set).resolve(["a"] as Set) == [
                []
        ] as Set
    }

    def "s2 is zero size"() {
        expect:
        Cartesian.of([1] as Set).resolve([] as Set) == [
                []
        ] as Set
    }

    def "s1, s2 is zero size"() {
        expect:
        Cartesian.of([] as Set).resolve([] as Set) == [
                []
        ] as Set
    }

    def "s1 is null"() {
        when:
        Cartesian.of(null).resolve([] as Set)

        then:
        thrown(NullPointerException)
    }

    def "s2 is null"() {
        when:
        Cartesian.of([] as Set).resolve(null)

        then:
        thrown(NullPointerException)
    }
}
