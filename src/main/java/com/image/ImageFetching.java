package com.image;

import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ImageFetching {
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver.class");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jecm7", "root", "root");
		PreparedStatement pt = con.prepareStatement("select * from image");
		ResultSet e = pt.executeQuery();
		while (e.next()) {
			System.out.println(e.getInt(1));
			FileOutputStream f = new FileOutputStream("C:\\Users\\MR.AK_INDIAN\\Desktop\\Image\\img1.jpg");
			Blob b = e.getBlob(2);
			f.write(b.getBytes(1, (int) b.length()));
			
		}
	}
}
