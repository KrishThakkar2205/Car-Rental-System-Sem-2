package com.cls;

import java.sql.*;
import java.util.*;
public class Test {
	private static final String owner_id="owner@gmail.com";
	private static final int owner_pass=2345;
	private static final int user_pass=1234;
	private static final String user_id= "xyz@gmail.com";
	static boolean whoIsUsing;
	public static void main(String[] args) {
		
		Connection connect=JdbcConeection.getConnection();
		Scanner sc=new Scanner(System.in);
		Scanner StrSc=new Scanner(System.in);
		if(connect==null){
			System.out.println("Not Connected:");
		}
		else{
			System.out.println("Welcome to the Car Rental System");
		}
		try {
			for(int i=0;i<=3;i++){
				if(i==3){
					System.out.println("3 trail finished try later :-(");
					System.exit(0);

				}
				System.out.print("enter the ID: ");
				String id=StrSc.nextLine();
				if(id.equals(user_id)){
					whoIsUsing=false;
				}
				else if(id.equals(owner_id)){
					whoIsUsing=true;
				}
				else{
					System.out.println("Invalid id Try again");
					continue;
				}
				if(whoIsUsing==true){
					System.out.print("Enter the Owner Password: ");
					int password=sc.nextInt();
					if(password==owner_pass){
						System.out.println("Welcome owner");
						break;
					}
					else{
						System.out.println("try again");
					}
				}
				else if(whoIsUsing==false){
					System.out.print("Enter the Staff Password: ");
					int password=sc.nextInt();
					if(password==user_pass){
						System.out.println("Welcome Staff");
						break;
					}
					else{
						System.out.println("try again");
					}
				}
				
			
			}
			if(whoIsUsing==false){
				while(true){
					Car car=new Car();
					Users user=new Users();
					bookingCar book=new bookingCar(connect);
					System.out.println("Staff Allowed work Menu: ");
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println("1.Add car\n2.Remove Car\n3.Display Availabe Cars\n4.Display Booked Cars\n5.Add User\n6.Remove User\n7.Get User Id\n8.Book car\n9.Return car\n10.exit");
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.print("Enter Choice: ");
					int choice=sc.nextInt();
					switch(choice){
						case 1:{
							car.addCar(connect);
							break;
						}
						case 2:{
							car.removeCar(connect);
							break;
						}
						case 3:{
							car.displayAvailableCar(connect);
							break;
						}
						case 4:{
							car.bookedCar(connect);
							break;
						}
						case 5:{
							user.addUser(connect);
							break;
						}
						case 6:{
							user.removeUser(connect);
							break;
						}
						case 7:{
							user.getUserId(connect);
							break;
						}
						case 8:{
							book.bookCar();
							break;
						}
						case 9:{
							book.returnCar();
							break;
						}
						case 10:{
							sc.close();
							StrSc.close();
							System.exit(0);
						}
						default:{
							System.out.println("Choice don't Exist");
							break;
						}
					}
				}
			}
			else{
				while(true){
					System.out.println();
					Car car=new Car();
					Users user=new Users();
					bookingCar book=new bookingCar(connect);
					System.out.println("Owner Allowed work Menu: ");
					System.out.println("--------------------------------------------------------------------------------------");
					System.out.println("1.Add car\n2.Remove Car\n3.Display Availabe Cars\n4.Display Booked Cars\n5.Add User\n6.Remove User\n7.Get User Id\n8.Book car\n9.Return car\n10.Get Accounting\n11.exit");
					System.out.println("---------------------------------------------------------------------------------------");
					System.out.print("Enter Choice: ");
					int choice=sc.nextInt();
					switch(choice){
						case 1:{
							car.addCar(connect);
							break;
						}
						case 2:{
							car.removeCar(connect);
							break;
						}
						case 3:{
							car.displayAvailableCar(connect);
							break;
						}
						case 4:{
							car.bookedCar(connect);
							break;
						}
						case 5:{
							user.addUser(connect);
							break;
						}
						case 6:{
							user.removeUser(connect);
							break;
						}
						case 7:{
							user.getUserId(connect);
							break;
						}
						case 8:{
							book.bookCar();
							break;
						}
						case 9:{
							book.returnCar();
							break;
						}
						case 10:{
							PreparedStatement revenue=connect.prepareStatement("Select sum(payment) from booking_car ");
							ResultSet rs=revenue.executeQuery();
							while(rs.next()){
								System.out.println("--------------------------------------------------------------------");
								System.out.println("Total ravenue Generated: "+rs.getInt("sum(payment)"));
								System.out.println("--------------------------------------------------------------------");
							}
							break;
						}
						case 11:{
							sc.close();
							StrSc.close();
							System.exit(0);
						}
						default:{
							System.out.println("Choice don't Exist");
							break;
						}
					}
				}
			}
		}catch(InputMismatchException e){
			System.out.println("-----------------------------------------------------------------");
			System.out.println("THE ENTERED DATA DOES NOT MATCH WITH THE VARIABLE DATA TYPE");

		}catch (Exception e) {
			
		}
	}
	
}