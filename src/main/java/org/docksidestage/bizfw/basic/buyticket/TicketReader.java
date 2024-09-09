package org.docksidestage.bizfw.basic.buyticket;

import java.time.LocalTime;

/**
 * 入園情報と退園情報を管理するクラス
 * @author tanaryo
 */
public class TicketReader {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final TicketType ticketType;

    private Integer leftDays;//が0の場合入園できない
    private boolean nowAlreadyIn;// trueは入園中を示す
    private LocalTime inTime;//は入園した時間を示す
    private LocalTime outTime;//は退園した時間を示す

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    /**
     * 1回目の入園時に作成する
     */
    public TicketReader(Ticket ticket) {
        this.ticketType = ticket.getTicketType();
        this.leftDays = ticketType.getDayCount();
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark(PresentTime presentTime) {
        if (leftDays == 0) {
            throw new IllegalStateException("This ticket is unavailable: leftDays=" + leftDays);
        }
        if (presentTime.getPresentTime().isBefore(ticketType.getStartTime())) {
            throw new IllegalStateException("入場可能時間より前なので入場できません: (presentTime, startTime) = (" + presentTime.getPresentTime() + "," + ticketType.getStartTime() + ")");
        }
        if (nowAlreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: inTime=" + inTime);
        }
        --leftDays;
        nowAlreadyIn = true;
        inTime = presentTime.getPresentTime();
    }

    // ===================================================================================
    //                                                                             Out Park
    //                                                                             =======
    public void doOutPark(PresentTime presentTime) {
        if (!nowAlreadyIn) {
            throw new IllegalStateException("Already out park by this ticket: outTime=" +outTime);
        }
        // done tanaryo ifの空白が他のコードと合ってない by jflute (2024/08/05)
        // done tanaryo 実質的に支障はあまり無さそうだが、最終日だけ帰った後nowAlreadyInがtrueのまんまが変というか必要あるか？ by jflute (2024/08/15)
        // nowAlreadyInは、現在パーク内に入ってるかどうか？を純粋に表現する変数というイメージで作っていると思うので
        nowAlreadyIn = false;
        outTime = presentTime.getPresentTime();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public boolean isAlreadyIn() {
        return nowAlreadyIn;
    }

    public LocalTime getInTime() {
        return inTime;//入園時間を上書きしてしまっているので、1回目入場と2回目入場をどちらも記録できたら良い
    }

    public LocalTime getOutTime() {
        return outTime;
    }
}
