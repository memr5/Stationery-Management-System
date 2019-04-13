package StationaryPackage;

import Authentication.Authentication;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StationaryClass implements Pages{

    private String user_name;

    public void Banner(){
        System.out.print  ("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n" +
                           "---------------------STATIONARY-MANAGEMENT-SYSTEM--------------------\n\n" +
                           "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n\n");
    }

    public void Menu(){
        System.out.print  ("---------------------------------MENU--------------------------------\n\n" +
                           "\t\t1. Search\n" +
                           "\t\t2. Your Orders\n" +
                           "\t\t3. Cart\n" +
                           "\t\t4. Logout\n" +
                           "\t\t5. Exit" +
                           "---------------------------------------------------------------------\n\n" +
                           "\t\tEnter your choice : ");
    }


    public void WelcomePage(){
        this.Banner();
        System.out.print  ("\t\t1. LOGIN\n" +
                           "\t\t2. SIGN UP\n" +
                           "\t\t3. EXIT\n\n" +
                           "\t\tEnter your choice : ");
    }

    public boolean Login() throws SQLException {

        String[] credentials = this.getCredentials();
        String user_name = credentials[0];
        String password = credentials[1];

        Connection conn = Authentication.connect();
        Statement st = conn.createStatement();
        ResultSet rst = st.executeQuery("SELECT users_name FROM users WHERE user_name IS " + user_name);
        //Add code to detect if user exist or not then check if the password is correct or not
        if(rst.next()) {
            if(rst.getString(2).equals(password)) {
                this.user_name = user_name;
                return true;
            }
            System.out.println("WRONG PASSWORD!");
        }

        return false;
    }

    public boolean Sign_up() throws SQLException{

        String[] credentials = this.getCredentials();
        String user_name = credentials[0];
        String password = credentials[1];

        Connection conn = Authentication.connect();
        Statement st = conn.createStatement();
        ResultSet rst = st.executeQuery("SELECT users_name FROM users WHERE user_name IS " + user_name);

        if(rst.next()){
            System.out.println("User Name already exists!");
            return false;
        }
        else {
            this.user_name = user_name;
            //Add code to insert new user record in user table
            return true;
        }
    }

    private String[] getCredentials(){

        Scanner in = new Scanner(System.in);
        System.out.print("\t\tUser_name : ");
        String user_name = in.nextLine();
        System.out.print("\t\tPassword : ");
        String password = in.nextLine();
        in.close();

        String[] credentials = new String[2];
        credentials[0] = user_name;
        credentials[1] = password;

        return credentials;
    }
}