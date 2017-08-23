package com.example;

import cn.vpclub.moses.utils.common.IdWorker;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@Slf4j
public class Complaint {
    @AggregateIdentifier
    private String complaintId;

    @CommandHandler
    public Complaint(FileComplaintCommand cmd) {
        Long id = IdWorker.getId();
        log.info("on command handler, cmd: {}", cmd);
        apply(new ComplaintFiledEvent(id.toString(), cmd.getCompany(), cmd.getDescription()));
    }

    @EventSourcingHandler
    public void on(ComplaintFiledEvent event) {
        log.info("on event sourcing handler, event: {}", event);
        this.complaintId = event.getId();
    }
}
