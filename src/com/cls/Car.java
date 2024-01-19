package com.cls;
import java.util.*;
import java.sql.*;
// import java.sql.Date;
//String.valueOf()--to the date datatype to the String
public class Car {
    int car_id;
    String color;
    String car_name;
    String car_type;
    String fuel;
    boolean booked=false;
    long odometer;
    Scanner sc=new Scanner(System.in);
    public void addCar(Connection connect){
        try {
            System.out.println("Enter Car Name(Company name Car name) : ");
            car_name=sc.nextLine();
            System.out.println("Enter color of car: ");
            color=sc.nextLine();
            System.out.println();
            while (true){
                boolean flg=false;
                System.out.println("Select Fuel type");
                System.out.println("1.Petrol\n2.Diesel\n3.CNG\n4.Electric");
                System.out.print("Select from the following: ");
                int ch=sc.nextInt();
                switch(ch){
                    case 1:
                        flg=true;
                        fuel="Petrol";
                        break;
                    case 2:
                        flg=true;
                        fuel="Diesel";
                        break;
                    case 3:
                        flg=true;
                        fuel="CNG";
                        break;
                    case 4:
                        flg=true;
                        fuel="Electric";
                        break;
                    default:
                        System.out.println("try again :-)");    
                        break;
                }
                if(flg==true){
                    break;
                }

            }
            System.out.println();
            
            while (true){
                boolean flg=false;
                System.out.println("Select car type");
                System.out.println("1.SUV\n2.Sedan\n3.HatchBack");
                System.out.print("Enter from the Following: ");
                int ch=sc.nextInt();
                switch(ch){
                    case 1:
                        flg=true;
                        car_type="SUV";
                        break;
                    case 2:
                        flg=true;
                        car_type="Sedan";
                        break;
                    case 3:
                        flg=true;
                        car_type="HatchBack";
                        break;
                    default:
                        System.out.println("try again :-)");    
                        break;
                }
                if(flg==true){
                    break;
                }

            }
            System.out.println();
            System.out.print("Enter the Initial odometer reading: ");
            odometer=sc.nextLong();
        
            PreparedStatement prepare=connect.prepareStatement("Insert into car (name,color,fuel,car_type,booked,odometer) values (?,?,?,?,?,?)");
            prepare.setString(1, car_name);
            prepare.setString(2, color);
            prepare.setString(3,fuel);
            prepare.setString(4,car_type);
            prepare.setBoolean(5, booked);
            prepare.setLong(6, odometer);
            int ans=prepare.executeUpdate();
            if(ans>=0){
                System.out.println("Car added Sucesfully");
            } else{
                System.out.println("Failed try again");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entered values Does not match the DataType");
            return;
        }
        catch(Exception e){
            System.out.println("System Error Occoured try again");
            System.out.println(e);
            
        }
    }

    void removeCar(Connection connect){
        try {
            ArrayList<Integer> carID=new ArrayList<Integer>();
            displayAvailableCar(connect, carID);
            if(carID.isEmpty()){
                System.out.println("You cannot delete car becoz all the cars are booked");
                return;
            }
            System.out.println("Enter the CAR ID from the Following: ");
            car_id=sc.nextInt();
            if(carID.contains(car_id)){
                PreparedStatement prepare=connect.prepareStatement("Delete from car where car_id="+car_id);
                int ans=prepare.executeUpdate();
                if(ans>0){
                    System.out.println("Car deleted Sucessfully");
                    return;
                }
            }    
        } catch (InputMismatchException e) {
            System.out.println("Entered values Does not match the DataType");
            return;
        }
        catch(Exception e){
            System.out.println("System Error Occoured try again");
            System.out.println(e);
            
        }

    }

    void displayAvailableCar(Connection connect){
        try {
            Statement st=connect.createStatement();
            ResultSet rs=st.executeQuery("Select*from car");
            while(rs.next()){
                if(rs.getBoolean("booked")==false){
                    System.out.println(rs.getInt("car_id")+"-->"+rs.getString("name")+" "+rs.getString("color")+" "+rs.getString("fuel"));
                }
            }
        
        }
        catch(Exception e){
            System.out.println("System Error Occoured try again");
            System.out.println(e);
            
        }
    }

    void displayAvailableCar(Connection connect,ArrayList<Integer>carId){
        try {
            Statement st=connect.createStatement();
            ResultSet rs=st.executeQuery("Select*from car where booked=false");
            while(rs.next()){
                
                    carId.add(rs.getInt("car_id"));
                    System.out.println(rs.getInt("car_id")+"-->"+rs.getString("name")+" "+rs.getString("color")+" "+rs.getString("fuel"));
                
            }
        
        }
        catch(Exception e){
            System.out.println("System Error Occoured try again");
            System.out.println(e);
            
        }
    }

    void bookedCar(Connection connect)
    {
       try {
            Statement st=connect.createStatement();
            ResultSet rs=st.executeQuery("Select*from car booekd=true");
            while(rs.next()){
                if(rs.getBoolean("booked")==true){
                    System.out.println(rs.getInt("car_id")+"-->"+rs.getString("name")+" "+rs.getString("color")+" "+rs.getString("fuel"));
                }
            }
        
        }
        catch(Exception e){
            System.out.println("System Error Occoured try again");
            System.out.println(e);
            
        }
    }

    void bookedCar(Connection connect,ArrayList<Integer> carId)
    {
       try {
            Statement st=connect.createStatement();
            ResultSet rs=st.executeQuery("Select*from car where booked=true");
            while(rs.next()){
               
                carId.add(rs.getInt("car_id"));
                System.out.println(rs.getInt("car_id")+"-->"+rs.getString("name")+" "+rs.getString("color")+" "+rs.getString("fuel"));
                
            }
        
        }
        catch(Exception e){
            System.out.println("System Error Occoured try again");
            System.out.println(e);
            
        }
    }
}
