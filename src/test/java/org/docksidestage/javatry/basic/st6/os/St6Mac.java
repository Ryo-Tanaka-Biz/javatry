package org.docksidestage.javatry.basic.st6.os;

import org.docksidestage.javatry.basic.st6.os.st6OperationSystem.St6OperationSystem;

/**
 * @author tanaryo
 */
public class St6Mac extends St6OperationSystem {
    // done tanaryo osTypeズレる問題は？選択肢A,Bを出してもらった、Aの延長のCは？ by jflute (2024/11/15)
    // A. そもそもコンストラクターで受け取らず、newされたら固定のosTypeを自分でsetする
    // B. バリデーションでズレチェック
    // C. "A"の続きで、もうosTypeの唯一の目的であるバリデーションが不要なのでosType自体が不要
    // 定数osTypeを削除し、コンストラクタの引数はloginIdのみにする

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public St6Mac(String loginId) {
        super(loginId);
    }

    // ===================================================================================
    //                                                                      User Directory
    //                                                                      ==============
    // done tanaryo [教訓] 時間が経つと、絶対に忘れないだろうと思ってたものも忘れる by jflute (2024/10/31)
    // なので、todoコメントがある、ひとこと「やりかけ」書くだけでも全然違う (もちろん詳細もあったらいいけど)
    // (高度なお仕事をどんどんしていくうえで、こういう意識は大事になる)
    @Override
    protected String getFileSeparator() {
        return "/";
    }

    @Override
    protected String getUserDirectory() {
        return "/Users/" + loginId;
    }
}
