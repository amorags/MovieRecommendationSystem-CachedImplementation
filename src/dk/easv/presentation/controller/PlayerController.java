package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.*;


public class PlayerController implements Initializable {
    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";

    private Movie movie;

    private UserSimilarity userSimilarity;

    private TopMovie topMovie;

    @FXML
    private Button btnSimilar;

    @FXML
    private Button btnTopMovies;

    @FXML
    private Button btnTopPicks;

    @FXML
    private Button btnTopUser;

    @FXML
    private ListView<User> lvUser;


    @FXML
    private ListView<Movie> lvTopUser;

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @FXML
    private ListView<UserSimilarity> lstTopUser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setModel(AppModel model) {

        this.model = model;
        lvUser.setItems(model.getObsUsers());
        lvTopUser.setItems(model.getObsTopMovieSeen());



        startTimer("Load users");
        model.loadUsers();
        stopTimer();




        lvUser.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldUser, selectedUser) -> {
                    startTimer("Loading all data for user: " + selectedUser);
                    model.loadData(selectedUser);
                });

        // Select the logged-in user in the listview, automagically trigger the listener above
        lvUser.getSelectionModel().select(model.getObsLoggedInUser());


    }


    public void handleTopPicks(ActionEvent actionEvent) {


    }

    public void handleSimilar(ActionEvent actionEvent) {


    }

    public void handleTopMovies(ActionEvent actionEvent) {


    }

    public void handleTopUser(ActionEvent actionEvent) {


    }
}
