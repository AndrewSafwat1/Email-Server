package com.example.mailServer.mail.services.DataCache;

import com.example.mailServer.mail.services.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
@Service
public class Data {
    private static Data data = null ;
    private static ArrayList<User> users = new ArrayList<User>() ; ;
    private Data() throws IOException{
    }
    public static synchronized Data getInstance() throws IOException {
        if(data == null)
            data = new Data() ;
        return data ;
    }
    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Data.users = users;
    }
    public void add(User user) throws IOException {
        users.add(user) ;
//        System.out.println(users.get(0).getUsername());
        saveToJson();
        return ;
    }

    public void saveToJson() throws IOException {
        try {
            String path ="E:\\material\\2nd year\\1st semester\\programming2\\mail server\\backend\\Email-Server\\users.json";
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), users);
            loadToJson();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public ArrayList<User> loadToJson() throws IOException {
        try {
            String path ="E:\\material\\2nd year\\1st semester\\programming2\\mail server\\backend\\Email-Server\\users.json";
            ObjectMapper mapper = new ObjectMapper();

            setUsers(mapper.readValue(new File(path), new TypeReference<ArrayList<User>>(){}));
//            for (User u : users){
//                System.out.println(u.getUsername());
//            }
            return getUsers();
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }
}
