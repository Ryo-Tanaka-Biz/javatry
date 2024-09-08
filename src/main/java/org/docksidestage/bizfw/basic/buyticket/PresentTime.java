package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * 現在時刻を取得するインターフェース。
 * @author tanaryo
 */
public interface PresentTime {
    public LocalTime getPresentTime();
}
