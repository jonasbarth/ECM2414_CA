/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

/**
 *
 * @author Jonas
 */
public class SpaceVerification {
    
    // instance variables - replace the example below with your own
    private boolean passedInt, passedSpace, recentPassInt, recentPassSpace;
    

    public SpaceVerification(){
        this.passedInt = false;
        this.passedSpace = false;
        this.recentPassInt = false;
        this.recentPassSpace = false;
    }
    
    private void resetFlags(){
        this.passedSpace = false;
        this.passedInt = false;
        this.recentPassInt = false;
        this.recentPassSpace = false;
    }
    
    public void allTogether(String check) throws IllegalFileContentException{
        for(int i = 0;i < check.length(); i++){
            
            if(passedInt) this.recentPassInt = true;
            if(passedSpace) this.recentPassSpace = true;
            String use = "";
            char check2 = check.charAt(i);
            use += check2;
            try {
                int numCheck = Integer.parseInt(use);
                this.passedInt = true;
                //System.out.println("Passed an int");
                canUse();
                this.passedSpace = false;
                this.recentPassSpace = false;
                
            }
            catch (NumberFormatException e) {
                //System.out.println("Not an int");
                if(use.equals(" ")) {
                    this.passedSpace = true;
                    //System.out.println("Passed a space");
                    
                }
                else if(use.equals(",")) {
                    resetFlags();
                    //System.out.println("reset flags");
                }
            }
            
        }
    }
    
    private void canUse() throws IllegalFileContentException{
        if(this.passedInt && this.passedSpace && this.recentPassInt && this.recentPassSpace) {
            
            
            throw new IllegalFileContentException("You are missing a comma somewhere in your file. Every integer needs to be separated by a comma");
        }
    }
}
