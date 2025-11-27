package jb.livro.api_livro;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static jb.livro.api_livro.Book.parseBooks;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do autor: ");
        String a = sc.nextLine();
        System.out.println(a);
        String resposta = GeminiCon.getBooks(a);
        List<Book> livros = parseBooks(resposta);
        int founds = livros.size();
        System.out.println("Livros encontrados: "+ founds);
        for (Book b : livros) {
            System.out.printf("%n | ------------------------------------ | %n");
            System.out.println(b.print());
        }
        System.out.print("Livros adicionados: ");
        System.out.println(DBManager.addBook(livros));
    }
}
