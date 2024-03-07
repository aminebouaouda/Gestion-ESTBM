package View;
import java.util.Scanner;

import Model.CreateDataBaseAndTables;
public class main {

	public static void main(String[] args) {
		
		Model.CreateDataBaseAndTables m=new	Model.CreateDataBaseAndTables();
		m.CreateDatabaseAndTables();
         new Acceuil();
         
	}

}
