package com.bochi.fairapi.domain;

import com.bochi.fairapi.domain.Fair;
import com.bochi.fairapi.domain.Fair_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

/**
 * The type Fair specification.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FairSpecification {

    /**
     * District specification.
     *
     * @param district the district
     * @return the specification
     */
    public static Specification<Fair> district(String district) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.district), district);
    }

    /**
     * Region 5 specification.
     *
     * @param region5 the region 5
     * @return the specification
     */
    public static Specification<Fair> region5(String region5) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.region5), region5);
    }

    /**
     * Fair name specification.
     *
     * @param fairName the fair name
     * @return the specification
     */
    public static Specification<Fair> fairName(String fairName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.fairName), fairName);
    }

    /**
     * Neighbourhood specification.
     *
     * @param neighbourhood the neighbourhood
     * @return the specification
     */
    public static Specification<Fair> neighbourhood(String neighbourhood) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Fair_.neighbourhood), neighbourhood);
    }

}
