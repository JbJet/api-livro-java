package jb.livro.api_livro;

import java.sql.*;
import java.util.List;

public class DBManager {
    public static Connection createConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/livros_ia";
        String user = "root";
        String passowrd = "root";

        Connection conexao = null;
        conexao = DriverManager.getConnection(url, user, passowrd);
        return conexao;
    }
    public static boolean checkBook(Book b) throws SQLException {
        Connection conn = createConnection();
        String sql = "SELECT id FROM `livros_ia`.`livros` WHERE `nome` = ?" ;
        PreparedStatement s = conn.prepareStatement(sql);
        s.setString(1, b.getInfos().get(0));

        try(ResultSet r = s.executeQuery();){
            return  r.next();
        }

    }

    public static int addBook(List<Book> books) throws SQLException {
        Connection conn = createConnection();

        String sql = "INSERT INTO `livros_ia`.`livros`(`nome`, `autor`, `ano`, `editora`, `paginas`, `genero`, `resumo`) VALUES (?, ?, ?, ?, ?, ?, ?);";

        int inserted = 0;
        for(Book b: books){
            if(!checkBook(b)) {
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, b.getInfos().get(0));
                    pstmt.setString(2, b.getInfos().get(1));
                    pstmt.setInt(3, Integer.parseInt(b.getInfos().get(2)));
                    pstmt.setString(4, b.getInfos().get(3));
                    pstmt.setInt(5, Integer.parseInt(b.getInfos().get(4)));
                    pstmt.setString(6, b.getInfos().get(5));
                    pstmt.setString(7, b.getInfos().get(6));
                    pstmt.executeUpdate();
                    inserted++;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return inserted;
    }


}
