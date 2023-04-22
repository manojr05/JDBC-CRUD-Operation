package com.image;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ImageInsertion {
	public static void main(String[] args) throws Exception {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jecm7","root","root");
		PreparedStatement pt = connection.prepareStatement("insert into image value(?,?)");
		pt.setInt(1, 1);
		FileInputStream f = new FileInputStream("C:\\Users\\MR.AK_INDIAN\\Desktop\\Flower.jpg");
		pt.setBinaryStream(2, f, f.available());
		pt.executeUpdate();
	}
}
