/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ECM2414_CA;

/**
 * Class for Exceptions that occur when the content of the file is not of the specified format: A list of comma, seperated
 * integers 
 *
 * @author 660050748, 660049985
 */
class IllegalFileContentException extends Exception {
    public IllegalFileContentException(String message) {
        super(message);
    }
}
