package br.ufc.crateus.algebra.interfaces;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;

import br.ufc.crateus.algebra.controle.GramSchmidt;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CalculatorVetoresController extends Application implements Initializable {
	public static Stage myStage;
	GramSchmidt gs = new GramSchmidt();
	DecimalFormat formato = new DecimalFormat("#.#");

	@FXML
	private Pane pAB;
	@FXML
	private GridPane gpA;
	@FXML
	private JFXButton btnCalcular;
	@FXML
	private JFXRadioButton rbOrton;
	@FXML
	private JFXRadioButton rbOrtog;
	@FXML
	private JFXButton btnMaAA;
	@FXML
	private JFXButton btnMeAA;
	@FXML
	private JFXButton btnMaLA;
	@FXML
	private JFXButton btnMeLA;
	@FXML
	private ImageView iBack;
	@FXML
	private Label lbV;

	private TextField cell;

	private int linhaA = 0;

	private int colunaA = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		CalculatorVetoresController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.CALCULATORVETORES);
		Scene scene = new Scene(pane);
		scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();

	}

	public void initialize(URL location, ResourceBundle resources) {
		Settings();

		iBack.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				myStage.close();
			}
		});

		btnMaAA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				if (colunaA == 0) {
					colunaA = 1;
				}
				linhaA++;
				gerarBase();
			}
		});

		btnMeAA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (linhaA > 0)
					linhaA--;
				gerarBase();
			}
		});

		btnMaLA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (linhaA == 0) {
					linhaA = 1;
				}
				colunaA++;
				gerarBase();
			}
		});

		btnMeLA.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (colunaA > 0) {
					colunaA--;
				}
				gerarBase();

			}
		});

		rbOrtog.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				lbV.setText("VETORES DA BASE B");
				zerarAllMatriz();
			}
		});

		rbOrton.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pAB.setVisible(true);
				lbV.setText("VETORES DA BASE B");
				zerarAllMatriz();
			}
		});

		btnCalcular.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (rbOrtog.isSelected()) {
					double[][] matr = montarBase(linhaA, colunaA, gpA);
					if (matr.length == matr[0].length) {
						lbV.setText("VETORES DA BASE C");
						exibirBase(gs.ortogonalizar(matr), gpA);
					} else {
						JOptionPane.showMessageDialog(null,
								"A dimensao dos vetores da base é diferente do numero de vetores. Tente novamente!");
					}

				} else if (rbOrton.isSelected()) {
					double[][] matr = montarBase(linhaA, colunaA, gpA);
					if (matr.length == matr[0].length) {
						lbV.setText("VETORES DA BASE C");
						exibirBase(gs.ortonormalizar(matr), gpA);
					} else {
						JOptionPane.showMessageDialog(null,
								"A dimensao dos vetores da base é diferente do numero de vetores. Tente novamente!");
					}
				}
			}
		});

	}

	public double[][] montarBase(int linha, int coluna, GridPane gp) {

		double[][] sistema = new double[linha][coluna];

		for (int j = 0; j < coluna; j++) {
			for (int i = 0; i < linha; i++) {
				if (!((TextField) gp.getChildren().get(j * linha + i)).getText().isEmpty())
					sistema[i][j] = Double.parseDouble(((TextField) gp.getChildren().get(i * coluna + j)).getText());
			}
		}

		return sistema;
	}

	public void gerarBase() {
		gpA.getChildren().clear();
		for (int i = 0; i < linhaA; i++) {
			for (int j = 0; j < colunaA; j++) {
				cell = new TextField();
				cell.setPrefWidth(30);
				cell.setPrefHeight(30);
				gpA.add(cell, i, j);
			}

		}

	}

	public void exibirBase(double[][] sistema, GridPane gp) {
		gp.getChildren().clear();
		for (int i = 0; i < sistema.length; i++) {
			for (int j = 0; j < sistema[0].length; j++) {
				cell = new TextField();
				cell.setPrefWidth(30);
				cell.setPrefHeight(30);
				cell.setText(String.valueOf(formato.format(sistema[i][j])));
				gp.add(cell, j, i);
			}

		}

	}

	public void Settings() {
		gpA.setHgap(2);
		gpA.setVgap(2);
		rbOrtog.setSelectedColor(Color.rgb(241, 87, 86));
		rbOrton.setSelectedColor(Color.rgb(241, 87, 86));

	}

	public void zerarAllMatriz() {
		linhaA = colunaA = 0;
		gpA.getChildren().clear();
	}

}
