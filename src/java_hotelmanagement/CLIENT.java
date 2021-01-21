
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
public class CLIENT {
    
    db_connection myconnection= new db_connection();
    
    //create functions to add, delete,edit clients
    public boolean addclient(String fname,String lname,String telephone,String email,String nationality)
    {
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `client`(`f_name`, `l_name`, `phone`, `email`, `nationality`) VALUES (?,?,?,?,?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, telephone);
            st.setString(4, email);
            st.setString(5, nationality);
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    //edit
    public boolean editclient(int id,String fname,String lname,String telephone,String email,String nationality)
    {
     PreparedStatement st;
        ResultSet rs;
        String editQuery="UPDATE `client` SET `f_name`=?,`l_name`=?,`phone`=?,`email`=?,`nationality`=? WHERE `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(editQuery);
            
            st.setString(1, fname);
            st.setString(2, lname);
            st.setString(3, telephone);
            st.setString(4, email);
            st.setString(5, nationality);
            st.setInt(6, id);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //remove
    public boolean removeclient(int id)
    {
    
     PreparedStatement st;
        ResultSet rs;
        String deleteQuery="DELETE FROM `client` WHERE `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            
            st.setInt(1, id);
            
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //function to fill the table
    public void fillclient(JTable table)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `client`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            
            DefaultTableModel tablemodel= (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
            row =new Object[6];
            row[0]=rs.getInt(1);
            row[1]=rs.getString(2);
            row[2]=rs.getString(3);
            row[3]=rs.getString(4);
            row[4]=rs.getString(5);
            row[5]=rs.getString(6);
            
            tablemodel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    } 
    
     public void fillcombobox(JComboBox combobox)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `nationality`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            

            
            while(rs.next())
            {
            
            combobox.addItem(rs.getString(2));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
