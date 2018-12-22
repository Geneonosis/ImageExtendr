package controller;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;
import java.util.Random;

public class MainController implements EventHandler<ActionEvent>, Initializable{

	@FXML
	Label infoLabel;
	@FXML
	Label purposeLabel;
	@FXML
	ImageView userImage;
	
	File selectedFile = null;
	Image image = null;
	String [] listOfExtensions = {".png",".gif",".jpg",".bmp"};
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		purposeLabel.setText("Please select an image file to upload, this program will let you save that image with a random assortment of extensions. because why not.");
		
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("I made it");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Pick the pic");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"));
		//Window stage;
		try {
			selectedFile = fileChooser.showOpenDialog(infoLabel.getScene().getWindow());
			System.out.println(selectedFile.getName());
		
			image = new Image("file:" + selectedFile);
			userImage.setImage(image);
			infoLabel.setText(selectedFile.getPath());
		}catch (NullPointerException e) {
			infoLabel.setText("There was no file chosen");
		}
		
	}
	
	public void saveHandle(ActionEvent arg0) {
		String str = selectedFile.getPath();
		String lastChoice = "";
		Random r = new Random();
		for(int i = 0; i < listOfExtensions.length; i++) {
			lastChoice = listOfExtensions[r.nextInt((3 - 0) + 1) + 0];
			str += lastChoice;
		}
		System.out.println(str);
		
		System.out.println(lastChoice.substring(1, lastChoice.length()));
		File outputfile = new File(str);
	    try {
	    	BufferedImage bi = ImageIO.read(selectedFile);
			ImageIO.write(bi, lastChoice.substring(1, lastChoice.length()), outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
