package org.docksidestage.bizfw.basic.buyticket;
/**
 * @author tanaryo
 */
public class TicketBuyResult {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
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
