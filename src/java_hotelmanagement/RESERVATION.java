/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_hotelmanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DJ LEN Lasitha
 */
public class RESERVATION {
    
    
    //add 2 foreign keys to client table and room table
    //alter TABLE reservation ADD CONSTRAINT fk_client_id FOREIGN KEY (client_id) REFERENCES client(id) on DELETE CASCADE
    //alter TABLE reservation ADD CONSTRAINT fk_room_number FOREIGN KEY (room_number) REFERENCES room(r_number) on DELETE CASCADE
    
    //foreign key between type table and room table
    //alter TABLE room ADD CONSTRAINT fk_type_id FOREIGN KEY (type) REFERENCES type(id) on DELETE CASCADE
    
    db_connection myconnection= new db_connection();
    
    ROOM room =new ROOM();
    
    public boolean addreservation(int client_id,int room_number, String checkIn, String checkOut,int roomprice,int nightstay,String charge)
    {
        PreparedStatement st;
        ResultSet rs;
        String addQuery="INSERT INTO `reservation`(`client_id`, `room_number`, `check_in`, `check_out`, `room_price`, `nightstay`, `charge`) VALUES (?,?,?,?,?,?,?)";
        
        try {
            st = myconnection.createConnection().prepareStatement(addQuery);
            
            st.setInt(1, client_id);
            st.setInt(2, room_number);
            st.setString(3, checkIn);
            st.setString(4, checkOut);
            st.setInt(5,roomprice);
            st.setInt(6,nightstay);
            st.setString(7,charge);
            
            if(room.checkroomstatus(room_number).equals("No"))
            {
            //when new reservation added reserved in room column should goes to Yes
                if (st.executeUpdate()>0)
            {
                room.roomstatus(room_number, "Yes");
            return true;
            }else{
            return false;
            }
            }else{
                JOptionPane.showMessageDialog(null,"Already reserved","Reserved room",JOptionPane.WARNING_MESSAGE);
        
            return false;
            }

            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
    public boolean editreservation(int reservation_id,int client_id,int room_number,String checkIn, String checkOut,int roomprice,int nightstay,String charge)
    {
     PreparedStatement st;
        ResultSet rs;
        String editQuery="UPDATE `reservation` SET `client_id`=?,`room_number`=?,`check_in`=?,`check_out`=?,`room_price`=?,`nightstay`=?,`charge`=? WHERE `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(editQuery);
            
            
            st.setInt(1, client_id);
            st.setInt(2, room_number);
            st.setString(3, checkIn);
            st.setString(4, checkOut);
            st.setInt(5,roomprice);
            st.setInt(6,nightstay);
            st.setString(7,charge);
            st.setInt(8, reservation_id);
            
            return (st.executeUpdate()>0);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean removereservation(int reservation_id)
    {
    
     PreparedStatement st;
        ResultSet rs;
        String deleteQuery="DELETE FROM `reservation` WHERE `id`=?";
        
        try {
            st = myconnection.createConnection().prepareStatement(deleteQuery);
            
            
            st.setInt(1, reservation_id);
            //when delete the reservation it should set No in room table
            int room_number=getroomnumber(reservation_id);
            if (st.executeUpdate()>0)
            {
             room.roomstatus(room_number, "No");
            return true;
            }else{
            return false;
            }
            } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     
     public void fillReservation(JTable table)
    {
    PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT * FROM `reservation`";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            
            rs=ps.executeQuery();
            
            DefaultTableModel tablemodel= (DefaultTableModel)table.getModel();
            
            Object[] row;
            
            while(rs.next())
            {
            row =new Object[8];
            row[0]=rs.getInt(1);
            row[1]=rs.getInt(2);
            row[2]=rs.getInt(3);
            row[3]=rs.getString(4);
            row[4]=rs.getString(5);
            row[5]=rs.getInt(6);
            row[6]=rs.getInt(7);
            row[7]=rs.getString(8);
            
            tablemodel.addRow(row);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
 
     //function to room no. from reservation
     public int getroomnumber(int reservation_id)
     {
     PreparedStatement ps;
    ResultSet rs;
    String selectQuery = "SELECT `room_number` FROM `reservation` WHERE `id`=?";
    
        try {
            ps= myconnection.createConnection().prepareStatement(selectQuery);
            ps.setInt(1, reservation_id);
            rs=ps.executeQuery();
            
            if(rs.next())
            {
            return rs.getInt(1);
            }else{
            return 0;
            }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
       return 0;
        }
    
     }
 
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
            
            combobox.addItem(rs.getString(3));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
