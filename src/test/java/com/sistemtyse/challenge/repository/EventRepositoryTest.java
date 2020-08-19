package com.sistemtyse.challenge.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.sistemtyse.challenge.entity.Event;
import com.sistemtyse.challenge.entity.LevelEnum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testGetFiltredEventList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Map<String, Object> entryList = new HashMap<>();
        entryList.put("system", "desktop");
        entryList.put("description", "Test description group");
        entryList.put("level", LevelEnum.ERROR);
        entryList.put("createdAt", sdf.parse("20/06/2016"));

        Page<Event> events = eventRepository.getFiltredEvents(entryList.entrySet(), PageRequest.of(0,10));
        
        assertEquals(2, events.getContent().size());
    }

    @Test
    public void testGetPagedAndFiltredEventList() throws ParseException {

        Map<String, Object> entryList = new HashMap<>();
        entryList.put("description", "Test description group");

        Page<Event> events = eventRepository.getFiltredEvents(entryList.entrySet(), PageRequest.of(0,4));

        assertEquals(5, events.getTotalElements());
        assertEquals(2, events.getTotalPages());
        assertEquals(4, events.getNumberOfElements());
    }

    @Test
    public void testGetSortedAndFiltredEventList() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      
        Map<String, Object> entryList = new HashMap<>();
        entryList.put("description", "Test description group");

        Page<Event> events = eventRepository.getFiltredEvents(entryList.entrySet(), "createdAt", PageRequest.of(0,5));

        Event firstEvent = events.getContent().get(0);
        Event lastEvent = events.getContent().get(4);

        assertEquals(sdf.parse("20/06/2016") , firstEvent.getCreatedAt());
        assertEquals(sdf.parse("29/06/2016") , lastEvent.getCreatedAt());
    }
 
}