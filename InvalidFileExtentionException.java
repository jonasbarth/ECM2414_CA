/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecm1414_ca;

/**
 * Class that holds the Exceptions specifically for when the incorrect file extension is used
 *
 * @author 660050748, 660049985
 */
public class InvalidFileExtentionException extends Exception {

    public InvalidFileExtentionException(String message) {
        super(message);
    }
    
}
