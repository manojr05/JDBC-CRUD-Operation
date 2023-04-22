package com.DynamicInput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jecm7", "root", "root");
		System.out.println("Enter 0 to terminate:\n" + "Enter 1 if you want to insert the data into the table: \n"
				+ "Enter 2 to update the address: \n" + "Enter 3 to delete the details: \n"
				+ "Enter 4 to fetch the particular details: \n" + "Enter 5 to fetch all the details:\n");

		boolean flag = true;
		while (flag) {
			int x = sc.nextInt();
			switch (x) {
			case 0:
				flag = false;
				break;
			case 1:
				PreparedStatement p = con.prepareStatement("insert into details value(?,?,?)");
				System.out.print("Enter the ID:");
				p.setInt(1, sc.nextInt());
				System.out.print("Enter the Name:");
				p.setString(2, sc.next());
				System.out.print("Enter the Address:");
				p.setString(3, sc.next());
				int y = p.executeUpdate();
				System.out.println(y + " rows affected");
				break;

			case 2:
				PreparedStatement preparedStatement = con.prepareStatement("update details set addr=? where id=?");
				System.out.print("Enter the address to be changed:");
				preparedStatement.setString(1, sc.next());
				System.out.print("Enter the ID to which the address has to be changed:");
				preparedStatement.setInt(2, sc.nextInt());
				int z = preparedStatement.executeUpdate();
				System.out.println(z + " rows affected");
				break;

			case 3:
				PreparedStatement ps = con.prepareStatement("delete from details where id=?");
				System.out.println("Enter id whose details to be deleted:");
				ps.setInt(1, sc.nextInt());
				int a = ps.executeUpdate();
				System.out.println(a + " rows affected");
				break;

			case 4:
				PreparedStatement psa = con.prepareStatement("select * from details where id=?");
				System.out.println("Enter the id whose details to be displayed:");
				psa.setInt(1, sc.nextInt());
				ResultSet e = psa.executeQuery();
				while (e.next()) {
					System.out.printf("%d\t%-10s%-10s", e.getInt(1), e.getString(2), e.getString(3));
				}
				break;

			case 5:
				PreparedStatement psb = con.prepareStatement("select * from details");
				ResultSet ey = psb.executeQuery();
				while (ey.next()) {
					System.out.printf("%d\t %-10s%-10s\n", ey.getInt(1), ey.getString(2), ey.getString(3));
				}
				break;

			default:
				System.out.println("Enter the valid option:");
				break;
			}
		}
		con.close();
	}
}
