/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author medit
 */
public class BookManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          ArrayList <record> recs= new ArrayList();
          magazine.getSelf(recs, true, true);
          for (record rec : recs) {
              System.out.print(rec.getId());
              System.out.println("      shelf NO : "+rec.getShelfNo());
        }
    }
    
}
