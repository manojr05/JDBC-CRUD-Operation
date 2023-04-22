package com.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.aes.AES;

public class Employee {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jecm7", "root", "root");

		boolean flag = true;
		while (flag) {
			System.out.println("\n\n" + "Enter 0 to terminate:\n"
					+ "Enter 1 if you want to insert the data into the employee table: \n"
					+ "Enter 2 to update the salary: \n" + "Enter 3 to delete the employee details: \n"
					+ "Enter 4 to fetch the particular employee details: \n"
					+ "Enter 5 to fetch all the employee details:\n");
			int x = sc.nextInt();
			switch (x) {

			case 0:
				System.out.print("Terminated.......");
				flag = false;
				break;

			case 1:
				PreparedStatement p = con.prepareStatement("insert into employee value(?,?,?,?)");
				System.out.print("Enter the ID:");
				p.setInt(1, sc.nextInt());
				System.out.print("Enter the Employee Name:");
				p.setString(2, sc.next());
				System.out.print("Enter the Salary:");
				p.setDouble(3, sc.nextDouble());
				System.out.print("Enter the Age:");
				p.setInt(4, sc.nextInt());
				int y = p.executeUpdate();
				System.out.println(y + " row added");
				break;

			case 2:
				PreparedStatement preparedStatement = con.prepareStatement("update employee set sal=? where id=?");
				System.out.print("Enter the salary which has to be changed:");
				preparedStatement.setDouble(1, sc.nextDouble());
				System.out.print("Enter the ID to which the salary has to be changed:");
				preparedStatement.setInt(2, sc.nextInt());
				int z = preparedStatement.executeUpdate();
				System.out.println(z + " rows affected");
				break;

			case 3:
				PreparedStatement ps = con.prepareStatement("delete from employee where id=?");
				System.out.println("Enter employee id whose details has to be deleted:");
				ps.setInt(1, sc.nextInt());
				int a = ps.executeUpdate();
				System.out.println(a + " rows affected");
				break;

			case 4:
				PreparedStatement psa = con.prepareStatement("select * from employee where id=?");
				System.out.println("Enter the employee id whose details to be displayed:");
				psa.setInt(1, sc.nextInt());
				ResultSet e = psa.executeQuery();
				while (e.next()) {
					System.out.printf("%d\t%-10s\t%10.2f\t%d\n", e.getInt(1), e.getString(2), e.getDouble(3),
							e.getInt(4));
				}
				break;

			case 5:
				PreparedStatement psb = con.prepareStatement("select * from employee");
				ResultSet ey = psb.executeQuery();
				while (ey.next()) {
					System.out.printf("%d\t%-10s\t%10.2f\t%d\n", ey.getInt(1), ey.getString(2), ey.getDouble(3),
							ey.getInt(4));
				}
				break;

			default:
				System.out.print("Enter the valid option:");
				break;
			}
		}
		con.close();
		sc.close();
	}
}
