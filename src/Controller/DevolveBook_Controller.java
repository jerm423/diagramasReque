/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.DataAccess.DatabaseHandler;
import View.DevolveBook;
import View.OptionChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DevolveBook_Controller {
    
    private static DevolveBook_Controller instance;
    private DatabaseHandler databaseHandler;

    protected DevolveBook_Controller(){
        
        databaseHandler = DatabaseHandler.getInstance();
    
    
    }

    public static DevolveBook_Controller getInstance(){

	if(instance == null){
            instance = new DevolveBook_Controller();
	}

	return instance;

    }
    
    public void devolveBook(String pBookId, DevolveBook pFrameDevolveBook){
        
        OptionChooser chooser = new OptionChooser();
        boolean bookExists = databaseHandler.bookExists(pBookId);
        
        if(bookExists){
            
            databaseHandler.removeBookLoan(pBookId);
            
        }
        else{
            
            String message = "El codigo de libro ingresado no existe.";
            JOptionPane.showMessageDialog(pFrameDevolveBook, message);
            
        }
        
        pFrameDevolveBook.setVisible(false);
        chooser.setVisible(true);
        
            
    }
    
}
