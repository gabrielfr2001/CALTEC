package br.com.teclogica.roskowski.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Conversor {
	public static byte[] toByteArray(Image image) {
		byte b[] = new byte[image.getWidth(null) * image.getHeight(null) * 3 + 8];
		int t = 8;
		byte[] b1 = toBytes(image.getWidth(null));
		b[0] = b1[0];
		b[1] = b1[1];
		b[2] = b1[2];
		b[3] = b1[3];
		b1 = toBytes(image.getHeight(null));
		b[4] = b1[0];
		b[5] = b1[1];
		b[6] = b1[2];
		b[7] = b1[3];
		for (int y = 0; y < image.getHeight(null); y++) {
			for (int x = 0; x < image.getWidth(null); x++) {
				int intColor = toBufferedImage(image).getRGB(x, y);
				int red = new Color(intColor).getRed();
				int green = new Color(intColor).getGreen();
				int blue = new Color(intColor).getBlue();
				b[t + 0] = (byte) (red & 0xFF);
				b[t + 1] = (byte) (green & 0xFF);
				b[t + 2] = (byte) (blue & 0xFF);
				t += 3;
			}
		}
		return b;
	}

	public static Image toImage(byte[] b) {
		byte[] a = { b[0], b[1], b[2], b[3] };
		byte[] c = { b[4], b[5], b[6], b[7] };
		int valA = toInt(a, 0);
		int valB = toInt(c, 0);
		BufferedImage bf = new BufferedImage(valA, valB, BufferedImage.TYPE_INT_RGB);
		int y = 0;
		int xx = 0;
		for (int x = 8; x < b.length; x += 3) {
			xx++;
			if (xx > valB) {
				y++;
				xx = 0;
			}
			try {
				bf.setRGB(xx, y, getIntFromColor(b[x], b[x + 1], b[x + 2]));
			} catch (Exception e) {
			}
		}
		return (Image) bf;
	}

	public static InputStream toInputStream(Image image) {
		BufferedImage bImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D bImageGraphics = bImage.createGraphics();
		bImageGraphics.drawImage(image, null, null);
		RenderedImage rImage = (RenderedImage) bImage;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(rImage, "png", os);
			InputStream fis = new ByteArrayInputStream(os.toByteArray());
			return fis;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getIntFromColor(int Red, int Green, int Blue) {
		Red = (Red << 16) & 0x00FF0000;
		Green = (Green << 8) & 0x0000FF00;
		Blue = Blue & 0x000000FF;
		return 0xFF000000 | Red | Green | Blue;
	}

	public static byte[] toBytes(int i) {
		byte[] result = new byte[4];
		result[0] = (byte) (i >> 24);
		result[1] = (byte) (i >> 16);
		result[2] = (byte) (i >> 8);
		result[3] = (byte) (i);
		return result;
	}

	public static int toInt(byte[] bytes, int offset) {
		int ret = 0;
		for (int i = 0; i < 4 && i + offset < bytes.length; i++) {
			ret <<= 8;
			ret |= (int) bytes[i] & 0xFF;
		}
		return ret;
	}

	public static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		return bimage;
	}
}
