package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

import org.apache.tomcat.jni.Local;

public enum TicketType {
    ONE_ALL_DAY(1, 7400, LocalTime.of(9, 0), LocalTime.of(22, 0)),
    TWO_ALL_DAY(2, 13200, LocalTime.of(9, 0), LocalTime.of(22, 0)),
    TWO_NIGHT_DAY(2, 7400, LocalTime.of(18, 0), LocalTime.of(22, 0)),
    FOUR_ALL_DAY(4, 22400, LocalTime.of(9, 0), LocalTime.of(22, 0)),;

    private final int dayCount;
    private final int price;
    private final LocalTime startTime;
    private final LocalTime endTime;

    TicketType(int dayCount, int price, LocalTime startTime, LocalTime endTime) {
        this.dayCount = dayCount;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDayCount() {
        return dayCount;
    }

    public int getPrice() {
        return price;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
