package com.cls;
import java.util.*;
import java.sql.*;

public class Users {
    String email_id;
    long phone_no;
    String user_name;
    Scanner sc=new Scanner(System.in);
    
    void addUser(Connection connect){
        try {
            System.out.print("Enter User Name(Name Surname): ");
            user_name=sc.nextLine();
            System.out.print("Enter email address: ");
            email_id=sc.nextLine(); 
            System.out.print("Enter Phone Numebr: ");
            phone_no=sc.nextLong();
       
            PreparedStatement prepare=connect.prepareStatement("Insert into users (user_name,email_id,phone_no) values (?,?,?);");
            prepare.setString(1,user_name);
            prepare.setString(2,email_id);
            prepare.setLong(3,phone_no);
            int ans=prepare.executeUpdate();
            if(ans>0){
                System.out.println("User Added SuccessFully");
            }
            else{
                System.out.print("Try Again: ");
            }
        }catch (InputMismatchException e){
            System.out.println("Entered data doesn't match with the varaible datatype");
            return;
        } catch (Exception e) {
            
        }
    }

    void removeUser(Connection connect){        
        try {
            System.out.print("Enter User Name(Name Surname): ");
            user_name=sc.nextLine();
            System.out.print("Enter Phone Numebr: ");
            phone_no=sc.nextLong();
            String searchName="";
        
            Statement st=connect.createStatement();
            ResultSet findUserName=st.executeQuery("select user_name from users where phone_no="+phone_no);
            while(findUserName.next()){
                if(user_name.equalsIgnoreCase(findUserName.getString("user_name"))){
                    searchName=findUserName.getString("user_name");
                    break;
                }
            }
            if(searchName.equals("")){
                System.out.println("user not found");
                return;
            }
            // System.out.println(searchName);
            PreparedStatement prepare =connect.prepareStatement("delete from users where phone_no="+phone_no+ " and user_name='"+searchName+"'");
            int ans=prepare.executeUpdate();
            if(ans>0){
                System.out.println("User removed: ");
            }
            else{
                System.out.println("try again");
            }
         }catch (InputMismatchException e){
            System.out.println("Entered data doesn't match with the varaible datatype");
            return;
        }catch (Exception e) {
            System.out.println("Try again");

        }
    }

    long getUserId(Connection connect){
        try{
            System.out.print("Enter User Name(Name Surname): ");
            user_name=sc.nextLine();
            System.out.print("Enter Phone Numebr: ");
            phone_no=sc.nextLong();
            String searchName="";
            long id=-1;
        
            Statement st=connect.createStatement();
            ResultSet findUserName=st.executeQuery("select user_name from users where phone_no="+phone_no);
            while(findUserName.next()){
                if(user_name.equalsIgnoreCase(findUserName.getString("user_name"))){
                    searchName=findUserName.getString("user_name");
                    break;
                }
            }
            if(searchName.equals("")){
                System.out.println("user not found");
                return id;
            }
            PreparedStatement prepare =connect.prepareStatement("select user_id from users where user_name='"+searchName+"' and phone_no="+phone_no);
            ResultSet ans=prepare.executeQuery();
            while(ans.next()){
                id= ans.getLong("user_id");
            }
            return id;
        }catch (InputMismatchException e){
            System.out.println("Entered data doesn't match with the varaible datatype");
            return -1;
        }catch (Exception e) {
            System.out.println("Try again");
            return -1;
        }
    }
}
