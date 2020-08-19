package com.sistemtyse.challenge.repository;

import java.util.Map;
import java.util.Set;

import com.sistemtyse.challenge.entity.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventRepositoryCustom {

	Page<Event> getFiltredEvents(Set<Map.Entry<String, Object>> filterParameters, String orderBy, Pageable pageable);

	Page<Event> getFiltredEvents(Set<Map.Entry<String, Object>> filterParameters, String orderBy);

	Page<Event> getFiltredEvents(Set<Map.Entry<String, Object>> filterParameters,Pageable pageable);
	
}