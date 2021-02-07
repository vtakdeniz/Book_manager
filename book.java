/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanager;

import java.io.Serializable;

/**
 *
 * @author medit
 */
public abstract class book implements record , Serializable{
    String name;
    String publisher;
    int page;
    int id;
    public Object[] returnVar(String shelf) {
        String [] s = {this.name," ",this.publisher," "," "," "," "};
        return null;
    }
    public String getKind() {
    return "kind";
    }
    public  abstract void changePage(int page);

}
