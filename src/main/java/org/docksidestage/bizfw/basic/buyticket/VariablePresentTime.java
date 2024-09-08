package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * 意図的に変更した現在時刻を取得するクラス。
 * @author tanaryo
 */
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
