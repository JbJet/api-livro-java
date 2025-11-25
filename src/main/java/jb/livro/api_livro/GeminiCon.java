package jb.livro.api_livro;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class GeminiCon {

    public static String getBooks(String author) {

        String querry = "ere um array JSON de todos os livros que você encontrar do autor que irei passar,EU NÃO QUERO SOMENTE 4 QUERO TODOS QUE ACHAR." +
                "Cada objeto no array JSON deve representar um livro e **deve** conter exatamente os seguintes campos, com o tipo de dado especificado:" +
                "1.  titulo: String." +
                "2.  autor: String." +
                "3.  ano: Número inteiro (Somente o número do ano de publicação)." +
                "4.  editora: String (Somente o nome da editora)." +
                "5.  paginas: Número inteiro (Somente o número total de páginas)." +
                "6.  genero: String (Somente um gênero principal).\n" +
                "7.  resumo: String." +
                "Não inclua nenhum texto introdutório ou explicativo, apenas o bloco de código JSON." +
                "---" +
                "Autor: ";

        querry += author;

        Client client = Client.builder().apiKey("AIzaSyAXDJ_mJBNsWX0Vrp8-9_4aLXDMbR7njk4").build();

        GenerateContentResponse response = client.models.generateContent("gemini-2.5-flash", querry, null);

        return response.text();
    }
}
