/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareadiagramasreque;

import Domain.DataAccess.DatabaseHandler;
import View.OptionChooser;

/**
 *
 * @author Admin
 */
public class Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        OptionChooser optionChooser = new OptionChooser();
        optionChooser.setVisible(true);
        
        //DatabaseHandler hndl = DatabaseHandler.getInstance();
        //hndl.bookAvailable("2");
        
        
    }
    
}
