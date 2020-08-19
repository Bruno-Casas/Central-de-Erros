package com.sistemtyse.challenge.repository;

import com.sistemtyse.challenge.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long>, EventRepositoryCustom {

}