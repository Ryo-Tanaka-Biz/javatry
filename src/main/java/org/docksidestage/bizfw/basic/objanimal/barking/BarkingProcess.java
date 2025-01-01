package org.docksidestage.bizfw.basic.objanimal.barking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tanaryo
 */
public class BarkingProcess {
    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final Logger logger = LoggerFactory.getLogger(BarkingProcess.class);

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected final Runnable animalRunnable;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Runnable animalRunnable) {
        this.animalRunnable = animalRunnable;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    public BarkedSound bark(String barkWord) {
        breatheIn();
        prepareAbdominalMuscle();
        return doBark(barkWord);
    }

    protected void breatheIn() {
        logger.debug("...Breathing in for barking");
        animalRunnable.run();
    }

    private void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle for barking");
        animalRunnable.run();
    }

    private BarkedSound doBark(String barkWord) {
        animalRunnable.run();
        return new BarkedSound(barkWord);
    }
}
