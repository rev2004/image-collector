package team.nm.nnet.app.imageCollector.test;

import team.nm.nnet.core.FaceRecognition;

public class TestFunction {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FaceRecognition fr = new FaceRecognition();
		String path = System.getProperty("user.dir");
		path += "\\ref\\imageStore\\trainFolder";
		fr.loadImage(path);
	}

}
