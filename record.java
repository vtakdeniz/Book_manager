/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookmanager;

/**
 *
 * @author medit
 */
public interface record {
    void addRecord();
    Object[] returnVar(String shelf,int shelfno);
    int getId();
    public int getShelfNo();
    Object[] returnVarMain(int shelfno);
    String getInfo();
    void addSelf();
    public String getKind();
}
