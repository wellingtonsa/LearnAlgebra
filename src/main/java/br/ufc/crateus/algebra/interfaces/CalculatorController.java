package br.ufc.crateus.algebra.interfaces;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import br.ufc.crateus.algebra.controle.Matriz;
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

public class CalculatorController extends Application implements Initializable {
	public static Stage myStage;
	Matriz opmatr = new Matriz();
	DecimalFormat formato = new DecimalFormat("#.#");

	@FXML
	private Pane pAB;
	@FXML
	private Pane pC;
	@FXML
	private Pane pB;
	@FXML
	private Pane pEsc;
	@FXML
	private GridPane gpA;
	@FXML
	private GridPane gpB;
	@FXML
	private GridPane gpC;
	@FXML
	private JFXButton btnMaLB;
	@FXML
	private JFXButton btnMeLB;
	@FXML
	private JFXButton btnMaAB;
	@FXML
	private JFXButton btnMeAB;
	@FXML
	private JFXButton btnMaAA;
	@FXML
	private JFXButton btnMeAA;
	@FXML
	private JFXButton btnMaLA;
	@FXML
	private JFXButton btnMeLA;
	@FXML
	private JFXButton btnP;
	@FXML
	private JFXButton btnM;
	@FXML
	private ImageView iBack;
	@FXML
	private JFXRadioButton rbSoma;
	@FXML
	private JFXRadioButton rbSub;
	@FXML
	private JFXRadioButton rbMult;
	@FXML
	private JFXRadioButton rbMultE;
	@FXML
	private JFXRadioButton rbDet;
	@FXML
	private JFXRadioButton rbIn;
	@FXML
	private JFXRadioButton rbPot;
	@FXML
	private JFXRadioButton rbTrans;
	@FXML
	private JFXRadioButton rbCofat;
	@FXML
	private JFXRadioButton rbAdj;
	@FXML
	private JFXButton btnCalcular;
	@FXML
	private JFXTextField tfNum;
	@FXML
	private Label lbNum;

	private TextField cell;

	private int linhaA = 0;

	private int colunaA = 0;

	private int linhaB = 0;

