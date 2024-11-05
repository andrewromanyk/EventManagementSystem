package ua.edu.ukma.event_management_system.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.edu.ukma.event_management_system.controller.TicketController;
import ua.edu.ukma.event_management_system.controller.UserController;
import ua.edu.ukma.event_management_system.dto.TicketDto;
import ua.edu.ukma.event_management_system.exceptions.EventFullException;

@Aspect
@Component
public class ExceptionAOP {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAOP.class);

    @Pointcut(value = "execution(* ua.edu.ukma.event_management_system.controller.TicketController.createTicket(..))")
    private void createTicket() {}

    @AfterThrowing(
            pointcut = "createTicket() && args(ticket)",
            throwing = "exception",
            argNames = "exception, ticket")
    public void catchFullEvent(EventFullException exception, TicketDto ticket) {
        logger.error("Could not create ticket for User(id={}, name={}) and Event(id={}, name={}) because {}",
                ticket.getUser().getId(), ticket.getUser().getUsername(),
                ticket.getEvent().getId(), ticket.getEvent().getEventTitle(),
                exception.getMessage());
    }
}
