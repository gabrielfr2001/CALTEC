package br.com.teclogica.roskowski.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Filer {
	public static final String GLOBAL_PATH = "C:" + File.separator + "Users" + File.separator + "TECBMGFI"
			+ File.separator;

	public static void saveImage(Image img, String arquivo) {
		try {
			BufferedImage bi = Conversor.toBufferedImage(img);
			File outputfile = new File(GLOBAL_PATH + arquivo + ".png");
			ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		}
	}

	public static Image loadImage(String arquivo) {
		try {
			return (Image) ImageIO.read(new File(GLOBAL_PATH + arquivo + ".png"));
		} catch (IOException e) {
		}
		return null;
	}
}
