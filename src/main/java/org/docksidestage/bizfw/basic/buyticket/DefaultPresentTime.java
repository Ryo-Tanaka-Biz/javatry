package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * 現在時刻を取得するクラス。
 * @author tanaryo
 */
public class DefaultPresentTime implements PresentTime {
    @Override
    public LocalTime getPresentTime() {
        return LocalTime.now();
    }
}
