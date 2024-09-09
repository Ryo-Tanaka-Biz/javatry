package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * 意図的に変更した現在時刻を取得するクラス。
 * @author tanaryo
 */
public class VariablePresentTime implements PresentTime {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO tanaryo startという単語はTicketの中で使っているもので、ここは独立領域なので "意図的に変更した" のニュアンスがあるといい by jflute (2024/09/09)
    public final LocalTime startTime;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public VariablePresentTime(LocalTime startTime){
        this.startTime = startTime;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    @Override
    public LocalTime getPresentTime() {
        return startTime;
    }
}
