package team.nm.nnet.util;

public class Matrix<E> {

	/**
	 * Giá trị của ma trận
	 */
	private E[][] value;
	
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
	public Matrix(E[][] value, int width, int height) {
		this.value = value;
		this.width = width;
		this.height = height;
	}

	public void setValue(E[][] value) {
		this.value = value;
	}

	public E[][] getValue() {
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
