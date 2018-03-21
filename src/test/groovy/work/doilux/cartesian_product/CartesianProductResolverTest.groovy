package work.doilux.cartesian_product

import spock.lang.Specification

class CartesianProductResolverTest extends Specification {


    def "multiple size"() {
        expect:
        def act1 = CartesianProductResolver.resolve([1, 2] as Set, ["a", "b"] as Set)

        act1 == [
                [1, "a"],
                [1, "b"],
                [2, "a"],
                [2, "b"]
        ] as Set

        def act2 = CartesianProductResolver.resolve(act1, ["C", "D"] as Set)

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
        CartesianProductResolver.resolve([1] as Set, ["a", "b"] as Set) == [
                [1, "a"],
                [1, "b"]
        ] as Set
    }

    def "s2 is one size"() {
        expect:
        CartesianProductResolver.resolve([1, 2] as Set, ["a"] as Set) == [
                [1, "a"],
                [2, "a"]
        ] as Set
    }

    def "s1, s2 is one size"() {
        expect:
        CartesianProductResolver.resolve([1] as Set, ["a"] as Set) == [
                [1, "a"]
        ] as Set
    }

    def "s1 is zero size"() {
        expect:
        CartesianProductResolver.resolve([] as Set, ["a"] as Set) == [
                ["a"]
        ] as Set
    }

    def "s2 is zero size"() {
        expect:
        CartesianProductResolver.resolve([1] as Set, [] as Set) == [
                [1]
        ] as Set
    }

    def "s1, s2 is zero size"() {
        expect:
        CartesianProductResolver.resolve([] as Set, [] as Set) == [
                []
        ] as Set
    }

    def "s1 is null"() {
        when:
        CartesianProductResolver.resolve(null, [] as Set)

        then:
        thrown(NullPointerException)
    }

    def "s2 is null"() {
        when:
        CartesianProductResolver.resolve([] as Set, null)

        then:
        thrown(NullPointerException)
    }
}
