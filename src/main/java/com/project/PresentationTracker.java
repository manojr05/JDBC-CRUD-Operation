package com.project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class PresentationTracker {

	static Scanner scc = new Scanner(System.in);
	static Scanner sccc = new Scanner(System.in);
	static Scanner scccc = new Scanner(System.in);
	static Scanner sccccc = new Scanner(System.in);
	public static String YELLOW = "\u001B[33m";
	public static String RESET = "\u001B[0m";

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jecm7", "root", "root");
		System.out.println(YELLOW
						+ "*****NOTE: please insert the seal and presentation photos in the respected "
						+ "folders in the desktop before starting*****"+ RESET);
		boolean flag = true;
		while (flag) {
			Scanner s = new Scanner(System.in);
			System.out.println("\n\n" + "Enter 0 to terminate:\n"
					+ "Enter 1 if you want to insert the data into the presentaton table: \n"
					+ "Enter 2 to display the details upon date: \n" + "Enter 3 to update the date: \n"
					+ "Enter 4 to update the presentation image: \n"
					+ "Enter 5 to remove the details based upon date:\n");
			int x = s.nextInt();
			switch (x) {
			case 0:
				System.out.print("Thank You for using the Application");
				flag = false;
				break;
			case 1:
				Scanner sc = new Scanner(System.in);
				PreparedStatement p = con.prepareStatement("insert into presentationtracking value(?,?,?,?,?,?,?,?)");
				System.out.print("Enter the ID:");
				p.setInt(1, sc.nextInt());
				System.out.print("Enter the User Name:");
				p.setString(2, scc.nextLine());
				System.out.print("Enter the Topic Name:");
				p.setString(3, sccc.next());
				System.out.print("Enter the Date in " + YELLOW + "DD/MM/YYYY" + RESET + " format:");
				p.setString(4, sc.next());
				System.out.print("Enter the Start Time in " + YELLOW + " HH:MM(AM/PM)" + RESET + " format:");
				p.setString(5, scccc.nextLine());
				System.out.print("Enter the End Time in " + YELLOW + " HH:MM(AM/PM)" + RESET + " format:");
				p.setString(6, sccccc.nextLine());
				System.out.print("Enter the Name of the photo in Presentation Folder:");
				String name = sc.next();
				String path = "C:\\Users\\MR.AK_INDIAN\\Desktop\\JDBCProject\\Presentation\\" + name + ".jpg";
				FileInputStream fp = new FileInputStream(path);
				p.setBinaryStream(7, fp, fp.available());
				System.out.print("Enter the Name of the photo in Seal Folder:");
				String fname = sc.next();
				String fpath = "C:\\Users\\MR.AK_INDIAN\\Desktop\\JDBCProject\\Seal\\" + fname + ".jpg";
				FileInputStream fs = new FileInputStream(fpath);
				p.setBinaryStream(8, fs, fp.available());
				int z = p.executeUpdate();
				System.out.println(z + " rows affected");
				break;

			case 2:
				Scanner sc2 = new Scanner(System.in);
				PreparedStatement psa = con.prepareStatement("select * from presentationtracking where date=?");
				System.out.print("Enter the date in " + YELLOW + "DD/MM/YYYY" + RESET
						+ " format of which the details to be displayed:");
				psa.setString(1, sc2.next());
				ResultSet e = psa.executeQuery();
				while (e.next()) {
					System.out.printf("%d\t%-8s\t%-8s\t%-8s\t%-8s\t%-8s\n", e.getInt(1), e.getString(2), e.getString(3),
							e.getString(4), e.getString(5), e.getString(6));
					String oppathp = "C:\\Users\\MR.AK_INDIAN\\Desktop\\JDBCProject\\Result\\" + "presentation" + e.getInt(1)
							+ ".jpg";
					String oppaths = "C:\\Users\\MR.AK_INDIAN\\Desktop\\JDBCProject\\Result\\" + "seal" + e.getInt(1) + ".jpg";

					FileOutputStream f1 = new FileOutputStream(oppathp);
					Blob b1 = e.getBlob(7);
					f1.write(b1.getBytes(1, (int) b1.length()));
					FileOutputStream f2 = new FileOutputStream(oppaths);
					Blob b2 = e.getBlob(8);
					f2.write(b2.getBytes(1, (int) b2.length()));
				}
				System.out.println(YELLOW
						+ "****Please open the Result folder in desktop to view the presetation's and seal's photo\n"
						+ "and it will be renamed as 'presentation' with your id(eg: presentation1/seal1 where 1 is your id)****"
						+ RESET);
				break;

			case 3:
				Scanner sc3 = new Scanner(System.in);
				PreparedStatement preparedStatement = con
						.prepareStatement("update presentationtracking set date=? where id=?");
				System.out.print(
						"Enter the date which has to be changed in " + YELLOW + "in (DD/MM/YYYY format)" + RESET + ":");
				preparedStatement.setString(1, sc3.next());
				System.out.print("Enter the ID to which the date has to be changed:");
				preparedStatement.setInt(2, sc3.nextInt());
				int z1 = preparedStatement.executeUpdate();
				System.out.println(z1 + " rows affected");
				break;

			case 4:
				Scanner sc4 = new Scanner(System.in);
				PreparedStatement preparedStatement1 = con
						.prepareStatement("update presentationtracking set photo=? where id=?");
				System.out.print("Enter the Name of the photo in Presentation Folder:");
				String nameupdate = sc4.next();
				String pathupdate = "C:\\Users\\MR.AK_INDIAN\\Desktop\\JDBCProject\\Presentation\\" + nameupdate + ".jpg";
				FileInputStream fp1 = new FileInputStream(pathupdate);
				preparedStatement1.setBinaryStream(1, fp1, fp1.available());
				System.out.print("Enter the ID to which the photo has to be changed:");
				preparedStatement1.setInt(2, sc4.nextInt());
				int z2 = preparedStatement1.executeUpdate();
				System.out.println(z2 + " rows affected");
				break;

			case 5:
				Scanner sc5 = new Scanner(System.in);
				PreparedStatement ps = con.prepareStatement("delete from presentationtracking where date=?");
				System.out.print("Enter presentation date " + YELLOW + "in (DD/MM/YYYY format)" + RESET
						+ " of which details has to be deleted:");
				ps.setString(1, sc5.next());
				int a = ps.executeUpdate();
				System.out.println(a + " rows affected");
				break;

			default:
				System.out.print("Enter the valid option....");
				break;
			}
		}
		con.close();
	}
}
