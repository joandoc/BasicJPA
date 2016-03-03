import java.sql.*;

/**
 * Created by joan on 07/11/15.
 */
public class TransactionClass {



    public static void callTransaction(){

        String dbUrl = "jdbc:mysql://localhost/PRUEBA";
        String dbClass = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "1299438";


        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //TRANSACTION---------------------------------------------------

        try {
            conn = DriverManager.getConnection(dbUrl, username, password);
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO PruebaCust values ('10','juan','Mardolondo')");
            st.executeUpdate("INSERT INTO PruebaCustal values ('7','juan','Mardolondo')");
            conn.commit();
        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException e2) {

            }

        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
