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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author medit
 */
public class reading extends book {

    String writer;
    String type;
    String shelfType;

    public void changePage(int page){
        try {
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
                Statement st = con.createStatement();
                st.executeUpdate("update reading set page = " + page + " where id = " + this.id);
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(newMain.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public String getKind() {
        return "reading";
    }

    public String getInfo() {
        return "Reading id : " + this.getId() + "/Reading Name : " + this.name + "/Reading writer : " + this.writer + "/Reading publisher : " + this.publisher + "/Reading type : " + this.type + "/Reading shelf type : " + this.shelfType + "/Reading shelf no : " + String.valueOf(this.getShelfNo());
    }

    public Object[] returnVarMain(int shelfno) {
        return new Object[]{this.id, this, this.writer, this.publisher, this.type, " ", " ", this.shelfType, this.getShelfNo(), "Reading",this.page};
    }

    public static void getSelf(ArrayList<record> recs, boolean isMain, boolean isSecond) {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            if (isMain) {
                Statement statementMain = con.createStatement();
                ResultSet resultMain = statementMain.executeQuery("SELECT * FROM reading r join mainshelf m on r.id = m.book_id ");
                while (resultMain.next()) {
                    reading s = new reading();
                    s.id = resultMain.getInt("id");
                    s.name = resultMain.getString("name");
                    s.publisher = resultMain.getString("publisher");
                    s.writer = resultMain.getString("writer");
                    s.type = resultMain.getString("type");
                    s.shelfType = "Primary shelf";
                    s.page=resultMain.getInt("page");
                    recs.add(s);
                }
            }

            if (isSecond) {
                Statement statementSecond = con.createStatement();
                ResultSet resultSecond = statementSecond.executeQuery("SELECT * FROM reading r join auxshelf m on r.id = m.book_id ");
                while (resultSecond.next()) {
                    reading s = new reading();
                    s.id = resultSecond.getInt("id");
                    s.name = resultSecond.getString("name");
                    s.publisher = resultSecond.getString("publisher");
                    s.writer = resultSecond.getString("writer");
                    s.type = resultSecond.getString("type");
                    s.shelfType = "Secondary shelf";
                    s.page=resultSecond.getInt("page");
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
                ResultSet resultMain = statementMain.executeQuery("SELECT * FROM reading r join mainshelf m on r.id = m.book_id and name = '" + name + "' ");
                while (resultMain.next()) {
                    reading s = new reading();
                    s.id = resultMain.getInt("id");
                    s.name = resultMain.getString("name");
                    s.publisher = resultMain.getString("publisher");
                    s.writer = resultMain.getString("writer");
                    s.type = resultMain.getString("type");
                    s.shelfType = "Primary shelf";
                    s.page=resultMain.getInt("page");
                    recs.add(s);
                }
            }

            if (isSecond) {
                Statement statementSecond = con.createStatement();
                ResultSet resultSecond = statementSecond.executeQuery("SELECT * FROM reading r join auxshelf m on r.id = m.book_id and name = '" + name + "' ");
                while (resultSecond.next()) {
                    reading s = new reading();
                    s.id = resultSecond.getInt("id");
                    s.name = resultSecond.getString("name");
                    s.publisher = resultSecond.getString("publisher");
                    s.writer = resultSecond.getString("writer");
                    s.type = resultSecond.getString("type");
                    s.shelfType = "Secondary shelf";
                    s.page=resultSecond.getInt("page");
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
            ResultSet resultid = stid.executeQuery("SELECT * FROM reading r join mainshelf m on r.id = m.book_id and r.id = " + this.id + " ");

            Statement scid = con.createStatement();
            ResultSet resultsc = scid.executeQuery("SELECT * FROM reading r join auxshelf m on r.id = m.book_id and r.id = " + this.id + " ");

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
            Statement st = con.createStatement();
            st.executeUpdate("insert into reading (id,name,publisher,writer,type) values (" + this.id + ",'" + this.name + "','" + this.publisher + "','" + this.writer + "','" + this.type + "') ");

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
            ResultSet id = ids.executeQuery("select * from reading");

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
            Statement st = con.createStatement();
            st.executeUpdate("insert into reading (id,name,publisher,writer,type) values (" + this.id + ",'" + this.name + "','" + this.publisher + "','" + this.writer + "','" + this.type + "') ");

        } catch (SQLException ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object[] returnVar(String shelf, int shelfno) {
        Object[] s = {this, this.writer, this.publisher, this.type, " ", " ", " ", shelf, shelfno};
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
