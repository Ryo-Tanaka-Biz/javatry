package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

public class VariablePresentTime implements PresentTime {

    public final LocalTime startTime;

    public VariablePresentTime(LocalTime startTime){
        this.startTime = startTime;
    }

    @Override
    public LocalTime getPresentTime() {
        return startTime;
    }
}
