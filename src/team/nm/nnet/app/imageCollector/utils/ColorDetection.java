package team.nm.nnet.app.imageCollector.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import team.nm.nnet.util.Matrix;

public class ColorDetection {

	public static BufferedImage toYCbCr(BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth(null);
        int  height = bufferedImage.getHeight(null);
        Raster raster = bufferedImage.getRaster();
        float[] sample = new float[4];
        BufferedImage yCbCrBufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sample = raster.getPixel(i, j, sample);
//                int y  = (int)( 0.257   * sample[0] + 0.504   * sample[1] + 0.098   * sample[2] + 16);
                int cb = (int)( 0.148   * sample[0] - 0.291   * sample[1] + 0.439   * sample[2] + 128);
                int cr = (int)( 0.439   * sample[0] - 0.368   * sample[1] - 0.071   * sample[2] + 128);
                boolean isCr = (140<cr) && (cr<165);
                boolean isCb = (105<cb) && (cb<130);
                if( isCr || isCb) {
                    yCbCrBufImage.setRGB(i, j, 255);
                } else {
                    yCbCrBufImage.setRGB(i, j, 0);
                }
            }
        }
        return yCbCrBufImage;
    }
	
	public static Matrix<Integer> getIntegralMatrix(BufferedImage bufferedImage) {
	    BufferedImage yCbCrBufImage = toYCbCr(bufferedImage);
        int width = yCbCrBufImage.getWidth(null);
        int  height = yCbCrBufImage.getHeight(null);
        Integer[][] integral = new Integer[width][height];
        Color pixelColour = new Color(yCbCrBufImage.getRGB(0, 0));
        integral[0][0] = pixelColour.getBlue() / 255;
        for(int i = 1; i < width; i++) {
            pixelColour = new Color(yCbCrBufImage.getRGB(i, 0));
            integral[i][0] = integral[i - 1][0] + (pixelColour.getBlue() / 255);
        }
        for(int j = 1; j < height; j++) {
            pixelColour = new Color(yCbCrBufImage.getRGB(0, j));
            integral[0][j] = integral[0][j - 1] + (pixelColour.getBlue() / 255);
        }
        for (int i = 1; i < width; i++) {
            for (int j = 1; j < height; j++) {
                pixelColour = new Color(yCbCrBufImage.getRGB(i, j));
                integral[i][j] = integral[i - 1][j] + integral[i][j - 1] - integral[i - 1][j - 1] + (pixelColour.getBlue() / 255);
            }
        }
        return new Matrix<Integer>(integral, width, height);
    }
}
