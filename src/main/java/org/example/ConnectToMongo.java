package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import com.google.gson.Gson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Data{
    private String id;
    private String word;
    private String html;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    private String des;
    private String pro;

    public String getWord() {
        return word;
    }

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setWord(String word) {
        this.word = word;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }


}

public class ConnectToMongo {
    public static List<Data> getData(String namedata) {
        MongoClient client  = MongoClients.create("mongodb+srv://testUser:vQpzsyz66a0Dbhvi@cluster0.hgbp0kr.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db  = client.getDatabase("TestJava");
        MongoCollection<Document> collection = db.getCollection(namedata);
        FindIterable<Document> documents = collection.find();
        List<Data> ldata = new ArrayList<>();
        Gson gson = new Gson();
        for (Document document : documents) {
            String json = document.toJson();
            Data data = gson.fromJson(json,Data.class);
            ldata.add(data);
        }
        return ldata;
    }
    public static void addWord(String namedata) {
        MongoClient client  = MongoClients.create("mongodb+srv://testUser:vQpzsyz66a0Dbhvi@cluster0.hgbp0kr.mongodb.net/?retryWrites=true&w=majority");
        MongoDatabase db  = client.getDatabase("TestJava");
        MongoCollection<Document> collection = db.getCollection(namedata);
        FindIterable<Document> documents = collection.find();
        int cnt = 0;
        for(Document doc : documents) {
            cnt++;
        }
        cnt++;
        Document document = new Document("_id","ew2").append("id",cnt).append("word","chetchamdu").append("html","").append("des","lachetchamdu").append("pro","");
        collection.insertOne(document);
    }
    public static void main(String[] args) {
        //thay ten database lan luot la : Congnghethontin,Dientu,TestJson,ThanhNgu,Ykhoa
        Scanner scan = new Scanner(System.in);
        List<Data> ldata = getData("Ykhoa");
        System.out.println(ldata.size());
        System.out.println(ldata.get(223).getWord());

    }
}
