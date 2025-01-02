package com.example.Genesis.Project;

public class LoadDriver {
    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        }
    }
}
