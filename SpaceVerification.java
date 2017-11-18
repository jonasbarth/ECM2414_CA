/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

/**
 * A class that holds methods to determine whether a list is a list of comma seperated integers.
 *
 * @author 660050748, 660049985
 */
public class SpaceVerification {
    
    private boolean passedInt, passedSpace, recentPassInt, recentPassSpace;
    
    /**
    * Constructor sets all of the flags to false to begin verification
    **/
    public SpaceVerification(){
        this.passedInt = false;
        this.passedSpace = false;
        this.recentPassInt = false;
        this.recentPassSpace = false;
    }
    
    //resets once we have encountered a comma in the string
    private void resetFlags(){
        this.passedSpace = false;
        this.passedInt = false;
        this.recentPassInt = false;
        this.recentPassSpace = false;
    }
    /**
    * Method throws an exception if the format is not a list of comma seperated integers
    *
    * @param check String which will be coming from the file input for the pebbles
    * @throws IllegalFileContentException when there are two integers not seperated by a comma
    **/
    public void allTogether(String check) throws IllegalFileContentException{
        for(int i = 0;i < check.length(); i++){
            
            if(passedInt) this.recentPassInt = true;
            if(passedSpace) this.recentPassSpace = true;
            //to identify as a space
            String use = "";
            char check2 = check.charAt(i);
            use += check2;
            try {
                //will work if we have encountered an integer
                int numCheck = Integer.parseInt(use);
                this.passedInt = true;
                //makes sure we have not passed an int and space already
                //i.e. the integers have not been seperated by a comma
                canUse();
                this.passedSpace = false;
                this.recentPassSpace = false;
                
            }
            catch (NumberFormatException e) {
                //passing a space
                if(use.equals(" ")) {
                    this.passedSpace = true;  
                }
                else if(use.equals(",")) {
                    resetFlags();
                }
            }
            
        }
    }
    
    //helper method to determine if we have two seperate integers not seperated by a comma - throws exception if so
    private void canUse() throws IllegalFileContentException{
        if(this.passedInt && this.passedSpace && this.recentPassInt && this.recentPassSpace) {
            
            
            throw new IllegalFileContentException("You are missing a comma somewhere in your file. Every integer needs to be separated by a comma");
        }
    }
}
