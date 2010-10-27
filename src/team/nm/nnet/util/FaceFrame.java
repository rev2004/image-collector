package team.nm.nnet.util;

public class FaceFrame {
	/**
	 * Toa do x tren cua frame
	 */
	private int x;
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Toa do y tren cua frame
	 */
	private int y;
	
	/**
	 * Chieu rong cua frame
	 */
	private int width;
	
	/**
	 * Chieu cao cua frame
	 */
	private int height;
	
	/**
	 * Khoi tao lop
	 * @param x Toa do x goc tren
	 * @param y Toa do y goc tren
	 * @param width Chieu rong khung
	 * @param height Chieu dai khung
	 */
	public FaceFrame(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	
}
