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
        String password = "password";
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password); // Conexão com banco de dados.

            String insertQuery = "INSERT INTO autores(nome, nacionalidade, data_nascimento, genero) " +
                                 "values(?, ?, \"1924-04-24\", ?)" ;

            String searchQuery = "SELECT * FROM livros";
            String updateQuery = "UPDATE livros SET coluna = ? WHERE ano_publicacao > 2000";
            String deleteQuery = "DELETE FROM livros WHERE ano_publicacao < 2000";

            PreparedStatement statement = connection.prepareStatement(insertQuery);
            for(int i=0; i<10000; i++){
                statement.setString(1, getRandomString(10));
                statement.setString(2, getRandomString(10));
                statement.setString(3, getRandomString(10));

                int rowsAffected = statement.executeUpdate();
            }


//            // ======= Consulta =======
//            Statement statementSearch = connection.createStatement();
//            ResultSet resultSearch = statementSearch.executeQuery(searchQuery);
//
//
//
//
//
//            while (resultSearch.next()) {
//                int id = resultSearch.getInt("id");
//                String titulo = resultSearch.getString("titulo");
//
//                System.out.println("ID: " + id + ", Título: " + titulo);
//            }





//
//            // ======= Atualização =======
//            PreparedStatement statamentUpdate = connection.prepareStatement(updateQuery);
//            statamentUpdate.setString(1, "novo_valor");
//            statamentUpdate.setInt(2, 1);
//
//            int rowsUpdate = statamentUpdate.executeUpdate();
//            System.out.println("Atualizados: " + rowsUpdate);
//
//            // ======= Exclusão =======
//            PreparedStatement statamentDelete = connection.prepareStatement(deleteQuery);
//            statamentDelete.setInt(1, 1);
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
