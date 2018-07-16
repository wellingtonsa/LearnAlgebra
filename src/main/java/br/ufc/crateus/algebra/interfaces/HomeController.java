package br.ufc.crateus.algebra.interfaces;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.ufc.crateus.algebra.util.FXMLResources;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomeController extends Application implements Initializable {

	@FXML
	private JFXButton btnMatrizes;
	@FXML
	private JFXButton btnDetermiantes;
	@FXML
	private JFXButton btnSistemas;
	@FXML
	private JFXButton btnEvetoriais;
	
	
	public static Stage myStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		HomeController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.HOME);
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

	}


	public void initialize(URL location, ResourceBundle resources) {

		btnMatrizes.setOnAction(new EventHandler<ActionEvent>() {
			
		
			public void handle(ActionEvent event) {
				try {
					new MatrizesController().start(new Stage());
					myStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnDetermiantes.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				try {
					new DeterminantesController().start(new Stage());
					myStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btnSistemas.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg0) {
				try {
					new SistemasController().start(new Stage());
					myStage.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
	}
}
