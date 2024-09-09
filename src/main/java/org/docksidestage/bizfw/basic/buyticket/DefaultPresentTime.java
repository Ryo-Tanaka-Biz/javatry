package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

// TODO tanaryo PresentTime系はTicketなんちゃらと質がだいぶ違うのでpackage分けしてみよう by jflute (2024/09/09)
/**
 * 現在時刻を取得するクラス。
 * @author tanaryo
 */
public class DefaultPresentTime implements PresentTime {
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public LocalTime getPresentTime() {
        return LocalTime.now();
    }
}
