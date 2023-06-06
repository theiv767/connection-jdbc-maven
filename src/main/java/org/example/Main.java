package org.example;
import java.sql.*;
import java.util.Random;

public class Main {

    public static String getRandomString(int size){
        StringBuilder sb = new StringBuilder(size);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomChar = random.nextInt(26) + 97;
            sb.append((char) randomChar);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws SQLException {
        // Config. De informações do banco de dados.
        String url = "jdbc:mysql://localhost:3306/gerenciamento_biblioteca";
        String user = "root";
        String password = "root";
        Connection connection = null;




        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password); // Conexão com banco de dados.




//            // 10000 INSERT =====================================================
//            String insertQuery = "INSERT INTO autores(nome, nacionalidade, data_nascimento, genero) " +
//                    "values(?, ?, \"1924-04-24\", ?)" ;
//            PreparedStatement statement = connection.prepareStatement(insertQuery);
//            int batchSize = 500;
//            int count = 0;
//            for(int i=0; i<10000; i++) {
//                System.out.println("iteracao " +  i);
//                statement.setString(1, getRandomString(10));
//                statement.setString(2, getRandomString(10));
//                statement.setString(3, getRandomString(10));
//
//                statement.addBatch();
//
//                if (++count % batchSize == 0) {
//                    statement.executeBatch();
//                }
//            }
//
//
//
//
//           // ======= Consulta ======================================================================
            String searchQuery = "SELECT * FROM autores";
            Statement statementSearch = connection.createStatement();
            ResultSet resultSearch = statementSearch.executeQuery(searchQuery);


            while (resultSearch.next()) {
                int id = resultSearch.getInt("id");
                String nome = resultSearch.getString("nome");
                String nacionalidade = resultSearch.getString("nacionalidade");

                System.out.println("ID: " + id + ", Nome: " + nome+ ", Nacionalidade: "+ nacionalidade);
            }
////
//
//
//
//
//
//            // ======= Atualização ===========================================
//            String updateQuery = "UPDATE autores SET nacionalidade = \"brasileiro\" WHERE id < 9000";
//            PreparedStatement statamentUpdate = connection.prepareStatement(updateQuery);
//
//            int rowsUpdate = statamentUpdate.executeUpdate();
//            System.out.println("Atualizados: " + rowsUpdate);
//






//            // ======= Exclusão =======
//            String deleteQuery = "DELETE FROM autores WHERE id < 5000";
//            PreparedStatement statamentDelete = connection.prepareStatement(deleteQuery);
//
//            int rowsDeleted = statamentDelete.executeUpdate();
//            System.out.println("Afetados: " + rowsDeleted);


        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        } finally {
            if(connection != null){
                connection.close();
            }
        }
    }
}
