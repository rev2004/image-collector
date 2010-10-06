package team.nm.nnet.core;

import java.util.Observable;
import java.util.Observer;

public class ControlFaceClassify implements Observer{
	/**
	 * Đối tượng faceclassify
	 */
	private FaceClassify faceClassify;
	
	/**
	 * Đường dẫn để lưu neural network
	 */
	private String filename;
	
	/**
	 * Contructor khởi tạo cho đối tương
	 * @param input FaceClassify cần set cho đối tượng
	 * @param filename Đường dẫn để lưu network khi học xong
	 */
	public ControlFaceClassify(FaceClassify input, String filename) {
		this.faceClassify = input;
		this.filename = filename;
		faceClassify.train();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		faceClassify.saveNetwork(filename);	
		System.out.println("Xong");
	}
	
	/**
	 * Lấy đối tượng face classify để sử dụng
	 * @return
	 */
	public FaceClassify getFaceClassify() {
		return faceClassify;
	}
}
