package com.oliversouc;

import com.oliversouc.be.Employee;
import com.oliversouc.dal.EmployeeDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/oliversouc/gui/view/MainView.fxml"));
        primaryStage.setTitle("MovieCollection");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
