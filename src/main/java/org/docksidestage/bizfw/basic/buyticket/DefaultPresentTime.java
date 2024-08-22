package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

public class DefaultPresentTime implements PresentTime {
    @Override
    public LocalTime getPresentTime() {
        return LocalTime.now();
    }
}
