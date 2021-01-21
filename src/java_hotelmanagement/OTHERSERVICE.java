/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_hotelmanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DJ LEN Lasitha
 */
public class OTHERSERVICE {
    
     db_connection myconnection= new db_connection();
     
     
     
      public void fillservice(JTable table)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `otherservice`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            
            DefaultTableModel tablemodel= (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
            row =new Object[2];
            
            row[0]=rs.getInt(1);
            
            row[1]=rs.getInt(2);
            
            tablemodel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     
     
      public boolean addservice(int totalprice)
    {
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `otherservice`(`totalprice`) VALUES (?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            
            
            st.setInt(1, totalprice);
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
       public boolean editservice(int serviceid,int totalprice)
    {
     PreparedStatement st;
        ResultSet rs;
        String editQuery="UPDATE `otherservice` SET `totalprice`=? WHERE `serviceid`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(editQuery);
            
            
            
            st.setInt(1, totalprice);
            st.setInt(2, serviceid);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
       public boolean removeservice(int serviceid)
    {
    
     PreparedStatement st;
        ResultSet rs;
        String deleteQuery="DELETE FROM `otherservice` WHERE `serviceid`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            
            st.setInt(1, serviceid);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    
    
}
