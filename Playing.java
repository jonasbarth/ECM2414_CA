
package ECM2414_CA;

import ECM2414_CA.listenerEvent.PlayerListener;

/**
 * An interface providing methods for the Player class
 * inside the PebbleGame class
 * @author 660050748, 6600499985
 */
public interface Playing {
    
    /**
     * Method to return the name of the player
     * @return String
     */
    public String getName();
    
    /**
     * Method to register a playerlistener to the Player class.
     * The class can only have one listener as a Player can
     * only participate in one game at the time.
     * @param listener 
     */
    public void registerPlayerListener(PlayerListener listener);
    
    /**
     * Method to deregister the playerlistener,
     * effectively setting the field to null.
     */
    public void deregisterPlayerListener();
    
    /**
     * Sets a new bag for the player.
     */
    public void setNewBag();
    
    /**
     * Draws a pebble from the a black bag
     * passed as an argument.
     * @param bag 
     */
    public void drawPebble(BlackBag bag);
    
    /**
     * Gets the total weight of all pebbles
     * currently held in the players hand.
     * @return 
     */
    public int getTotalWeight();
    
    /**
     * Gets the bag currently held by the
     * player class
     * @return 
     */
    public Bag getCurrentBag();
    
    /**
     * Discards a pebble from the players hand
     * into the partner bag of the bag currently
     * held by the player.
     */
    public void discardPebble();
    
    /**
     * Sets the file path for the file the player
     * is writing to.
     * @param filePath 
     */
    public void setFile(String filePath);
    
    /**
     * Produces a message including the value of the
     * discarded pebble and the bag it was discarded
     * into.
     * @param bag
     * @param value 
     */
    public void discardedMessage(Bag bag, int value);
    
    /**
     * Produces a message including the value of the 
     * drawn pebble and the bag it was drawn from.
     * @param bag
     * @param value 
     */
    public void drawnMessage(Bag bag, int value);
    
    /**
     * Produces a message of the player's
     * current hand.
     */
    public void handMessage();
    
    /**
     * Writes the argument into a file.
     * @param output 
     */
    public void writeToFile(String output);
    
    
    
}
