//U10416025

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.security.SecureRandom;

public class BallPane extends Pane {
	
	//the ball radius
	private double radius = 15;
	
	//set balls appear position x
  	private double[] x = {
		radius, radius*2,radius*4,radius*6,radius*8, radius*10,radius*12
	};
	//set balls appear position y
	private double[] y = {
		radius*12, radius*10,radius*8,radius*6, radius*4,radius*2,radius
	};
	//x move distance
	private double[] dx = {
		1,1,1,1,1,1,1
	};
	//y move distance
	private double[] dy = {
		1,1,1,1,1,1,1
	};
	//create balls
	private Circle[] circle = {
		new Circle(x[0], y[0], radius),new Circle(x[1], y[1], radius),
		new Circle(x[2], y[2], radius),new Circle(x[3], y[3], radius),
		new Circle(x[4], y[4], radius),new Circle(x[5], y[5], radius),
		new Circle(x[6], y[6], radius)
	};
	private Timeline animation;

	//Constructor
	public BallPane() {
		//SecureRandom
		SecureRandom random = new SecureRandom();
		//add balls in pane
		for(int i = 0; i < 7; i++){
			getChildren().add(circle[i]);
			//Set ball color
			circle[i].setFill(new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1.0));
		}

		// Create an animation for moving the ball
		animation = new Timeline(
			new KeyFrame(Duration.millis(20), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play(); // Start animation
	}

	//Play method
	public void play() {
		animation.play();
	}

	//Pause method
	public void pause() {
		animation.pause();
	}

	//IncreaseSpeed method
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}

	//DecreaseSpeed method
	public void decreaseSpeed() {
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}

	//RateProperty method
	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}
  
  	//MoveBall method
	protected void moveBall() {
		// Check boundaries
		for(int i = 0; i < 7; i++){
			if (x[i] < radius || x[i] > getWidth() - radius) {
				dx[i] *= -1; // Change ball move direction
			}
			if (y[i] < radius || y[i] > getHeight() - radius) {
				dy[i] *= -1; // Change ball move direction
			}

			// Adjust ball position
			x[i] += dx[i];
			y[i] += dy[i];
			circle[i].setCenterX(x[i]);
			circle[i].setCenterY(y[i]);
		}
	}
}
