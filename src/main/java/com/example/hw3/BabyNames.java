package com.example.hw3;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

//GUI is pretty much in place, you will need to add EventHandling.  See the NCAA example for referencce.
public class BabyNames extends Application {

    Statement stmt;
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
//        initializeDB();

        BorderPane bp = new BorderPane();

        RadioButton boy = new RadioButton("Boy");
        RadioButton girl = new RadioButton("Girl");
        RadioButton either = new RadioButton("Both");
        ToggleGroup genderGroup = new ToggleGroup();
        boy.setToggleGroup(genderGroup);
        girl.setToggleGroup(genderGroup);
        either.setToggleGroup(genderGroup);

        VBox gender = new VBox(3);
        gender.setPadding(new Insets(5,5,5,5));
        // gender.setStyle("=fx-border-width: 2px; -fx-border-color: green");
        gender.getChildren().addAll(boy, girl, either);
        bp.setTop(gender);

        TextArea taResults = new TextArea();
        ScrollPane sp = new ScrollPane(taResults);
        Label results = new Label("Results");
        BorderPane resultPane = new BorderPane();
        resultPane.setTop(results);
        resultPane.setBottom(sp);
        bp.setBottom(resultPane);
        BorderPane masterPane = new BorderPane();
        masterPane.setCenter(bp);

        ComboBox<String> letter = new ComboBox <>();
        letter.setPrefWidth(200);

        Button search = new Button("Search");
        BorderPane fl = new BorderPane();
        fl.setCenter(letter);
        fl.setTop(new Label("Starts with..."));
        fl.setBottom(search);


        String[] alphabet={"A","B", "C", "D", "E", "F", "G","H","I","J","K","L","M","N","O","P","Q",
                "R","S","T","U","V","W","X","Y","Z"};
        ObservableList<String> items = FXCollections.observableArrayList(alphabet);
        letter.getItems().addAll(items);

        BorderPane yearList = new BorderPane();

        ComboBox<String> yearDropDown = new ComboBox <>();
        yearDropDown.setPrefWidth(200);
        String[] yr = {"2001", "2002", "2003","2004", "2005", "2006", "2007", "2008", "2009", "2010"};
        ObservableList<String> yritems = FXCollections.observableArrayList(yr);
        yearDropDown.getItems().addAll(yr);
        yearList.setCenter(yearDropDown);
        yearList.setTop(new Label("Year or interest"));
        yearList.setBottom(search);

        VBox vb = new VBox();
        vb.getChildren().addAll(fl, yearList);
        bp.setCenter(vb);


        Scene scene = new Scene(masterPane, 200, 400);
        primaryStage.setTitle("Baby Name Widget");
        primaryStage.setScene(scene);
        primaryStage.show();

		//event handling
        EventHandler<ActionEvent> eventHandler = e->{
            taResults.clear();
            char c = letter.getValue().charAt(0);  //letter selected in drop down box.
            int y = Integer.parseInt(yearDropDown.getValue());
            String queryString = null;
            int rcount = 0;
            try {
        	    Class.forName("com.mysql.cj.jdbc.Driver");
        	    System.out.println("Driver loaded!");
        	    char g1 = 'M';
        	    System.out.println("SELECT * FROM babyname WHERE byear="+y+" AND gender="+g1+" AND bname LIKE "+c+"%;");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/babynames", "root", "");
           
            if (boy.isSelected()) {

                System.out.println("boy selected" + c);
                char g = 'M';
                //#1 queryString is set to string that is mysql statement to
                //find all boys in y with names that start with c
                String query = "SELECT * FROM babyname WHERE byear='"+y+"' AND gender='"+g+"' AND bname LIKE '"+c+"%'";
                // create the java statement
                Statement st = connection.createStatement();
                // execute the query
                ResultSet rs = st.executeQuery(query);
                // iterate through the java resultset
                while (rs.next())
                {
                  String Name = rs.getString("bname");
                  rcount++;
                  taResults.appendText(Name+"\n");
                  taResults.setScrollTop(Double.MAX_VALUE);
                }
            }
            else if (girl.isSelected()){

                System.out.println("Girl selected");
                //#2 queryString is set to string that is mysql statement to
                //find all girls in y with names that start with c
                char g = 'F';
                //#1 queryString is set to string that is mysql statement to
                //find all boys in y with names that start with c
                String query = "SELECT * FROM babyname WHERE byear='"+y+"' AND gender='"+g+"' AND bname LIKE '"+c+"%'";
                // create the java statement
                Statement st = connection.createStatement();
                // execute the query
                ResultSet rs = st.executeQuery(query);
                // iterate through the java resultset
                while (rs.next())
                {
                  String Name = rs.getString("bname");
                  rcount++;
                  taResults.appendText(Name+"\n");
                  taResults.setScrollTop(Double.MAX_VALUE);
                }

            }
            else{
                //#3 queryString is set to string that is mysql statement to
                //find all boys and girls in y with names that start with c
                System.out.println("Either");
                
                //#1 queryString is set to string that is mysql statement to
                //find all boys in y with names that start with c
                String query = "SELECT * FROM babyname WHERE byear='"+y+"' AND bname LIKE '"+c+"%'";
                // create the java statement
                Statement st = connection.createStatement();
                // execute the query
                ResultSet rs = st.executeQuery(query);
                // iterate through the java resultset
                while (rs.next())
                {
                  String Name = rs.getString("bname");
                  rcount++;
                  taResults.appendText(Name+"\n");
                  taResults.setScrollTop(Double.MAX_VALUE);
                }
            }

           // try {  //#4
                
                //display the list of names in taResults

                System.out.println("row count" + rcount); //this is just to check that displayed number of rows returned
          //  } catch (SQLException ex) {
           //     ex.printStackTrace();
         //   }


            } catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            };
        search.setOnAction(eventHandler);  //associate event handling on the search button.


    }//end start

    private void makeQuery(){
    }

    void initializeDB() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/babynames", "root", "");
        stmt = connection.createStatement();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
