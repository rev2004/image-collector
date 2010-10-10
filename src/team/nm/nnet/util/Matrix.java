package team.nm.nnet.util;

public class Matrix {

	/**
	 * Giá trị của ma trận
	 */
	private double[][] value;
	
	/**
	 * Chiều rộng của matran
	 */
	private int width;
	
	/**
	 * Chiều cao của matran
	 */
	private int height;
	
	/**
	 * Phương thức khởi tạo cho matran
	 * @param value Giá trị của ma tran
	 * @param width Chiều rộng ma tran
	 * @param height Chiều cao ma tran
	 */
	public Matrix(double[][] value, int width, int height) {
		this.value = value;
		this.width = width;
		this.height = height;
	}

	public void setValue(double[][] value) {
		this.value = value;
	}

	public double[][] getValue() {
		return value;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}
	
	
}
