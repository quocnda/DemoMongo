package org.example;

import com.mongodb.client.*;
import org.bson.Document;
import com.google.gson.Gson;
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
            System.out.println(data.getWord());
            ldata.add(data);
        }
        return ldata;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Data> ldata = getData("Ykhoa");
        for(int i = 0 ; i < 10; i ++) {
            System.out.println(ldata.get(i).getWord());
            System.out.println(ldata.get(i).getDes());
        }

    }
}
