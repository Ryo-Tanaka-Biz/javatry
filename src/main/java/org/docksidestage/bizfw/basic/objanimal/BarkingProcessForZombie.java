package org.docksidestage.bizfw.basic.objanimal;

/**
 * @author tanaryo
 */
public class BarkingProcessForZombie extends BarkingProcess {
    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private final Zombie zombie;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public BarkingProcessForZombie(Animal animal) {
        super(animal);
        zombie = (Zombie) super.animal;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected void breatheIn() {
        super.breatheIn();
        zombie.zombieDiary.countBreatheIn();
    }
}
