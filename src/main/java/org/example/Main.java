package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import org.bson.Document;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
class tmp {
    public int id;
    public String word;
    public String html;
    public String des;
    public String pro;

    tmp() {
        id = 0;
        word = "";
        html = "";
        des = "";
        pro = "";
    }

    tmp(int i, String wo,String htm, String ds, String pr) {
        id = i;
        word = wo;
        html = htm;
        des = ds;
        pro = pr;
    }
}
public class Main {
    public static List<tmp> GetWordFromData() throws Exception {
        List<tmp> entities = new ArrayList<tmp>();
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        try (Connection connection1 = DriverManager.getConnection(
                "jdbc:sqlite:D:\\Code_vsc\\BTLOOP\\DictionaryJavaFx\\src\\resources\\DataBase\\dict_hh.db");) {
            if (connection1 != null) {
                System.out.println("Connected");
                System.out.println(connection1);

            }
            String querry = String.format("SELECT * FROM av");
            System.out.println(querry);
            PreparedStatement preparedStatement;
            System.out.println("connection state: " + connection1);
            preparedStatement = connection1.prepareStatement(querry);
            System.out.println("prepare state: " + preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            int i = 0;

            while (resultSet.next() == true && i<1000) {
                int id = resultSet.getInt(1);
                String word = resultSet.getString(2);
                String html = resultSet.getString(3);
                String des = resultSet.getString(4);
                String pro = resultSet.getString(5);
                System.out.println(id + "  " + word + "  " + html+ " " +des + " "+ "  " + pro);
                i++;
                tmp a = new tmp(id, word, html,des, pro);
                entities.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return entities;
    }
    public static void exportToFile(String json, String path) {
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(json);
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<tmp> LoadThanhngu(String path) {
        List<tmp> data = new ArrayList<>();
        int i = 0;
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            boolean b = true;
            String line;
            tmp temp = new tmp();
            while (true) {
                line = br.readLine();
                if(line == null) {
                    break;
                }
                int j = 0;
                String w = "";
                for (int h = 0 ; j < line.length() ; h++) {
                    if(line.charAt(h) !=':') {
                        w+=line.charAt(h);
                    } else {
                        j = h;
                        break;
                    }
                }
                String des = "";
                j+=2;
                for(int h = j; h < line.length();h++) {
                    des+=line.charAt(h);
                }
                temp.word = w;
                temp.des = des;
                temp.id = i;
                temp.html = "";
                temp.pro="";
                i++;
                temp = new tmp();
                data.add(temp);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void main(String[] args) throws Exception {

        List<tmp> ans = LoadThanhngu("D:\\Intell\\TestMongo\\src\\main\\resources\\Ykhoa.txt");
        for(int  i = 0 ; i < ans.size() ; i ++) {
            tmp t = ans.get(i);
            System.out.println(t.word);
        }
        Gson gson = new Gson();
        String json = gson.toJson(ans);
        System.out.println(json);
        String path = "D:\\Intell\\TestMongo\\src\\main\\java\\org\\example\\Thanhngu.json";
        exportToFile(json,path);
    }
}