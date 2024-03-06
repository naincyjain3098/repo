package com.springboot.assignment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.assignment.model.Ticket;
import com.springboot.assignment.model.User;
import com.springboot.assignment.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    private User user = new User();
    
    @GetMapping("/test")
    public ResponseEntity<String> test() {
    	 return ResponseEntity.status(HttpStatus.OK).body("Working");
    }

    @PostMapping("/purchase") 
    public ResponseEntity<Ticket> purchaseTicket(@RequestParam String from, @RequestParam String to,
                                 @RequestParam String userName, @RequestParam String userEmail,
                                 @RequestParam double pricePaid,  @RequestParam int seatNumber,
                                 @RequestParam String section) {
        Ticket ticketDetails = ticketService.purchaseTicket(from, to, userName, userEmail, pricePaid, seatNumber, section);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketDetails);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> TicketsBooked =  ticketService.getAllTickets();
        return ResponseEntity.status(HttpStatus.OK).body(TicketsBooked);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getTicketByUserName(@PathVariable String userName) {

        Ticket data = ticketService.getTicketByUserName(userName);
        
        if(data==null)
        {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        user.setUserEmail(data.getUserEmail());
        user.setUserName(data.getUserName());
        return ResponseEntity.status(HttpStatus.OK).body(user);
      
        
    }

    @DeleteMapping("/remove/{userName}")
    public ResponseEntity<String> removeTicket(@PathVariable String userName) {
        String status =ticketService.removeTicket(userName);
        if(status.equals("Not Found"))
        {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Ticket details Not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PutMapping("/modify-seat/{userName}/{newSeatNumber}")
    public ResponseEntity<Ticket> modifySeat(@PathVariable String userName, @PathVariable int newSeatNumber) {
    Ticket	ticketDetails =  ticketService.modifySeat(userName, newSeatNumber);
    if(ticketDetails ==null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    
        return ResponseEntity.status(HttpStatus.OK).body(ticketDetails);
    }
}



