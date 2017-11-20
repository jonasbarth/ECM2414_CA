/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

/**
 * Class to allow the creation of Bag type object as Bag is abstract. Allows for distinction
 * between the different coloured Bag objects since the WhiteBag does not add any more functionality
 * as specified in the super class.
 * @author 660050748, 660049985
 */
public class WhiteBag extends Bag{
    
    public WhiteBag(Pebble[] pebbles, String name) {
        super(pebbles, name);
    }
    
    /**
     * Method to set the partner bag of the WhiteBag object.
     * @param bag the BlackBag object be the partner of this bag.
     */
    public void setPartnerBag(BlackBag bag) {
        this.partnerBag = bag;
    }
}
