package team.nm.nnet.app.imageCollector.utils;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import team.nm.nnet.util.Matrix;

public class ColorDetection {

	public static Matrix<Integer> toYCbCr(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth(null);
		int  height = bufferedImage.getHeight(null);
		Raster raster = bufferedImage.getRaster();
        float[] sample = new float[3];
        Integer[][] arr = new Integer[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sample = raster.getPixel(j, i, sample);
//                int y  = (int)( 0.257   * sample[0] + 0.504   * sample[1] + 0.098   * sample[2] + 16);
        		int cb = (int)( 0.148   * sample[0] - 0.291   * sample[1] + 0.439   * sample[2] + 128);
        		int cr = (int)( 0.439   * sample[0] - 0.368   * sample[1] - 0.071   * sample[2] + 128);
        		boolean isCr = (140<cr) && (cr<165);
        		boolean isCb = (105<cb) && (cb<130);
        		if( isCr || isCb) {
        			arr[j][i] = 255;
        		} else {
        			arr[j][i] = 0;
        		}
            }
        }
		return new Matrix<Integer>(arr, width, height);
	}
}
