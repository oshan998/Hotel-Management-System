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
public class ROOM {
    
    db_connection myconnection= new db_connection();
    
    
    //function to display room type
     public void fillroomtype(JTable table)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `type`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            
            DefaultTableModel tablemodel= (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
            row =new Object[3];
            row[0]=rs.getInt(1);
            row[1]=rs.getString(2);
            row[2]=rs.getString(3);
            
            tablemodel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     
     
     public void fillroom(JTable table)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `room`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            
            DefaultTableModel tablemodel= (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
            row =new Object[5];
            row[0]=rs.getInt(1);
            row[1]=rs.getInt(2);
            row[2]=rs.getString(3);
            row[3]=rs.getString(4);
            
            row[4]=rs.getInt(5);
            
            
            tablemodel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
     
     //fill the combo box
      public void fillcombobox(JComboBox combobox)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `type`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            

            
            while(rs.next())
            {
            
            combobox.addItem(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

   //function to add rooms
      public boolean addroom(int rnumber,int rtype,String telephone,int totalcharge)
    {
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `room`(`r_number`, `type`, `phone`, `reserved`, `totalcharge`) VALUES (?, ?, ?, ?, ?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, rnumber);
            st.setInt(2, rtype);
            st.setString(3, telephone);
            //when add new room reserve should no
            st.setString(4, "No");
            
            st.setInt(5, totalcharge);
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
      
      public boolean editroom(int rnumber,int rtype,String telephone,String isReserved,int totalcharge)
    {
     PreparedStatement st;
        ResultSet rs;
        String editQuery="UPDATE `room` SET `type`=?,`phone`=?,`reserved`=?,`totalcharge`=? WHERE `r_number`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(editQuery);
            
            st.setInt(1, rtype);
            st.setString(2, telephone);
            st.setString(3, isReserved);
            
            
            st.setInt(4, totalcharge);
            st.setInt(5, rnumber);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
      
      public boolean removeroom(int rnumber)
    {
    
     PreparedStatement st;
        ResultSet rs;
        String deleteQuery="DELETE FROM `room` WHERE `r_number`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            
            st.setInt(1, rnumber);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
      
      //function to set the room reserved or not
       public boolean roomstatus(int rnumber,String isReserved)
    {
     PreparedStatement st;
        ResultSet rs;
        String editQuery="UPDATE `room` SET `reserved`=? WHERE `r_number`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(editQuery);
            
            
            st.setString(1, isReserved);
            st.setInt(2, rnumber);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    } 
       //function to check the room reserved or not
        public String checkroomstatus(int rnumber)
    {
     PreparedStatement st;
        ResultSet rs;
        String editQuery="SELECT `reserved` FROM `room` WHERE `r_number`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(editQuery);
            
            
            
            st.setInt(1, rnumber);
            
            rs=st.executeQuery();
            
            if(rs.next())
            {
            return rs.getString(1);
            }else{
            return "";
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    } 
     
}
