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

/**
 *
 * @author medit
 */
public class magazine implements record, Serializable {

    String name;
    int issue;
    int year;
    String publisher;
    String type;
    int id;
    String shelfType;

    public String getKind(){
    return "magazine";
    }
    
    
    public String getInfo() {
        return "Magazine id : " + this.getId() + "/Magazine Name : " + this.name + "/Magazine publisher : " + this.publisher + "/Magazine type : " + this.type + "/Magazine Issue : " + this.issue + "/Magazine shelf type : " + this.shelfType + "/Magazine shelf no : " + String.valueOf(this.getShelfNo());
    }

    public Object[] returnVarMain(int shelfno) {
        return new Object[]{this.id, this, " ", this.publisher, this.type, this.issue, " ", this.shelfType, this.getShelfNo(), "Magazine"};
    }

    public static void getSelf(ArrayList<record> recs, boolean isMain, boolean isSecond) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            if (isMain) {
                Statement statementMain = con.createStatement();
                ResultSet resultMain = statementMain.executeQuery("SELECT * FROM magazine r join mainshelf m on r.id = m.book_id ");
                while (resultMain.next()) {
                    magazine s = new magazine();
                    s.id = resultMain.getInt("id");
                    s.name = resultMain.getString("name");
                    s.publisher = resultMain.getString("publisher");
                    s.issue = resultMain.getInt("issue");
                    s.type = resultMain.getString("type");
                    s.shelfType = "Primary shelf";

                    recs.add(s);
                }
            }

            if (isSecond) {
                Statement statementSecond = con.createStatement();
                ResultSet resultSecond = statementSecond.executeQuery("SELECT * FROM magazine r join auxshelf m on r.id = m.book_id ");
                while (resultSecond.next()) {
                    magazine s = new magazine();
                    s.id = resultSecond.getInt("id");
                    s.name = resultSecond.getString("name");
                    s.publisher = resultSecond.getString("publisher");
                    s.issue = resultSecond.getInt("issue");
                    s.type = resultSecond.getString("type");
                    s.shelfType = "Secondary shelf";
                    recs.add(s);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(study.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void getSelf(String name, ArrayList<record> recs, boolean isMain, boolean isSecond) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            if (isMain) {
                Statement statementMain = con.createStatement();
                ResultSet resultMain = statementMain.executeQuery("SELECT * FROM magazine r join mainshelf m on r.id = m.book_id and name = '" + name + "' ");
                while (resultMain.next()) {
                    magazine s = new magazine();
                    s.id = resultMain.getInt("id");
                    s.name = resultMain.getString("name");
                    s.publisher = resultMain.getString("publisher");
                    s.issue = resultMain.getInt("issue");
                    s.type = resultMain.getString("type");
                    s.shelfType = "Primary shelf";
                    recs.add(s);
                }
            }

            if (isSecond) {
                Statement statementSecond = con.createStatement();
                ResultSet resultSecond = statementSecond.executeQuery("SELECT * FROM magazine r join auxshelf m on r.id = m.book_id and name = '" + name + "' ");
                while (resultSecond.next()) {
                    magazine s = new magazine();
                    s.id = resultSecond.getInt("id");
                    s.name = resultSecond.getString("name");
                    s.publisher = resultSecond.getString("publisher");
                    s.issue = resultSecond.getInt("issue");
                    s.type = resultSecond.getString("type");
                    s.shelfType = "Secondary shelf";
                    recs.add(s);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(study.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getShelfNo() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            Statement stid = con.createStatement();
            ResultSet resultid = stid.executeQuery("SELECT * FROM magazine r join mainshelf m on r.id = m.book_id and r.id = " + this.id + " ");

            Statement scid = con.createStatement();
            ResultSet resultsc = scid.executeQuery("SELECT * FROM magazine r join auxshelf m on r.id = m.book_id and r.id = " + this.id + " ");

            int id = -1;
            while (resultid.next()) {
                id = resultid.getInt("shelf_no");
            }

            while (resultsc.next()) {
                id = resultsc.getInt("shelf_no");
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(study.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void addSelf() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            Statement insertion = con.createStatement();
            insertion.executeUpdate("insert into magazine (id,name,publisher,type) values (" + this.id + ",'" + this.name + "','" + this.publisher + "','" + this.type + "') ");
            Statement st2 = con.createStatement();
            st2.executeUpdate("update magazine set issue = " + this.issue + " where id= " + this.id);
        } catch (SQLException ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addRecord() {
        Connection con;
        boolean whileloop = true;
        boolean iseq = false;
        ArrayList<Integer> val = new ArrayList();
        int randint = 0;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            Statement ids = con.createStatement();
            ResultSet id = ids.executeQuery("select * from magazine");

            while (id.next()) {
                val.add(id.getInt("id"));
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
            this.id = randint;

            Statement insertion = con.createStatement();
            insertion.executeUpdate("insert into magazine (id,name,publisher,type) values (" + this.id + ",'" + this.name + "','" + this.publisher + "','" + this.type + "') ");
            Statement st2 = con.createStatement();
            st2.executeUpdate("update magazine set issue = " + this.issue + " where id= " + this.id);
        } catch (SQLException ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object[] returnVar(String shelf, int shelfno) {
        Object[] s = {this, " ", this.publisher, this.type, Integer.toString(this.issue), Integer.toString(this.year), " ", shelf, shelfno};
        return s;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