	private int colunaB = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		CalculatorController.myStage = primaryStage;
		Pane pane = (Pane) FXMLLoader.load(FXMLResources.CALCULATORDETERMINANTES);
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

		
			public void handle(Event event) {
				myStage.close();

			}
		});

		btnM.setOnAction(new EventHandler<ActionEvent>() {

	
			public void handle(ActionEvent event) {
				if (colunaA > 0 && colunaB > 0 && linhaA > 0 && linhaB > 0) {
					colunaA--;
					colunaB--;
					linhaA--;
					linhaB--;
					GerarMatrizA();
					GerarMatrizB();
				}

			}
		});

		btnP.setOnAction(new EventHandler<ActionEvent>() {

		
			public void handle(ActionEvent event) {
				colunaA++;
				colunaB++;
				linhaA++;
				linhaB++;
				GerarMatrizA();
				GerarMatrizB();

			}
		});

		btnMaAA.setOnAction(new EventHandler<ActionEvent>() {

	
			public void handle(ActionEvent event) {
				if (colunaA == 0)
					colunaA = 1;
				linhaA++;
				GerarMatrizA();
			}
		});

		btnMeAA.setOnAction(new EventHandler<ActionEvent>() {

		
			public void handle(ActionEvent event) {
				if (linhaA > 0)
					linhaA--;
				GerarMatrizA();
			}
		});

		btnMaLA.setOnAction(new EventHandler<ActionEvent>() {


			public void handle(ActionEvent event) {
				if (linhaA == 0)
					linhaA = 1;
				colunaA++;
				GerarMatrizA();
			}
		});

		btnMeLA.setOnAction(new EventHandler<ActionEvent>() {


			public void handle(ActionEvent event) {
				if (colunaA > 0)
					colunaA--;
				GerarMatrizA();
			}
		});

		btnMaAB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (colunaB == 0)
					colunaB = 1;
				linhaB++;
				GerarMatrizB();
			}
		});

		btnMeAB.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				if (linhaB > 0)
					linhaB--;
				GerarMatrizB();
			}
		});

		btnMaLB.setOnAction(new EventHandler<ActionEvent>() {

	
			public void handle(ActionEvent event) {
				if (linhaB == 0)
					linhaB = 1;
				colunaB++;
				GerarMatrizB();
			}
		});

		btnMeLB.setOnAction(new EventHandler<ActionEvent>() {

	
			public void handle(ActionEvent event) {
				if (colunaB > 0)
					colunaB--;
				GerarMatrizB();
			}
		});

		btnCalcular.setOnAction(new EventHandler<ActionEvent>() {


			public void handle(ActionEvent arg0) {
				if (rbSoma.isSelected()) {

					double[][] mA = new double[linhaA][colunaA];
					double[][] mB = new double[linhaB][colunaB];
					mA = montarMatriz(linhaA, colunaA, gpA);
					mB = montarMatriz(linhaB, colunaB, gpB);
					if (mA.length == mB.length && mA[0].length == mB[0].length) {
						pAB.setVisible(false);
						pC.setVisible(true);
						exibirMatriz(opmatr.soma(mA, mB), gpC);
					} else {
						JOptionPane.showMessageDialog(null,
								"As matrizes não tem o mesmo tamanho. \n Verifique os valores da matriz e tente novamente!");
					}

				} else if (rbSub.isSelected()) {

					double[][] mA = new double[linhaA][colunaA];
					double[][] mB = new double[linhaB][colunaB];
					mA = montarMatriz(linhaA, colunaA, gpA);
					mB = montarMatriz(linhaB, colunaB, gpB);
					if (mA.length == mB.length && mA[0].length == mB[0].length) {
						pAB.setVisible(false);
						pC.setVisible(true);
						exibirMatriz(opmatr.subtracao(mA, mB), gpC);
					} else {
						JOptionPane.showMessageDialog(null,
								"As matrizes não tem o mesmo tamanho. \n Verifique os valores da matriz e tente novamente!");
					}

				} else if (rbMult.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					double[][] mB = new double[linhaB][colunaB];
					mA = montarMatriz(linhaA, colunaA, gpA);
					mB = montarMatriz(linhaB, colunaB, gpB);
					if (mA[0].length == mB.length) {
						pAB.setVisible(false);
						pC.setVisible(true);
						exibirMatriz(opmatr.multiplicacao(mA, mB), gpC);
					} else {
						JOptionPane.showMessageDialog(null,
								"O número de colunas da matriz A não é igual o número de linhas de B. \n Verifique os valores da matriz e tente novamente!");
					}

				} else if (rbMultE.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
					pAB.setVisible(false);
					pC.setVisible(true);
					exibirMatriz(opmatr.multiplicarEscalar(mA, Double.parseDouble(tfNum.getText())), gpC);

				} else if (rbPot.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
					pAB.setVisible(false);
					pC.setVisible(true);
					exibirMatriz(opmatr.potencia(mA, Integer.parseInt(tfNum.getText())), gpC);

				} else if (rbIn.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
					if (opmatr.determinante(0, mA) != 0.0) {
						pAB.setVisible(false);
						pC.setVisible(true);
						exibirMatriz(opmatr.inversa(mA), gpC);
					} else {
						JOptionPane.showMessageDialog(null, "Essa matriz não possui inversa!");
					}

				} else if (rbDet.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
					if (mA.length == mA[0].length) {
						tfNum.setText(String.valueOf(formato.format(opmatr.determinante(0, mA))));
					} else {
						JOptionPane.showMessageDialog(null,
								"A matriz não é NxN. \n Verifique os valores da matriz e tente novamente!");
					}
				} else if (rbTrans.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
					pAB.setVisible(false);
					pC.setVisible(true);
					exibirMatriz(opmatr.transposta(mA), gpC);
					
				} else if (rbCofat.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
						pAB.setVisible(false);
						pC.setVisible(true);
						exibirMatriz(opmatr.matrizCofatora(mA), gpC);
					 
				}
				
				else if (rbAdj.isSelected()) {
					double[][] mA = new double[linhaA][colunaA];
					mA = montarMatriz(linhaA, colunaA, gpA);
						pAB.setVisible(false);
						pC.setVisible(true);
						exibirMatriz(opmatr.matrizAdjunta(mA), gpC);
				}

			}
		});

		rbMultE.setOnMousePressed(new EventHandler<Event>() {

			public void handle(Event arg0) {
				pB.setVisible(false);
				pEsc.setVisible(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				lbNum.setText("DIGITE O ESCALAR");
				tfNum.setText("");
				tfNum.setEditable(true);
				zerarAllMatriz();

			}
		});

		rbPot.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				pB.setVisible(false);
				pEsc.setVisible(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				lbNum.setText("DIGITE O EXPOENTE");
				tfNum.setText("");
				tfNum.setEditable(true);
				zerarAllMatriz();

			}
		});

		rbSoma.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(true);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});

		rbSub.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(true);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});

		rbMult.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(true);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});

		rbDet.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(false);
				pEsc.setVisible(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				lbNum.setText("DETERMINANTE");
				tfNum.setEditable(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});

		rbIn.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(false);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});
		

		rbTrans.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(false);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});
		
		rbCofat.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(false);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});
		
		rbAdj.setOnMousePressed(new EventHandler<Event>() {


			public void handle(Event arg0) {
				lbNum.setText("");
				pB.setVisible(false);
				pEsc.setVisible(false);
				tfNum.setEditable(true);
				pAB.setVisible(true);
				pC.setVisible(false);
				tfNum.setText("");
				zerarAllMatriz();

			}
		});

	}
	
	

	public void GerarMatrizA() {
		gpA.getChildren().clear();
		for (int i = 0; i < linhaA; i++) {
			for (int j = 0; j < colunaA; j++) {
				cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(30);
				gpA.add(cell, j, i);
			}

		}

	}

	public void GerarMatrizB() {
		gpB.getChildren().clear();
		for (int i = 0; i < linhaB; i++) {
			for (int j = 0; j < colunaB; j++) {
				cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(30);
				gpB.add(cell, j, i);
			}

		}

	}

	public void Settings() {
		gpA.setHgap(2);
		gpA.setVgap(2);
		gpB.setHgap(2);
		gpB.setVgap(2);
		rbSoma.setSelectedColor(Color.rgb(241, 87, 86));
		rbSub.setSelectedColor(Color.rgb(241, 87, 86));
		rbMult.setSelectedColor(Color.rgb(241, 87, 86));
		rbMultE.setSelectedColor(Color.rgb(241, 87, 86));
		rbDet.setSelectedColor(Color.rgb(241, 87, 86));
		rbIn.setSelectedColor(Color.rgb(241, 87, 86));
		rbPot.setSelectedColor(Color.rgb(241, 87, 86));
		rbTrans.setSelectedColor(Color.rgb(241, 87, 86));
		rbCofat.setSelectedColor(Color.rgb(241, 87, 86));
		rbAdj.setSelectedColor(Color.rgb(241, 87, 86));

	}

	public double[][] montarMatriz(int linha, int coluna, GridPane gp) {

		double[][] matriz = new double[linha][coluna];

		
		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++) {
				if (!((TextField) gp.getChildren().get(i * coluna + j)).getText().isEmpty())
					matriz[i][j] = Double.parseDouble(((TextField) gp.getChildren().get(i * coluna + j)).getText());
			}
		}

		return matriz;
	}

	public void exibirMatriz(double[][] matriz, GridPane gp) {
		gp.getChildren().clear();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				cell = new TextField();
				cell.setPrefWidth(50);
				cell.setPrefHeight(30);
				cell.setText(String.valueOf(formato.format(matriz[i][j])));
				gp.add(cell, j, i);
			}

		}

	}

	public void zerarAllMatriz() {
		linhaA = linhaB = colunaA = colunaB = 0;
		gpA.getChildren().clear();
		gpB.getChildren().clear();
		gpC.getChildren().clear();
	}

}
