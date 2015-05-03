/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain.Model;

/**
 *
 * @author Admin
 */
public class Book{

	private String bookId;
	private String bookName;


	public Book(String pBookId, String pBookName){

		bookId = pBookId;
		bookName = pBookName;

	}


	public String getBookId(){
		return bookId;
	}

	public String getBookName(){
		return bookName;
	}
        
        public Book getBookInfo(String bookId){
            
            
            Book book =  new Book("1","1");
            return book;
        }


}