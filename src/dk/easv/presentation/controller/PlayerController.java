package dk.easv.presentation.controller;

import dk.easv.entities.*;
import dk.easv.presentation.model.AppModel;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.*;


public class PlayerController implements Initializable {
    private AppModel model;
    private long timerStartMillis = 0;
    private String timerMsg = "";

    @FXML
    private Button btnSimilar;

    @FXML
    private Button btnTopMovies;

    @FXML
    private Button btnTopPicks;

    @FXML
    private Button btnTopUser;


    @FXML
    private ListView<Movie> lvTopMovie;

    @FXML
    private ListView<TopMovie> lvTopReco;

    @FXML
    private ListView<UserSimilarity> lvTopSimilar;

    @FXML
    private ListView<Movie> lvTopUser;

    @FXML
    private ListView<User> lvUser;

    @FXML
    private StackPane stacky;

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
        lvTopSimilar.setItems(model.getObsSimilarUsers());
        lvTopReco.setItems(model.getObsTopMoviesSimilarUsers());
        lvTopMovie.setItems(model.getObsTopMovieNotSeen());

        lvTopMovie.setVisible(false);
        lvTopReco.setVisible(false);
        lvTopSimilar.setVisible(false);


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
        lvTopMovie.setVisible(false);
        lvTopReco.setVisible(true);
        lvTopSimilar.setVisible(false);
        lvTopUser.setVisible(false);
    }

    public void handleSimilar(ActionEvent actionEvent) {
        lvTopMovie.setVisible(false);
        lvTopReco.setVisible(false);
        lvTopSimilar.setVisible(true);
        lvTopUser.setVisible(false);

    }

    public void handleTopMovies(ActionEvent actionEvent) {
        lvTopUser.setVisible(false);
        lvTopMovie.setVisible(true);
        lvTopReco.setVisible(false);
        lvTopSimilar.setVisible(false);
    }

    public void handleTopUser(ActionEvent actionEvent) {
        lvTopUser.setVisible(true);
        lvTopMovie.setVisible(false);
        lvTopReco.setVisible(false);
        lvTopSimilar.setVisible(false);
    }
}
