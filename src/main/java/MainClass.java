import java.sql.*;

/**
 * Created by joan on 07/11/15.
 */
public class MainClass {

    public static void main(String args[]) {


        Connection conn = null;
        String dbUrl = "jdbc:mysql://localhost/PRUEBA";
        String dbClass = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "1299438";
        String query = "Select * from PruebaCust where idCustomer > 2";
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connTran = null;

        try {


            //El driver es usado para conectarse a la DB.
            //La registración de un driver es un proceso simple (pero critico).
            Class.forName(dbClass).newInstance();

            //OBtenemos una conección
            conn = DriverManager.getConnection(dbUrl, username, password);

            if (!conn.isClosed()) {

                System.out.println("Database connection working");


                //Statement
                statement = conn.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println("idCustomer -> " + resultSet.getString("idCustomer"));
                    System.out.println("nombre -> " + resultSet.getString("nombre"));
                    System.out.println("apellido -> " + resultSet.getString("apellido"));
                }


                //Prepared Statement
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from PruebaCust where idCustomer = ? and nombre = ?");
                preparedStatement.setString(1, "1");
                preparedStatement.setString(2, "Jose");
                ResultSet resultSetPrepared = preparedStatement.executeQuery();
                while (resultSetPrepared.next()) {
                    System.out.println("idCustomer -> " + resultSetPrepared.getString("idCustomer"));
                    System.out.println("nombre -> " + resultSetPrepared.getString("nombre"));
                    System.out.println("apellido -> " + resultSetPrepared.getString("apellido"));
                }
                preparedStatement.close();
                resultSetPrepared.close();
            }




        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());

        } finally {

            if (conn != null) {
                try {
                    statement.close();
                    resultSet.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        TransactionClass.callTransaction();

    }

}
