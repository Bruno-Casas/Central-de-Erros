package com.sistemtyse.challenge.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Set;

import com.sistemtyse.challenge.entity.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl implements EventRepositoryCustom {

	@Autowired
	private EntityManager em;

	@Override
	public Page<Event> getFiltredEvents(Set<Map.Entry<String, Object>> filterMap, String orderBy, Pageable pageable) {

		CriteriaQuery<Event> criteriaQuery = buildCriteria(filterMap, orderBy);

		TypedQuery<Event> query = em.createQuery(criteriaQuery);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		return new PageImpl<>(query.getResultList(), pageable, countFiltredEvents(filterMap));
	}


	@Override
	public Page<Event> getFiltredEvents(Set<Entry<String, Object>> filterMap, String orderBy) {
		return getFiltredEvents(filterMap, orderBy, null);
	}

	@Override
	public Page<Event> getFiltredEvents(Set<Entry<String, Object>> filterMap, Pageable pageable) {
		return  getFiltredEvents(filterMap, null, pageable);
	}

	private CriteriaQuery<Event> buildCriteria(Set<Map.Entry<String, Object>> filterMap, String orderBy){

		List<Predicate> predicates = new ArrayList<>();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Event> cq = cb.createQuery(Event.class);
		Root<Event> event = cq.from(Event.class);

		cq.select(event);

		if (filterMap != null) {
			for (Map.Entry<String, Object> entry : filterMap) {
				predicates.add(cb.equal(event.get(entry.getKey()), entry.getValue()));
			}

			cq.where(predicates.toArray(new Predicate[] {}));
		}

		if (orderBy != null) {
			cq.orderBy(cb.asc(event.get(orderBy)));
		}

		return cq;

	}

	private Long countFiltredEvents( Set<Map.Entry<String, Object>> filterMap ) {

		List<Predicate> predicates = new ArrayList<>();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Event> event = cq.from(Event.class);

		cq.multiselect(cb.count(event));

		if (filterMap != null) {
			for (Map.Entry<String, Object> entry : filterMap) {
				predicates.add(cb.equal(event.get(entry.getKey()), entry.getValue()));
			}

			cq.where(predicates.toArray(new Predicate[] {}));
		}

		return em.createQuery(cq).getSingleResult();

	}

}
