package org.docksidestage.bizfw.basic.buyticket.presenttime;

import java.time.LocalTime;

// done tanaryo PresentTime系はTicketなんちゃらと質がだいぶ違うのでpackage分けしてみよう by jflute (2024/09/09)
/**
 * 現在時刻を取得するクラス。
 * @author tanaryo
 */
public class DefaultPresentTimeManager implements PresentTimeManager {
    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public LocalTime getPresentTime() {
        return LocalTime.now();
    }
}
