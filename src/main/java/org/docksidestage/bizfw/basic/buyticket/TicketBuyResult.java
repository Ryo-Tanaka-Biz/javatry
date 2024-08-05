package org.docksidestage.bizfw.basic.buyticket;

// TODO tanaryo タグコメントの上 (クラス宣言の直下) に一行空行を by jflute (2024/08/05)
/**
 * @author tanaryo
 */
public class TicketBuyResult {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // TODO tanaryo daycountがfinal付いてない理由がない by jflute (2024/08/05)
    // TODO tanaryo ここではdaycountだけどTicketではdayCountなので一貫性がない by jflute (2024/08/05)
    private final int displayPrice; // written on ticket, park guest can watch this
    private final int change;
    private Integer daycount;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBuyResult(int displayPrice, int change, int daycount) {
        this.displayPrice = displayPrice;
        this.change = change;
        this.daycount = daycount;
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public Ticket getTicket() {
        return new Ticket(displayPrice, daycount);
    }

    public int getChange(){
        return change;
    }
}
