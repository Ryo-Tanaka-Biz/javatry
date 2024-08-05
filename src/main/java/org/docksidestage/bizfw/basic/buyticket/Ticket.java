/*
 * Copyright 2019-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

/**
 * @author jflute
 * @author tanaryo
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final int displayPrice;// written on ticket, park guest can watch this
    private Integer dayCount;
    private boolean alreadyIn; // true means this ticket is unavailable

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, Integer dayCount) {
        this.displayPrice = displayPrice;
        this.dayCount = dayCount;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        --dayCount;
        alreadyIn = true;
    }
    // TODO tanaryo 自己レビューでalreadyInの名前がしっくり来ない話 by jflute (2024/08/05)
    // 現時点では、alreadyInが2つの役割を持っている
    //  o nowIn = true;
    //  o alreadyUsedCompletely = true; // これって dayCount == 0 と言える？
    // alreadyIn を nowAreadyIn にして...チケットを使い切った判定はdayCount == 0にするという選択肢もある
    // ちょっと考えてみましょう

    // ===================================================================================
    //                                                                             Out Park
    //                                                                             =======
    public void doOutPark() {
        if (!alreadyIn) {
            throw new IllegalStateException("Already out park by this ticket: displayedPrice=" + displayPrice);
        }
        // TODO tanaryo ifの空白が他のコードと合ってない by jflute (2024/08/05)
        if(dayCount >= 1){
            alreadyIn = false;
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getDisplayPrice() {
        return displayPrice;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
