package com.uniloftsky.spingframework.spring5advertismentservice.filter;

import com.uniloftsky.spingframework.spring5advertismentservice.model.Advertisement;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class AdvertisementCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public AdvertisementCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Advertisement> findAllWithFilters(AdvertisementPage advertisementPage,
                                                  AdvertisementSearchCriteria advertisementSearchCriteria) {
        CriteriaQuery<Advertisement> criteriaQuery = criteriaBuilder.createQuery(Advertisement.class);
        Root<Advertisement> advertisementRoot = criteriaQuery.from(Advertisement.class);
        Predicate predicate = getPredicate(advertisementSearchCriteria, advertisementRoot);
        criteriaQuery.where(predicate);
        setOrder(advertisementPage, criteriaQuery, advertisementRoot);

        TypedQuery<Advertisement> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(advertisementPage.getPageNumber() * advertisementPage.getPageSize());
        typedQuery.setMaxResults(advertisementPage.getPageSize());

        Pageable pageable = getPageable(advertisementPage);

        long itemsCount = getEmployeesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, itemsCount);
    }

    private Predicate getPredicate(AdvertisementSearchCriteria advertisementSearchCriteria,
                                   Root<Advertisement> advertisementRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(advertisementSearchCriteria.getCategory())) {
            predicates.add(
                    criteriaBuilder.like(advertisementRoot.get("category").get("name"),
                            "%" + advertisementSearchCriteria.getCategory().getName() + "%")
            );
        }
        if (Objects.nonNull(advertisementSearchCriteria.getRegion())) {
            predicates.add(
                    criteriaBuilder.like(advertisementRoot.get("city").get("region").get("name"),
                            "%" + advertisementSearchCriteria.getRegion().getName() + "%")
            );
        }
        if (Objects.nonNull(advertisementSearchCriteria.getCity())) {
            predicates.add(
                    criteriaBuilder.like(advertisementRoot.get("city").get("name"),
                            "%" + advertisementSearchCriteria.getCity().getName() + "%")
            );
        }
        if (Objects.nonNull(advertisementSearchCriteria.getKeyword())) {
            predicates.add(criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(advertisementRoot.get("name")), "%" + advertisementSearchCriteria.getKeyword().toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(advertisementRoot.get("description")), "%" + advertisementSearchCriteria.getKeyword().toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(advertisementRoot.get("responsibilities")), "%" + advertisementSearchCriteria.getKeyword().toLowerCase() + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(advertisementRoot.get("qualifications")), "%" + advertisementSearchCriteria.getKeyword().toLowerCase() + "%"))
            );
        }
        if (Objects.nonNull(advertisementSearchCriteria.getCheckSalary())) {
            if (Objects.nonNull(advertisementSearchCriteria.getMaxPrice())) {
                if (advertisementRoot.get("salary") != null) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(advertisementRoot.get("salary"),
                            advertisementSearchCriteria.getMaxPrice())
                    );
                }
            }
            if (Objects.nonNull(advertisementSearchCriteria.getMinPrice())) {
                if (advertisementRoot.get("salary") != null) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(advertisementRoot.get("salary"),
                            advertisementSearchCriteria.getMinPrice())
                    );
                }
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(AdvertisementPage advertisementPage,
                          CriteriaQuery<Advertisement> criteriaQuery,
                          Root<Advertisement> advertisementRoot) {
        if (advertisementPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(advertisementRoot.get(advertisementPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(advertisementRoot.get(advertisementPage.getSortBy())));
        }
    }

    private Pageable getPageable(AdvertisementPage advertisementPage) {
        Sort sort = Sort.by(advertisementPage.getSortDirection(), advertisementPage.getSortBy());
        return PageRequest.of(advertisementPage.getPageNumber(), advertisementPage.getPageSize(), sort);
    }

    private long getEmployeesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Advertisement> countRoot = countQuery.from(Advertisement.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }

}
