/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes_group4.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 *
<<<<<<< HEAD
 * @author mads_
 */
public class DatabaseConnector
{
    private SQLServerDataSource dataSource;

    public DatabaseConnector() throws IOException
    {
        Properties props = new Properties();
        props.load(new FileReader("DBSettings.txt"));
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }

    
=======
 * @author M
 */
public class DatabaseConnector {
>>>>>>> 8390b6a1ce67dcb87806d1c6bf80238addb249bb
    
    public Connection getConnection() throws SQLServerException
    {   
        return dataSource.getConnection();
    }
}
