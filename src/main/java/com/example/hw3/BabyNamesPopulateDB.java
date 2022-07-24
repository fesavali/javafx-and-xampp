package com.example.hw3;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

//DON'T FORGET TO ADD the mysql jar file to your project. And delete mine.

public class BabyNamesPopulateDB {
    public static void main(String[] args) throws Exception {
    	try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	    System.out.println("Driver loaded!");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/babynames", "root", "");

 		//add the prepared statement that will insert tuple into babyname

		//declare variables
        String boyNum, girlNum;
        String boyName, girlName;
        Scanner data;

		//loop to read files
        for (int year = 2001; year <=2010; year++) {
            //go here to see how a single year of date is formatted
            //http://liveexample.pearsoncmg.com/data/babynamesranking2001.txt
            //there is a separate textfile for each year, 2001 thru 2010
            data = new Scanner(new URL(
                    "http://liveexample.pearsoncmg.com/data/babynamesranking" + year + ".txt").openStream());
            System.out.print(year + " has ");
            int count = 0;

            //loop to read process a single year and insert into db
            while (data.hasNext()){
                count++;
                data.next(); // Skip rank. It is not saved in the table.

               boyName = data.next();
               boyNum = data.next();
               girlName = data.next();
               girlNum = data.next();
//                System.out.println(data.next());
//               System.out.println(boyName + " "+ boyNum + " " + girlName + " "+ girlNum );
                // uncomment above to see if file was read correctly before writing to database.

                //insert boy variables into prepared statement and execute
               String sql = " insert into babyname (byear, bname, gender, bcount)"
            		    + " values (?, ?, ?, ?)";
               
               PreparedStatement preparedStmt = connection.prepareStatement(sql);
               preparedStmt.setInt (1, year);
               preparedStmt.setString (2, boyName);
               preparedStmt.setString (3, "M");
               preparedStmt.setString(4, boyNum); 
               
               preparedStmt.execute();

                //insert girl variables into prepared statement and execute
               String sql1 = " insert into babyname (byear, bname, gender, bcount)"
           		    + " values (?, ?, ?, ?)";
               
               PreparedStatement preparedStmt1 = connection.prepareStatement(sql1);
               preparedStmt1.setInt (1, year);
               preparedStmt1.setString (2, girlName);
               preparedStmt1.setString (3, "F");
               preparedStmt1.setString(4, girlNum); 
               
               preparedStmt1.execute();

            }
            System.out.println("count= " + count);

        }
    	} catch (ClassNotFoundException e) {
    	    throw new IllegalStateException("Cannot find the driver in the classpath!", e);
    	} 


    }

}
