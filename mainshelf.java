/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanager;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author medit
 */
public class mainshelf implements Serializable{

    int rec_id;
    int book_id;
    int shelf_no;

     public static void getSelf(ArrayList<mainshelf> recs) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            
                Statement statementMain = con.createStatement();
                ResultSet resultMain = statementMain.executeQuery("SELECT * FROM mainshelf");
                while (resultMain.next()) {
                    mainshelf s = new mainshelf();
                    s.rec_id=resultMain.getInt("rec_id");
                    s.book_id=resultMain.getInt("book_id");
                    s.shelf_no=resultMain.getInt("shelf_no");
                    recs.add(s);
                }
           
        } catch (SQLException ex) {
            Logger.getLogger(study.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
    public int addSelf(record r, int no) {
        this.book_id = r.getId();
        Connection con;
        boolean whileloop = true;
        boolean iseq = false;
        ArrayList<Integer> val = new ArrayList();
        int randint = 0;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

            this.shelf_no = no;

            Statement ids = con.createStatement();
            ResultSet id = ids.executeQuery("select * from mainshelf");

            while (id.next()) {
                val.add(id.getInt("rec_id"));
            }

            while (whileloop) {
                Random rand = new Random();
                randint = rand.nextInt(100000);
                iseq = false;
                for (int j = 0; j < val.size(); j++) {
                    if (randint == val.get(j)) {
                        iseq = true;
                    }
                }
                if (iseq == false) {
                    whileloop = false;
                    break;
                }
            }
            this.rec_id = randint;
            Statement st = con.createStatement();
            st.executeUpdate("insert into mainshelf (rec_id,book_id,shelf_no) values (" + this.rec_id + "," + this.book_id + "," + this.shelf_no + ") ");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    
    
    
    public int addSelf() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");

            Statement st = con.createStatement();
            st.executeUpdate("insert into mainshelf (rec_id,book_id,shelf_no) values (" + this.rec_id + "," + this.book_id + "," + this.shelf_no + ") ");
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(mainshelf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
