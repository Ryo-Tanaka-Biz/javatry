package org.docksidestage.bizfw.basic.objanimal.barking;

import org.docksidestage.bizfw.basic.objanimal.Animal;
import org.docksidestage.bizfw.basic.objanimal.Zombie;

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
        super(animal);//animalはZombieインスタンス
        zombie = (Zombie) super.animal;
    }

    // ===================================================================================
    //                                                                               Bark
    //                                                                              ======
    @Override
    protected void breatheIn() {
        super.breatheIn();
        zombie.getZombieDiary().countBreatheIn();
    }
}
