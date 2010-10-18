package team.nm.nnet.app.imageCollector.test;

import java.awt.Image;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.encog.neural.data.NeuralData;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.data.image.ImageNeuralData;
import org.encog.neural.data.image.ImageNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.strategy.ResetStrategy;
import org.encog.util.downsample.SimpleIntensityDownsample;
import org.encog.util.simple.EncogUtility;

import team.nm.nnet.util.IOUtils;

public class TestFaceClassify {

	public static void main(String[] agrs) throws Exception{
		ImageNeuralDataSet dataSet = new ImageNeuralDataSet(new SimpleIntensityDownsample(), false, 1, -1);
		String folder = System.getProperty("user.dir");
		folder += "\\ref\\imageStore";
		String facesTrain = folder + "\\faces";
		String noneFacesTrain = folder + "\\nonFaces";
		List<String> listFaces = IOUtils.listFileName(facesTrain);
		for (int i = 0; i < listFaces.size(); i ++) {
			Image image = ImageIO.read(new File(facesTrain + "\\" + listFaces.get(i)));
			ImageNeuralData data = new ImageNeuralData(image);
			double[] array = new double[20];
			NeuralData ideal = new BasicNeuralData(array);
			ideal.setData(0, 1);
			dataSet.add(data, ideal);
		}
		
		List<String> listNonFaces = IOUtils.listFileName(noneFacesTrain);
		for (int i = 0; i < listNonFaces.size(); i ++) {
			Image image = ImageIO.read(new File(noneFacesTrain + "\\" + listNonFaces.get(i)));
			ImageNeuralData data = new ImageNeuralData(image);
			double[] array = new double[20];
			NeuralData ideal = new BasicNeuralData(array);
			int index = i % 20;
			if (index == 0) {
				index ++;
			}
			ideal.setData(i, 1);
			dataSet.add(data, ideal);
		}
		
		dataSet.downsample(30, 20);
		BasicNetwork network = EncogUtility.simpleFeedForward(dataSet.getInputSize(), 300, 150, dataSet.getIdealSize(), true);
		ResilientPropagation train = new ResilientPropagation(network, dataSet);
		train.addStrategy(new ResetStrategy(0.25, 50));
		EncogUtility.trainConsole(train, network, dataSet,4);
		System.out.println("Train complete");
		System.out.println("Face test");
		List<String> listTest = IOUtils.listFileName(facesTrain);
		for (int i = 0; i < listTest.size(); i ++) {
			Image image = ImageIO.read(new File(facesTrain + "\\" + listTest.get(i)));
			ImageNeuralData input = new ImageNeuralData(image);
			input.downsample(new SimpleIntensityDownsample(), false, 30, 20, 1, -1);
			System.out.println("Index is: " + network.winner(input));
		}
		System.out.println("None ace test");
		listTest = IOUtils.listFileName(noneFacesTrain);
		for (int i = 0; i < listTest.size(); i ++) {
			Image image = ImageIO.read(new File(noneFacesTrain + "\\" + listTest.get(i)));
			ImageNeuralData input = new ImageNeuralData(image);
			input.downsample(new SimpleIntensityDownsample(), false, 30, 20, 1, -1);
			System.out.println("Index is: " + network.winner(input));
		}
		
	}
}
