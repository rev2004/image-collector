package team.nm.nnet.app.imageCollector.test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import team.nm.nnet.core.Const;
import team.nm.nnet.core.FaceClassify;
import team.nm.nnet.util.IOUtils;
import team.nm.nnet.util.ImageUtils;

public class TestNeuralNetwork {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
		//--------------------------------------------
		FaceClassify fc = new FaceClassify(Const.NUMBER_OF_INPUT_NEURAL, 
				Const.NUMBER_OF_HIDDEN_NEURAL, Const.NUMBER_OF_OUTPUT_NEURAL);
		List<double[]> listFaceTrain = new ArrayList<double[]>();
		for (String str : listFace) {
			BufferedImage bi = ImageUtils.load(pathFace + "\\" + str);
			bi = ImageUtils.grayScale(bi);
			bi = ImageUtils.resize(bi, Const.FACE_WIDTH, Const.FACE_HEIGHT);
			double[] inputArray = ImageUtils.toArray(bi);
			listFaceTrain.add(inputArray);
		}
		fc.addFacesToTrain(listFaceTrain);
		
		List<double[]> listNonFaceTrain = new ArrayList<double[]>();
		for (String str : listNonFace) {
			BufferedImage bi = ImageUtils.load(pathFace + "\\" + str);
			bi = ImageUtils.grayScale(bi);
			bi = ImageUtils.resize(bi, Const.FACE_WIDTH, Const.FACE_HEIGHT);
			double[] inputArray = ImageUtils.toArray(bi);
			listNonFaceTrain.add(inputArray);
		}
		fc.addNonFaceToTrain(listNonFaceTrain);
		
		fc.train();
		fc.saveNetwork(path + "\\ref\\outputNetwork\\output.nnet");
		

	}

}
