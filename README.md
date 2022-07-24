# javafx-and-xampp

😂😂 I knew you'dd be looking for this!

Javafx application to read data from a URL and update an sql database.  The 1000 most popular boys and girls names from 2001 can be found here: http://liveexample.pearsoncmg.com/data/babynamesranking2001.txt  There is a similar page for years 2002 through 2010.  You will need to read in the data and update your database for each of the 10 years.

In BabyNamesPopulateDB the for loop goes thru the years, and the while loop takes care of reading in the names for a particular year, based on the loop invariant year. After you have read in your data, go to workbench and make sure it looks like it was read correctly.

Preliquisites
MySQL and MySQL Workbench
Attached Files:
File mysql-connector-java-8.0.18.jar (2.223 MB)
1. MySQL Workbench installer
Go here https://dev.mysql.com/downloads/workbench/ and download and run the installer.
 
2. MySQL Server download
https://dev.mysql.com/downloads/windows/installer/8.0.html  I set it up so that MySQL is always running.  You can check status and launch this service by typing "Services" in your search box, look for MySQL80.  If it is not running, you can choose run. If the service is not running, you will not be able to connect to any database.
I chose easy name/password since this is just for classwork.   Username: root  Password: root
3. Download the attached Jar file i.e "mysql-connector-java-8.0.18.jar". You need this file for Java programs to be able to find your SQL Server. Download the jar and place in a folder where you can easily find it.  (I placec mine in my code folder for this class)
Here is a helpful video on downloading and installing https://www.youtube.com/watch?v=u96rVINbAUI   (~6 minutes)
