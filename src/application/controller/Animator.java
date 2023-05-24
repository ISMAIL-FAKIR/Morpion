package application.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import application.data.Player;
import application.data.Tile;

public class Animator {

    // Method to animate the title image
    public static void animateTitle(ImageView title) {
        // Create a scale transition with a duration of 2 seconds
        ScaleTransition st = new ScaleTransition(Duration.millis(2000), title);
        // Scale the image by -0.2 in both X and Y directions
        st.setByX(-0.2);
        st.setByY(-0.2);
        // Enable auto-reversal of the transition
        st.setAutoReverse(true);
        // Set the cycle count to indefinite
        st.setCycleCount(Timeline.INDEFINITE);
        // Start the animation
        st.play();
    }

    // Method to animate the fading of a node
    public static void animateFadingNode(Node node, double from, double to, int durationMillis, int count) {
        // Create a fade transition with the specified duration and node
        FadeTransition ft = new FadeTransition(Duration.millis(durationMillis), node);
        // Set the start and end opacity values
        ft.setFromValue(from);
        ft.setToValue(to);
        // Enable auto-reversal of the transition
        ft.setAutoReverse(true);
        // Set the cycle count to the specified value
        ft.setCycleCount(count);
        // Start the animation
        ft.play();
    }

    // Method to animate a clicked tile
    public static void animateClickedTile(Tile tile) {
        // Create a new image view with the player's shape
        ImageView playerShape = new ImageView(tile.getPlayer().getShape());
        System.out.println(tile.getPlayer().getShape().toString());
        // Create a scale transition with a duration of 200 milliseconds
        ScaleTransition st = new ScaleTransition(Duration.millis(200), playerShape);
        // Set the start and end scale values
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1);
        st.setToY(1);
        // Start the animation
        st.play();
        // Add the image view to the tile's pane
        tile.getPane().getChildren().add(playerShape);
        // Bind the image view's width and height to the tile's pane
        playerShape.fitWidthProperty().bind(tile.getPane().widthProperty());
        playerShape.fitHeightProperty().bind(tile.getPane().heightProperty());
    }

    // Method to animate the winning line
    public static void animateWinningLine(Pane surface, double x1, double y1, double x2, double y2) {
        // Create a new line with the specified coordinates
        Line line = new Line(x1, y1, x2, y2);
        // Set the line's stroke width and color
        line.setStrokeWidth(10);
        line.setStyle("-fx-stroke:green");
        // Create a scale transition with a duration of 500 milliseconds
        ScaleTransition st = new ScaleTransition(Duration.millis(500), line);
        // Set the start and end scale values
        st.setFromX(0);
        st.setFromY(0);
        st.setToX(1.1);
        st.setToY(1.1);
        // Start the animation
        st.play();

        surface.setDisable(false);
        surface.getChildren().add(line);
    }
    
    // Method to animate the score 
    public  static void animateScore(Label label, Player player) {
        label.setText(String.valueOf(player.getScore()));
    }

    public static void changeLabel(Label label, String text, String colorCode) {
        label.setText(text);

    }
}

