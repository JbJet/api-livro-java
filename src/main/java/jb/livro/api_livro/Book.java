package jb.livro.api_livro;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Book {
    @SerializedName("titulo")
    private String title;

    @SerializedName("autor")
    private String author;

    @SerializedName("ano")
    private String year;

    @SerializedName("editora")
    private String publisher;

    @SerializedName("paginas")
    private String pages;

    @SerializedName("genero")
    private String genre;

    @SerializedName("resumo")
    private String synopsis;

    public List<String> getInfos() {
        return List.of(new String[]{title, author, year, publisher, pages, genre, synopsis});
    }

    public String print(){
       return String.format("Nome: %s, Autor: %s, Ano: %s", title,author,year);
    }
    @Override
    public String toString() {
        return "" + title + author + year + publisher + pages + genre + synopsis;
    }

    public static List<Book> parseBooks(String json) {

        json = json.trim();

        if (json.startsWith("```")) {
            json = json.substring(json.indexOf('\n') + 1);
            json = json.substring(0, json.lastIndexOf("```")).trim();
        }

        if (json.startsWith("\"") && json.endsWith("\"")) {
            json = json.substring(1, json.length() - 1);
            json = json.replace("\\\"", "\"");
        }

        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<List<Book>>(){}.getType());
    }
}
