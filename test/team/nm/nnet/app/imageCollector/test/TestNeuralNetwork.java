package team.nm.nnet.app.imageCollector.test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.encog.neural.data.NeuralData;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.data.image.ImageNeuralData;
import org.encog.neural.data.image.ImageNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.strategy.ResetStrategy;
import org.encog.persist.EncogPersistedCollection;
import org.encog.util.downsample.SimpleIntensityDownsample;
import org.encog.util.simple.EncogUtility;

import team.nm.nnet.core.Const;
import team.nm.nnet.core.FaceClassify;
import team.nm.nnet.util.IOUtils;
import team.nm.nnet.util.ImageProcess;
import team.nm.nnet.util.Matrix;



public class TestNeuralNetwork{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
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
		
		/*
		FaceClassify fc = new FaceClassify();
		fc.addTrainFaceData(System.getProperty("user.dir") + "\\ref\\imageStore\\faces");
		fc.addTrainNonFaceData(System.getProperty("user.dir") + "\\ref\\imageStore\\nonFaces");
		ControlFaceClassify cfc = new ControlFaceClassify(fc);
		fc.addObserver(cfc);
		Thread t = new Thread(fc);
		t.start();
		*/
		
		/**Test thu trong tai lieu Hom nay lam hoai ma hong ra ket qua hic**/
		/**Gio lam y chan nhu trong tai lieu luon na**/
		ImageNeuralDataSet dataSet = new ImageNeuralDataSet(new SimpleIntensityDownsample(), false, 1, -1);
		String folder = System.getProperty("user.dir");
		folder += "\\ref\\imageStore";
		String trainFolder = folder + "\\train";
		String testFolder = folder + "\\test";
		List<String> listTrain = IOUtils.listFileName(trainFolder);
		for (int i = 0; i < listTrain.size(); i ++) {
			Image image = ImageIO.read(new File(trainFolder + "\\" + listTrain.get(i)));
			ImageNeuralData data = new ImageNeuralData(image);
			double[] array = new double[2];
			NeuralData ideal = new BasicNeuralData(array);
			ideal.setData(i, 1);
			dataSet.add(data, ideal);
		}
		dataSet.downsample(16, 16);
		int size = dataSet.getInputSize();
		BasicNetwork network = EncogUtility.simpleFeedForward(dataSet.getInputSize(), 300, 150, dataSet.getIdealSize(), true);
		ResilientPropagation train = new ResilientPropagation(network, dataSet);
		train.addStrategy(new ResetStrategy(0.25, 50));
		EncogUtility.trainConsole(train, network, dataSet,2);
		System.out.println("Train complete");
		
		List<String> listTest = IOUtils.listFileName(testFolder);
		for (int i = 0; i < listTest.size(); i ++) {
			Image image = ImageIO.read(new File(testFolder + "\\" + listTest.get(i)));
			ImageNeuralData input = new ImageNeuralData(image);
			input.downsample(new SimpleIntensityDownsample(), false, 16, 16, 1, -1);
			System.out.println("Index is: " + network.winner(input));
		}
		
	}

}
