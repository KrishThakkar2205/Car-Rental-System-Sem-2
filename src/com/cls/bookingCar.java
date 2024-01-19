package com.cls;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.sql.Date;
public class bookingCar {

    int car_id;
    long user_id;
    String end_date_time;
    long charge_perkm;
    long km_driven;
    String book_date;
    Scanner sc=new Scanner(System.in);
    Connection connect;
    Users u=new Users();
    Car c=new Car();
    bookingCar(Connection connect){
        this.connect=connect;
    }

    void bookCar(){
        System.out.println("1.Already a user\n2.New User");
        System.out.print("Enter the choice: ");
        int choice=sc.nextInt();
        switch(choice){
            case 1: 
                break;
            case 2:
                
                u.addUser(connect);
                break;
            default:
                System.out.println("Invalid chice");
                break;
        }
        user_id=u.getUserId(connect);
        System.out.println("list of available cars");
        ArrayList<Integer> carId=new ArrayList<>();
        c.displayAvailableCar(connect,carId);
        System.out.println();
        System.out.print("Select the car id of car  you want: ");
        car_id=sc.nextInt();
        if(!carId.contains(car_id)){
            System.out.println("Enterd CarId is not available try again :-)");
        }
        sc.nextLine();
        System.out.print("enter the Booking Date(YYYY-MM-DD): ");
        book_date =sc.nextLine();
        System.out.println();
        System.out.print("Enter the Returning date(YYYY-MM-DD): ");
        end_date_time=sc.nextLine();
        System.out.println("Enter the Charge per KM decide with user : ");
        charge_perkm=sc.nextLong();
        try {
            CallableStatement cb=connect.prepareCall("call addBooking_car(?,?,?,?,?)");
            cb.setInt(1,car_id);
            cb.setLong(2,user_id);
            cb.setDate(3, Date.valueOf(book_date));
            cb.setDate(4,Date.valueOf(end_date_time));
            cb.setLong(5,charge_perkm);
            cb.execute();
            System.out.println("Car Booked Sucessfully");
        } catch (Exception e) {
            System.out.println("Failed to book car try again");
        }
    }

    void returnCar(){
        user_id=u.getUserId(connect);
        if(user_id==-1){
            return;
        }
        
        ArrayList <Integer> carId=new ArrayList<>();

        c.bookedCar(connect,carId);
        if(carId.isEmpty()){
            System.out.println("No Booked Car ");
            return;
        }
        System.out.println("\nWhich car you have booked.");
        car_id=sc.nextInt();
        
        if(!carId.contains(car_id)){
            System.out.println("Enterd CarId is not available try again :-)");
        }
        System.out.println();
        System.out.println("Enter the Odometer Reading from the car:  ");
        long odometer=sc.nextLong();
        try {
            Statement st=connect.createStatement();
            ResultSet rs=st.executeQuery("Select odometer from car where car_id="+car_id);
            long odoRead=0;
            while(rs.next()){
                odoRead=rs.getLong("odometer");
            }

            Statement state1=connect.createStatement();
            ResultSet chrs=state1.executeQuery("select charge_perkm from booking_car where user_id="+user_id+" and car_id="+car_id+" and km_driven is null");
            while(chrs.next()){
                charge_perkm=chrs.getLong("charge_perkm");
            }

            long kmDriven=odometer-odoRead;
            System.out.println();
            System.out.println("Any penalty to be charged: ");
            long penalty=penalty();
            long payment=(charge_perkm*kmDriven);
            PreparedStatement prepare=connect.prepareStatement("update booking_Car set km_driven="+kmDriven+" , payment="+(payment+penalty)+" where user_id="+user_id+" and car_id="+car_id+" and km_driven is null");

            int ans=prepare.executeUpdate();
            prepare=connect.prepareStatement("update car set odometer="+(odoRead+kmDriven)+" , booked="+0+" where car_id="+car_id);
            ans=prepare.executeUpdate();
            if(ans>0){
                rs=st.executeQuery("select user_name from users where user_id="+user_id);
                String user_name="";
                while(rs.next()){
                    user_name=rs.getString("user_name");
                }
                String car_name="";
                rs=st.executeQuery("select name from car where car_id="+car_id);
                while(rs.next()){
                    car_name=rs.getString("name");
                }
                System.out.println("Car returned Sucessfully");
                System.out.println("The Payable Amount : "+payment);

                // below piece of code if for .txt file for bill
                File f=new File(user_name+" Bill.txt");
                f.createNewFile();
                BufferedWriter bw=new BufferedWriter(new FileWriter(f,true));
                bw.write("The Car Rental System \n\n");
                bw.write("User_name :     "+user_name);
                bw.write("\nCar_name :   "+car_name);
                bw.write("\nKM Driven :   "+kmDriven);
                bw.write("\nPenalty:    "+penalty);
                bw.write("\nAmount for km driven: "+(int)payment);
                bw.write("\n5Total payable amount: "+(int)(payment+penalty));
                System.out.println("Bill Path: "+f.getAbsolutePath());
                bw.close();
            
            }
        } catch (InputMismatchException e) {
            System.out.println("Input do not match with the varaible datatype try again");
            return;
        }
        catch(Exception e){

        }
        

    }

    long penalty(){
        long penalty=0;
        try {
            while(true){
                boolean flg=true;

                
                System.out.println("1.Body damage\n2.Color Damage\n3.Interior Damage\n4.Late Returning\n5.Wrong Fuel\n6.exit");
                System.out.print("Enter from Above:");
                int ch=sc.nextInt();

                switch(ch){
                    case 1:
                        penalty+=1000;
                        break;
                    case 2:
                        penalty+=2000;
                        break;
                    case 3:
                        penalty+=1500;
                        break;
                    case 4:
                        penalty+=1000;
                        break;
                    case 5:
                        penalty+=2500;
                        break;
                    case 6:
                        flg=false;
                        break;
                    default:
                        System.out.println("Invalid Choice Try Again");
                        break;
                }
                if(flg==false){
                    break;
                }
            }
            return penalty;
        } catch (InputMismatchException e) {
            System.out.println("Entered datatype does not match wiht the varable data Type");
            return penalty;
        }
    }
}
