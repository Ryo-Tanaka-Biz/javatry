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

import java.time.LocalTime;


/**
 * @author jflute
 * @author tanaryo
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // done tanaryo [いいね] 横の//すらすらコメントで変数の補足が素晴らしい by jflute (2024/08/15)
    // done tanaryo [いいね] 変数の定義順番、わかりやすくていいですね by jflute (2024/08/15)
    private final int displayPrice;// written on ticket, park guest can watch this
    private Integer dayCount;//dayCountが0の場合入園できない
    private boolean nowAlreadyIn;// trueは入園中を示す
    private boolean nightTerm;//trueは夜限定チケットを示す

    // TODO tanaryo ご自身で思っている通り、唐突にpublicフィールドで公開するのはちょっと危険と思う人が多い by jflute (2024/08/15)
    // テストコード以外の人(mainコード)も、これを使って細工できちゃうので怖い

    // TODO tanaryo staticなものは Attribute よりも上に定義するのがJavaの慣習になっています by jflute (2024/08/22)
    private static final LocalTime nightStartTime = LocalTime.of(17, 0);//夜チケットの入場開始時間
    // done tanaryo 固定のオブジェクトなので、これはstatic finalで定義でOK by jflute (2024/08/15)

    PresentTime presentTime = new DefaultPresentTime();

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, Integer dayCount) {
        this.displayPrice = displayPrice;
        this.dayCount = dayCount;
        if (displayPrice == 7400 && dayCount == 2) {
            nightTerm = true;
        }
    }

    // TODO jflute 1on1にて続きフォロー (2024/08/15)
    // [ふぉろー] publicフィールドよりは、まだsetterの方が管理がしやすい by jflute
    // setの呼び出しを管理することができる: 例えば状態をチェックして例外をthrowしたり、処理を挟み込むことができる
    // publicフィールドは処理の挟み込みが全くできないので、全く無防備
    // とはいえ、これでもmainコードでsetを呼び出すことができちゃうので、ちょっと怖さはなくなってはいない
    //public void setNowTime(LocalTime specifiedTime) {
    //    this.nowTime = specifiedTime;
    //}
    
    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    public void doInPark() {
        if (dayCount == 0) {
            throw new IllegalStateException("This ticket is unavailable: displayedPrice=" + displayPrice);
        }
        // done tanaryo [いいね] nightと関係ないチケットではチェック処理走らないように工夫している、これは良い by jflute (2024/08/15)
        if (nightTerm && presentTime.getPresentTime().isBefore(nightStartTime)) {
            throw new IllegalStateException("17時より前なので入場できません");
        }
        if (nowAlreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        }
        --dayCount;
        nowAlreadyIn = true;
    }
    // done tanaryo 自己レビューでalreadyInの名前がしっくり来ない話 by jflute (2024/08/05)
    // 現時点では、alreadyInが2つの役割を持っている
    //  o nowIn = true;
    //  o alreadyUsedCompletely = true; // これって dayCount == 0 と言える？
    // alreadyIn を nowAlreadyIn にして...チケットを使い切った判定はdayCount == 0にするという選択肢もある
    // ちょっと考えてみましょう

    // ===================================================================================
    //                                                                             Out Park
    //                                                                             =======
    public void doOutPark() {
        if (!nowAlreadyIn) {
            throw new IllegalStateException("Already out park by this ticket: displayedPrice=" + displayPrice);
        }
        // done tanaryo ifの空白が他のコードと合ってない by jflute (2024/08/05)
        // done tanaryo 実質的に支障はあまり無さそうだが、最終日だけ帰った後nowAlreadyInがtrueのまんまが変というか必要あるか？ by jflute (2024/08/15)
        // nowAlreadyInは、現在パーク内に入ってるかどうか？を純粋に表現する変数というイメージで作っていると思うので

        nowAlreadyIn = false;
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
        return nowAlreadyIn;
    }
}
