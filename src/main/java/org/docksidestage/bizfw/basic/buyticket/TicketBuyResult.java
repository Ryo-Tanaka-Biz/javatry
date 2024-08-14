package org.docksidestage.bizfw.basic.buyticket;

// TODO tanaryo タグコメントの上 (クラス宣言の直下) に一行空行を by jflute (2024/08/05)
/**
 * @author tanaryo
 */
public class TicketBuyResult {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done tanaryo daycountがfinal付いてない理由がない by jflute (2024/08/05)
    // TODO tanaryo ここではdaycountだけどTicketではdayCountなので一貫性がない by jflute (2024/08/05)
    private final int displayPrice; // written on ticket, park guest can watch this
    private final int change;
    private final Integer dayCount;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(int displayPrice, int change, int dayCount) {
        this.displayPrice = displayPrice;
        this.change = change;
        this.dayCount = dayCount;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return new Ticket(displayPrice, dayCount);
    }

    public int getChange(){
        return change;
    }
}
