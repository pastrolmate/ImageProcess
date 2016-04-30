package i2a;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class getcurrent {
	static double start_x = 137.507647;
	static double start_y = -31.688579;
	static double end_x = 150.588373;
	static double end_y = -40.266388;
	static int row = 4468;
    static int col = 3704;

	public static void main(String[] args) throws IOException {
		BufferedImage image = ImageIO.read(new File("resource/g2009033.jpg"));//C:\\Users\\zhang\\Downloads\\NASA\\g2009033.jpg"));
		BufferedImage landtype = ImageIO.read(new File("resource/landtype_rgb.jpg"));//C:\\Users\\zhang\\Downloads\\NASA\\landtype_rgb.jpg"));
//		System.out.println(landtype.getHeight());
//		System.out.println(landtype.getWidth());
		int row2 = landtype.getWidth();
	    int col2 = landtype.getHeight();
	    
//		double input_x = 144.376618;
//		double input_y = -34.621932;
		double input_x = Double.parseDouble(args[0]);
		double input_y = Double.parseDouble(args[1]);
		
		double unitr = (end_x - start_x) / row;
		double unitc = (start_y - end_y) / col;
		double difr = (input_x - start_x) / unitr;
		double difc = (start_y - input_y) / unitc;
		int rgb = image.getRGB((int) difr +1, (int) difc +1);	
		
		double unitR = (end_x - start_x) / row2;
		double unitC = (start_y - end_y) / col2;
		double difR = (input_x - start_x) / unitR;
		double difC = (start_y - input_y) / unitC;
		int rgb_landtype = landtype.getRGB((int) difR, (int) difC);
		Color c = new Color(rgb);
		Color c_landtype = new Color(rgb_landtype);
		int green = 235 - c.getGreen();
		int blue = c_landtype.getBlue();
		getcurrent now = new getcurrent();
		now.getStatus(green, blue);
		now.startInfo(green, blue);
	}

	public String getStatus(int green, int Blue) {
		if (Blue > 50) {
			if (green < 33) {
				return "LOW";
			}
			if (33 <= green && green < 62) {
				return "Normal";
			}
			if (62 <= green && green < 92) {
				return "Good";
			}
			if (green >= 92) {
				return "Great";
			}
		}
		return "other";
	}

	//
	public void startInfo(int green, int blue) {

		if (getStatus(green, blue) == "LOW") {
			System.out.println(
					"Grass Quality: Low! WARNING: You'd better move to a new place as soon as possible since insufficient resource!");
		}
		if (getStatus(green, blue) == "Normal") {
			System.out.println("Grass Quality: Normal! Warm prompt: Be careful of the quality of your grassland and get ready for moving.");
		}
		if (getStatus(green, blue) == "Good") {
			System.out.println("Grass Quality: Good! Your livestock is living in happiness with ample resource.");
		}
		if (getStatus(green, blue) == "Great") {
			System.out.println("Grass Quality: Great! You are living in a ideal place! Enjoy your life!");
		}
		if (getStatus(green, blue) == "other") {
			System.out.println("This is not the grassland");
		}

	}
}
