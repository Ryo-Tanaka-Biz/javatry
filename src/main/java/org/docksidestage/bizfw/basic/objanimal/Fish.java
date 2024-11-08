package org.docksidestage.bizfw.basic.objanimal;

// done tanaryo javadocお願いします by jflute (2024/10/31)

/**
 * The object for fish(魚).
 * @author tanaryo
 */
public class Fish extends Animal {
    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Fish() {
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected String getBarkWord() {
        return "pitya-"; // mew? in English
    }

    // ===================================================================================
    //                                                                           Hit Point
    //                                                                           =========
    @Override
    protected void downHitPoint() {
        super.downHitPoint();
        if (hitPoint % 2 == 0) {
            super.downHitPoint();
        }
    }
}
