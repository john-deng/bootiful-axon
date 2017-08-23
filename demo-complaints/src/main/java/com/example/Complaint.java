package com.example;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Complaint {
    @AggregateIdentifier
    private String complaintId;

    @CommandHandler
    public Complaint(FileComplaintCommand cmd) {
        String id = UUID.randomUUID().toString();
        log.info("on command handler, cmd: {}", cmd);
        apply(new ComplaintFiledEvent(id, cmd.getCompany(), cmd.getDescription()));
    }

    @EventSourcingHandler
    public void on(ComplaintFiledEvent event) {
        log.info("on event sourcing handler, event: {}", event);
        this.complaintId = event.getId();
    }
}
