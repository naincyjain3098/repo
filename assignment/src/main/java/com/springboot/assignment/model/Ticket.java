package com.springboot.assignment.model;





public class Ticket {
    private String from;
    private String to;
    private String userName;
    private String userEmail;
    private double pricePaid;
    private String section;
    private int seatNumber;

    // Constructors

    public Ticket() {
    }

    public Ticket(String from, String to, String userName, String userEmail, double pricePaid, String section, int seatNumber) {
        this.from = from;
        this.to = to;
        this.userName = userName;
        this.userEmail = userEmail;
        this.pricePaid = pricePaid;
        this.section = section;
        this.seatNumber = seatNumber;
    }

    // Getters and Setters

  

	public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}

