package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Zombie;

// TODO done tanaryo 名前のお作法として、ZombieBarkingProcess がオーソドックスではあるかな by jflute (2024/11/28)
// 「Forなんちゃら」は最終手段みたいな感じかも。

/**
 * @author tanaryo
 */
public class ZombieBarkingProcess extends BarkingProcess {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Zombie zombie;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    // TODO done tanaryo もう、Zombie専用のBarkingProcessなので、Zombieしか受け取らないようにした方が安全 by jflute (2024/11/28)
    // 間違えて new Dog() を引数にしちゃう人もいるかもしれない。すると、ClassCastException が発生する。
    public ZombieBarkingProcess(Zombie zombie) {
        super(zombie);//animalはZombieインスタンス
        // TODO done tanaryo super.じゃ無くていいかな。superに保存したanimalから導出する必要がない、引数のものでOK by jflute (2024/11/28)
        this.zombie = zombie;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected void breatheIn() {
        super.breatheIn();
        // [1on1でのフォロー] 仮に Animal や BarkingProcess がフレームワークの中に入ってるクラスだとしても...
        // こういう方法で処理のカスタマイズができるってことがオブジェクト指向の冥利。
        zombie.getZombieDiary().countBreatheIn();
    }
}
