package com.example;

import cn.vpclub.moses.core.constant.MessageCodeConstant;
import cn.vpclub.moses.core.model.response.Response;
import cn.vpclub.moses.utils.validator.AttributeValidatorException;
import cn.vpclub.moses.web.controller.AbstractController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/")
public class ComplaintController extends AbstractController {
    private final ComplaintQueryObjectRepository repository;
    private final CommandGateway commandGateway;

    @PostMapping("/complaints")
    @ResponseBody
    public Response fileComplaint(@RequestBody FileComplaintCommand cmd) {

        Response response = new Response();

        try {
            cmd.validate("complaint");

            log.info("commandGateway send cmd: {}", cmd);
            CompletableFuture<String> future = commandGateway.send(cmd);
            log.info("commandGateway is sent, result: {}", future);

            response.setReturnCode(1000);
            response.setMessage(MessageCodeConstant.MESSAGE_COMMON_SUCCESS);
        } catch (AttributeValidatorException e) {
            log.error("{}", e.getMessage());
            response.setReturnCode(1006);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @PostMapping("/complaints-async")
    @ResponseBody
    public CompletableFuture<String> fileComplaintAsync(@RequestBody FileComplaintCommand cmd) {

        CompletableFuture<String> future = null;

        try {
            cmd.validate("complaint");

            log.info("commandGateway send cmd: {}", cmd);
            future = commandGateway.send(cmd);
            log.info("commandGateway is sent, result: {}", future);
        } catch (AttributeValidatorException e) {
            log.error("{}", e.getMessage());
        }

        return future;
    }

    @GetMapping("/find")
    public List<ComplaintQueryObject> findAll() {
        return repository.findAll();
    }

    @GetMapping("/find/{id}")
    public ComplaintQueryObject find(@PathVariable String id) {
        return repository.findOne(id);
    }
}