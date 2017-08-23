package com.example;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class CompaintQueryObjectUpdater {
    private final ComplaintQueryObjectRepository repository;

    @EventHandler
    public void on(ComplaintFiledEvent event) {
        log.info("repository save, event: {}", event);
        repository.save(new ComplaintQueryObject(event.getId(), event.getCompany(), event.getDescription()));
    }
}
