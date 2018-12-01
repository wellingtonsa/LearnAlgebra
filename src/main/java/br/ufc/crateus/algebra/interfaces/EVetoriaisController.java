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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EVetoriaisController extends Application implements Initializable {
	public static Stage myStage;
	
	@FXML
	private ProgressIndicator piStatus;
	@FXML
	private Pane pAulas;
	@FXML
	private Pane pExercicios;
	@FXML
	private Pane pMenu;
	@FXML
	private ImageView iBack;
	@FXML
	private ImageView iCalculator;
	@FXML
	private JFXButton btnAulas;
	@FXML
	private JFXButton btnExercicios;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		EVetoriaisController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.EVETORIAIS);
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);

		primaryStage.show();

	}


	public void initialize(URL location, ResourceBundle resources) {
		piStatus.setProgress(0.1);

		btnAulas.setOnAction(new EventHandler<ActionEvent>() {

	
			public void handle(ActionEvent event) {
				pMenu.setVisible(false);
				pAulas.setVisible(true);

			}
		});

		btnExercicios.setOnAction(new EventHandler<ActionEvent>() {


			public void handle(ActionEvent event) {
				pMenu.setVisible(false);
				pExercicios.setVisible(true);

			}
		});

		iBack.setOnMousePressed(new EventHandler<Event>() {

		
			public void handle(Event event) {
				if (pAulas.isVisible()) {
					pMenu.setVisible(true);
					pAulas.setVisible(false);
				} else if (pExercicios.isVisible()) {
					pMenu.setVisible(true);
					pExercicios.setVisible(false);
				} else if (pMenu.isVisible()) {
					try {
						new HomeController().start(new Stage());
					} catch (Exception e) {
						e.printStackTrace();
					}
					myStage.close();
				}

			}
		});
		
		iCalculator.setOnMousePressed(new EventHandler<Event>() {

	
			public void handle(Event event) {
				try {
					new CalculatorVetoresController().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

}
