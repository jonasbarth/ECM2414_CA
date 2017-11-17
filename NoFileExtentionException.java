/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

/**
 * Class that holds information about the Exception specifically for when no file extension has been used with the file name
 *
 * @author 660050748, 660049985
 */
class NoFileExtentionException extends Exception {
    
    public NoFileExtentionException(String message) {
        super(message);
    }
}
