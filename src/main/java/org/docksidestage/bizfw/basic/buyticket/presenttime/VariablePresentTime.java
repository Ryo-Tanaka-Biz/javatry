package org.docksidestage.bizfw.basic.buyticket.presenttime;

import java.time.LocalTime;

/**
 * 意図的に変更した現在時刻を取得するクラス。
 * @author tanaryo
 */
public class VariablePresentTime implements PresentTime {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done tanaryo startという単語はTicketの中で使っているもので、ここは独立領域なので "意図的に変更した" のニュアンスがあるといい by jflute (2024/09/09)
    public final LocalTime intentionalChangedTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public VariablePresentTime(LocalTime intentionalChangedTime){
        this.intentionalChangedTime = intentionalChangedTime;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public LocalTime getPresentTime() {
        return intentionalChangedTime;
    }
}
