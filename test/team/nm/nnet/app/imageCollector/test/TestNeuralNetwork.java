package team.nm.nnet.app.imageCollector.test;

import java.util.List;

import team.nm.nnet.util.IOUtils;

public class TestNeuralNetwork {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir");
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

	}

}
