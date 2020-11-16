import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3307/db_receitas?useTimezone=true&serverTimezone=UTC";
    private static final String user = "leandro";
    private static final String senha = "Astre45@QQ";
    private static final String MAX_POOL = "250";
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";//"com.mysql.cj.jdbc.Driver"//"com.mysql.jdbc.Driver"
    private Statement stmt;
    private ResultSet retornosql = null;
    private String query = "SELECT NOME FROM PESSOAS LIMIT 1";

    private Connection connection;
    private Properties properties;

        private Properties getProperties() {
            if (properties == null) {
                properties = new Properties();
                properties.setProperty("user", user);
                properties.setProperty("password", senha);
                properties.setProperty("MaxPooledStatements", MAX_POOL);
            }
            return properties;
        }

        private Connection connect() {
            if (connection == null) {
                try {
                    Class.forName(DATABASE_DRIVER);
                    connection = DriverManager.getConnection(url,user, senha);                    
                } catch (ClassNotFoundException | SQLException e){}
            }
            return connection;
        }
        private void disconnect() {
            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {}
            }
        }
        
        public String stringreturn ()
        {            
            try 
            {
                connect();
                stmt = this.connection.createStatement();
                this.retornosql = stmt.executeQuery(this.query); 
                 String strr = "";
                while (retornosql.next()) 
                {
                    strr = retornosql.getString("NOME");
                }
                
                disconnect();
                return strr;
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return "";
        }
        
}