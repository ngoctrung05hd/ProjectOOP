package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;


public class Controller {
	@FXML
	private Circle myCircle;
	private double x;
	private double y;
	public void Up(ActionEvent e) {
		System.out.println("Up");
		myCircle.setCenterY(y=y-10);
	}
	public void Down(ActionEvent e) {
		System.out.println("Down");
		myCircle.setCenterY(y=y+10);
	}
	public void Left(ActionEvent e) {
		System.out.println("Left");
		myCircle.setCenterX(x=x-10);
	}
	public void Right(ActionEvent e) {
		System.out.println("Right");
		myCircle.setCenterX(x=x+10);
	}

}
