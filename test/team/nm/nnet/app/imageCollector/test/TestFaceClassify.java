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
import org.encog.persist.EncogPersistedCollection;
import org.encog.util.downsample.SimpleIntensityDownsample;
import org.encog.util.simple.EncogUtility;

import team.nm.nnet.core.FaceClassify;
import team.nm.nnet.util.IOUtils;

public class TestFaceClassify {

	public static void main(String[] agrs) throws Exception{
		ImageNeuralDataSet dataSet = new ImageNeuralDataSet(new SimpleIntensityDownsample(), false, 1, -1);
		String folder = System.getProperty("user.dir");
		folder += "\\ref\\imageStore";
		String facesTrain = folder + "\\faces";
		String noneFacesTrain = folder + "\\nonFaces";
		FaceClassify fc = new FaceClassify();
		
		fc.addFaceToTrain(facesTrain);
		fc.addNonFaceToTrain(noneFacesTrain);
		fc.train();
		
		System.out.println("Face test");
		List<String> listTest = IOUtils.listFileName(facesTrain);
		for (int i = 0; i < listTest.size(); i ++) {
			System.out.println(fc.isFace(facesTrain + "\\" + listTest.get(i)));
		}
		
		System.out.println("None ace test");
		listTest = IOUtils.listFileName(noneFacesTrain);
		for (int i = 0; i < listTest.size(); i ++) {
			System.out.println(fc.isFace(noneFacesTrain + "\\" + listTest.get(i)));
		}
		
		System.out.println("Actual test face");
		String testFaces = folder + "\\testFaces";
		listTest = IOUtils.listFileName(testFaces);
		for (int i = 0; i < listTest.size(); i ++) {
			System.out.println(fc.isFace(testFaces + "\\" + listTest.get(i)));
		}
		
		System.out.println("Actual test none face");
		String testNonFaces = folder + "\\testNonFaces";
		listTest = IOUtils.listFileName(testNonFaces);
		for (int i = 0; i < listTest.size(); i ++) {
			System.out.println(fc.isFace(testNonFaces + "\\" + listTest.get(i)));
		}

	}
}
