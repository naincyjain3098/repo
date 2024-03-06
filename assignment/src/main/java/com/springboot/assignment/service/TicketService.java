package com.springboot.assignment.service;


import com.springboot.assignment.model.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;




@Service
public class TicketService {
   private List<Ticket> tickets =  new ArrayList<>();
  
 
  
    
	 @CachePut(value = "tickets", key = "#userName")
	    public Ticket purchaseTicket(String from, String to, String userName, String userEmail, double pricePaid, int seatNumber, String section) {
	       
		  Ticket ticket = new Ticket();
	        ticket.setFrom(from);
	        ticket.setTo(to);
	        ticket.setUserName(userName);
	        ticket.setUserEmail(userEmail);
	        ticket.setPricePaid(pricePaid);
	        ticket.setSeatNumber(seatNumber);
	        ticket.setSection(section);
	        tickets.add(ticket);
            return ticket;
	    }

    
    public List<Ticket> getAllTickets() {
       return tickets;
        
    }


    @Cacheable(value = "tickets", key = "#userName")
    public Ticket getTicketByUserName(String userName) {
   Ticket  data =tickets.stream().filter(t -> t.getUserName().equals(userName)).findFirst().orElse(null);
    
    return data;
       
    }

    @CacheEvict(value = "tickets", key = "#userName")
    public String removeTicket(String userName) {
    	
    	 List<Ticket> updatedTickets = tickets.stream()
                 .filter(t -> !(t.getUserName().equals(userName)))
                 .collect(Collectors.toList());

         if (updatedTickets.size() == tickets.size()) {
             return("Not Found");
         } else {
             tickets = updatedTickets;
             return("Ticket with username " + userName + " removed successfully.");
         }
    }

    @CachePut(value = "tickets", key = "#userName")
    public Ticket modifySeat(String userName, int newSeatNumber) {
    	Ticket ticket =  getTicketByUserName(userName);
        if (ticket != null) {
            ticket.setSeatNumber(newSeatNumber);
            // Return the modified ticket to update the cache
            return ticket;
        } else {
            // Return null or throw an exception if the ticket is not found
            return null;
        }
    }
}

