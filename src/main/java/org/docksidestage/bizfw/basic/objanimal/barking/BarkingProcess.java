package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
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
    protected final Animal animal;
    protected final Runnable animalRunnable;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcess(Animal animal, Runnable animalRunnable) {
        this.animal = animal;
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
        animalRunnable.run(); // Animal経由で呼び出す
    }

    private void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle for barking");
        animalRunnable.run(); // Animal経由で呼び出す
    }

    private BarkedSound doBark(String barkWord) {
        animalRunnable.run(); // Animal経由で呼び出す
        return new BarkedSound(barkWord);
    }
}
