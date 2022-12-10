package com.bochi.fairapi.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * The Fair specifications.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FairSpecification {

    /**
     * district specification.
     *
     * @param district the district
     * @return the specification
     */
    public static Specification<Fair> district(String district) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.district), district);
    }

    /**
     * region5 specification.
     *
     * @param region5 the region5
     * @return the specification
     */
    public static Specification<Fair> region5(String region5) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.region5), region5);
    }

    /**
     * fairName specification.
     *
     * @param fairName the fair name
     * @return the specification
     */
    public static Specification<Fair> fairName(String fairName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.fairName), fairName);
    }

    /**
     * neighbourhood specification.
     *
     * @param neighbourhood the neighbourhood
     * @return the specification
     */
    public static Specification<Fair> neighbourhood(String neighbourhood) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.neighbourhood), neighbourhood);
    }

}
