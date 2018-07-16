package br.ufc.crateus.algebra.interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.ufc.crateus.algebra.util.FXMLResources;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashController extends Application implements Initializable {
	public static Stage myStage;

	@FXML
	private JFXButton btnIniciar;
	@FXML
	private Label lbSair;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SplashController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.SPLASHSCREEN);
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

	}


	public void initialize(URL location, ResourceBundle resources) {

		btnIniciar.setOnAction(new EventHandler<ActionEvent>() {

	
			public void handle(ActionEvent event) {
				try {
					new HomeController().start(new Stage());
					myStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		lbSair.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event event) {
				myStage.close();

			}
		});

	}

}
