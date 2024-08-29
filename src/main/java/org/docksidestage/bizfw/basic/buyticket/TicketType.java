package org.docksidestage.bizfw.basic.buyticket;

public enum TicketType {
    ONE_ALL_DAY(1, 7400, "09:00-22:00"),
    TWO_ALL_DAY(2, 13200, "09:00-22:00"),
    TWO_NIGHT_DAY(2, 7400, "18:00-22:00"),
    FOUR_ALL_DAY(4, 22400, "09:00-22:00"),;

    private final int dayCount;
    private final int price;
    private final String timeSlot;


    TicketType(int dayCount, int price, String timeSlot ) {
        this.dayCount = dayCount;
        this.price = price;
        this.timeSlot = timeSlot;
    }

    public int getDayCount() {
        return dayCount;
    }

    public int getPrice() {
        return price;
    }

    public String getTimeSlot() {
        return timeSlot;
    }



}
