/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Domain.DataAccess.DatabaseHandler;
import View.LoanBook;
import View.OptionChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class LoanBook_Controller {
    
    private static LoanBook_Controller instance;
    private DatabaseHandler databaseHandler;

    
    
    protected LoanBook_Controller(){
        
        databaseHandler = DatabaseHandler.getInstance();
    
    
    }

    public static LoanBook_Controller getInstance(){

	if(instance == null){
            instance = new LoanBook_Controller();
	}

	return instance;

    }
    
    public void registerBookLoan(String pStudentId, String pBookId, LoanBook pFrameLoanBook){
        
        OptionChooser chooser = new OptionChooser();
        boolean bookExists = databaseHandler.bookExists(pBookId);
        boolean studentExists = databaseHandler.studentExists(pStudentId);
        boolean bookAvailable = databaseHandler.bookAvailable(pBookId);
        
        if( (bookExists) && (studentExists) ){
            
            if(bookAvailable){
                
                boolean insert;
            
                insert = databaseHandler.insertBookLoan(pBookId, pStudentId);

                if (!insert){
                    String message = "Ha ocurrido un error en el registro del prestamo. Intente de nuevo.";
                    JOptionPane.showMessageDialog(pFrameLoanBook, message);
                }
                else{
                    String message = "El prestamo se ha realizado de forma exitosa.";
                    JOptionPane.showMessageDialog(pFrameLoanBook, message);
                                      
                }
                
            }
            else{
                String message = "El libro seleccionado no esta disponible.";
                JOptionPane.showMessageDialog(pFrameLoanBook, message);
            }
            
            
            
            
        }
        else{
                      
            if(!studentExists){
                String message = "El carne del estudiante ingresado no existe.";
                JOptionPane.showMessageDialog(pFrameLoanBook, message);
                
            }
            else if(!bookExists){
                String message = "El codigo de libro ingresado no existe.";
                JOptionPane.showMessageDialog(pFrameLoanBook, message);
            }
            
            
            
        }
        
        pFrameLoanBook.setVisible(false);
        chooser.setVisible(true);
                        
    }
    
}
