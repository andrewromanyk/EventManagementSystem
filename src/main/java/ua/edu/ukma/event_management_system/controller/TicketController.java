package ua.edu.ukma.event_management_system.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.event_management_system.domain.Ticket;
import ua.edu.ukma.event_management_system.domain.User;
import ua.edu.ukma.event_management_system.dto.TicketDto;
import ua.edu.ukma.event_management_system.dto.UserDto;
import ua.edu.ukma.event_management_system.exceptions.EventFullException;
import ua.edu.ukma.event_management_system.service.interfaces.EventService;
import ua.edu.ukma.event_management_system.service.interfaces.TicketService;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    private ModelMapper modelMapper;
    private TicketService ticketService;

    @Autowired
    public void setModelWrapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setEventService(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable long id) {
        return toDto(ticketService.getTicketById(id));
    }

    @GetMapping("/")
    public List<TicketDto> getTickets() {
        return ticketService.getAllTickets()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @PostMapping("/")
    public void createTicket(@RequestBody @Valid TicketDto ticketDto) {
        if (ticketDto.getEvent().getUsers().size() >= ticketDto.getEvent().getNumberOfTickets()){
            throw new EventFullException("Event is full!");
        }
        ticketService.createTicket(ticketDto);
    }

    @PutMapping("/{id}")
    public void updateTicket(@PathVariable long id, @RequestBody @Valid TicketDto ticketDto) {
        ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable long id) {
        ticketService.removeTicket(id);
    }

    private TicketDto toDto(Ticket ticket) {
        return modelMapper.map(ticket, TicketDto.class);
    }
}
