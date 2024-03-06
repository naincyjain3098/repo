package com.springboot.assignment.controller;


import com.springboot.assignment.model.Ticket;
import com.springboot.assignment.service.TicketService;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(TicketController.class)
class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    @Test
    void testTestEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tickets/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Working"));
    }

    @Test
    void testPurchaseTicketEndpoint() throws Exception {
        Ticket ticket = new Ticket("JamNagar", "Mumbai", "User1", "user1@example.com", 25.0, "A",1);

        Mockito.when(ticketService.purchaseTicket(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyDouble(), Mockito.anyInt(), Mockito.anyString()))
                .thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/tickets/purchase")
                .param("from", "JamNagar")
                .param("to", "Mumbai")
                .param("userName", "User1")
                .param("userEmail", "user1@example.com")
                .param("pricePaid", "25.0")
                .param("seatNumber", "1")
                .param("section", "A"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("User1"));
    }

    @Test
    void testGetAllTicketsEndpoint() throws Exception {
        List<Ticket> tickets = Arrays.asList(
        		new Ticket("JamNagar", "Mumbai", "User1", "user1@example.com", 25.0, "A",1),
                new Ticket("From2", "To2", "User2", "user2@example.com", 25.0,"B", 2)
        );

        Mockito.when(ticketService.getAllTickets()).thenReturn(tickets);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tickets/all"))
                .andExpect(MockMvcResultMatchers.status().isOk());
               
    }
//
    @Test
    void testGetTicketByUserNameEndpoint() throws Exception {
        String userName = "User1";
        Ticket ticket = new Ticket("JamNagar", "Mumbai", "User1", "user1@example.com", 25.0, "A",1);

        Mockito.when(ticketService.getTicketByUserName(userName)).thenReturn(ticket);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/tickets/user/{userName}", userName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value(userName));
    }

    @Test
    void testRemoveTicketEndpoint() throws Exception {
        String userName = "User1";
        String status = "Ticket with username " + userName + " removed successfully.";

        Mockito.when(ticketService.removeTicket(userName)).thenReturn(status);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/tickets/remove/{userName}", userName))
                .andExpect(MockMvcResultMatchers.content().string(status));
    }

    @Test
    void testModifySeatEndpoint() throws Exception {
        String userName = "User1";
        int newSeatNumber = 1;
      
           Mockito.when(ticketService.modifySeat(userName, newSeatNumber)).thenReturn(null);
           mockMvc.perform(MockMvcRequestBuilders.put("/api/tickets/modify-seat/{userName}/{newSeatNumber}", userName, newSeatNumber))
        		.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
