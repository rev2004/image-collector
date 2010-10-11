package team.nm.nnet.app.imageCollector.test;

import team.nm.nnet.core.ControlFaceClassify;
import team.nm.nnet.core.FaceClassify;



public class TestNeuralNetwork{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*System.out.println("Begin time: " + System.currentTimeMillis());
		
		String path = System.getProperty("user.dir");
		String root = path;
		path += "\\ref\\imageStore";
		String pathFace = path + "\\faces";
		String pathNonFace = path + "\\nonFaces";
		System.out.println(path);
		List<String> listFace = IOUtils.listFileName(pathFace);
		List<String> listNonFace = IOUtils.listFileName(pathNonFace);
		System.out.println("Danh sach cac face la:");
		for (String str : listFace) {
			System.out.println(str);
		}
		System.out.println("Danh sach cac non face la:");
		for (String str : listNonFace) {
			System.out.println(str);
		}
		*/
		
		FaceClassify fc = new FaceClassify();
		fc.addTrainFaceData(System.getProperty("user.dir") + "\\ref\\imageStore\\faces");
		fc.addTrainNonFaceData(System.getProperty("user.dir") + "\\ref\\imageStore\\nonFaces");
		ControlFaceClassify cfc = new ControlFaceClassify(fc);
		fc.addObserver(cfc);
		Thread t = new Thread(fc);
		t.start();
	}

}
