
package java_hotelmanagement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DJ LEN Lasitha
 */
public class db_connection {
  //connect with databases 
    public static Connection createConnection()
    {
    Connection connection =null;
    
    MysqlDataSource mds=new MysqlDataSource();
    
    mds.setServerName("localhost");
    mds.setPortNumber(3306);
    mds.setUser("root");
    mds.setPassword("");
    mds.setDatabaseName("hoteldb");
    
        try {
            connection=mds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    return connection;
    }
}
