package main.java.company.evo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private int id;
    private String name;
    private int age;
    private Sex sex;

    private static Map<Integer, User> allUsers;

    private static int countId = 0;


    public User(String name, int age, Sex sex) {
        if (allUsers == null){
            allUsers = new HashMap<>();
        }

        this.name = name;
        this.age = age;
        this.sex = sex;

        if (!hasUser()){
            countId++;
            this.id = countId;
            allUsers.put(id, this);
        }
    }

    public static List<User> getAllUsers(){
        return new ArrayList<>(allUsers.values());
    }

    private boolean hasUser(){
        for (User user : allUsers.values()){
            if (user.equals(this) && user.hashCode() == this.hashCode()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    public static List<User> getAllUsers(Sex sex){
        List<User> listAllUsers = new ArrayList<>();
        for (User user : allUsers.values()){
            if (user.sex == sex){
                listAllUsers.add(user);
            }
        }
        return listAllUsers;
    }

    public static int getHowManyUsers(){
        return allUsers.size();
    }

    public static int getHowManyUsers(Sex sex){
        return getAllUsers(sex).size();
    }


    public static int getAllAgeUsers(){
        int countAge = 0;
        for (User user : allUsers.values()){
            countAge += user.age;
        }
        return countAge;
    }

    public static int getAllAgeUsers(Sex sex){
        int countAge = 0;
        for (User user : getAllUsers(sex)){
            countAge += user.age;
        }
        return countAge;
    }


    public static int getAverageAgeOfAllUsers(){
        return getAllAgeUsers() / getHowManyUsers();
    }

    public static int getAverageAgeOfAllUsers(Sex sex){
        return getAllAgeUsers(sex) / getHowManyUsers(sex);
    }


}
