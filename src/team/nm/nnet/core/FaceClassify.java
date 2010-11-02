package team.nm.nnet.core;

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

import team.nm.nnet.util.IOUtils;



public class FaceClassify{

	/**
	 * Ten cua network khi luu xuong file
	 */
	private String networkName = "network";
	
	
	/*
	 * Luu data cho viec train
	 */
	private ImageNeuralDataSet dataSet = new ImageNeuralDataSet(new SimpleIntensityDownsample(), false, 1, -1);
	
	/**
	 * Mang neural
	 */
	private BasicNetwork network;
	/**
	 * Add thu muc chua face de train
	 * @param path Duong dan den thu muc chua face
	 */
	public void addFaceToTrain(String path) {
		try {
		List<String> listFilename = IOUtils.listFileName(path);
			for (int i = 0; i < listFilename.size(); i ++) {
				Image image = ImageIO.read(new File(path + "\\" + listFilename.get(i)));
				ImageNeuralData data = new ImageNeuralData(image);
				double[] array = new double[Const.FACE_CLASSIFY_NUMBER_OF_OUTPUT_NEURAL];
				NeuralData ideal = new BasicNeuralData(array);
				ideal.setData(0, 1);
				dataSet.add(data, ideal);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Add thu muc chua none face de train
	 * @param path Duong dan den thu muc chua none face
	 */
	public void addNonFaceToTrain(String path) {
		try {
		List<String> listFilename = IOUtils.listFileName(path);
			for (int i = 0; i < listFilename.size(); i ++) {
				Image image = ImageIO.read(new File(path + "\\" + listFilename.get(i)));
				ImageNeuralData data = new ImageNeuralData(image);
				double[] array = new double[Const.FACE_CLASSIFY_NUMBER_OF_OUTPUT_NEURAL];
				NeuralData ideal = new BasicNeuralData(array);
				ideal.setData(1, 1);
				dataSet.add(data, ideal);
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Train cho network
	 */
	public void train() {
		dataSet.downsample(Const.FACE_HEIGHT, Const.FACE_WIDTH);
		network = EncogUtility.simpleFeedForward(dataSet.getInputSize(), Const.FACE_CLASSIFY_NUMBER_OF_HIDDEN_NEURAL_1, Const.FACE_CLASSIFY_NUMBER_OF_HIDDEN_NEURAL_2, dataSet.getIdealSize(), true);
		ResilientPropagation train = new ResilientPropagation(network, dataSet);
		train.addStrategy(new ResetStrategy(0.25, 50));
		int epoch = 0;
		for (int i = 0; i < 1000; i ++) {
			train.iteration();
			System.out.println("Epoch is : " + epoch);
			System.out.println("Error is : " + train.getError());
			epoch ++;
			if (train.getError() < 0.0005) {
				return;
			}
		}
		System.out.println("Final epoch is : " + epoch);
		System.out.println("Final error is : " + train.getError());
		
	}
	
	/**
	 * Nhan dang phai la guong mat hay khong
	 * @param filename Duong dan toi guong mat
	 * @return Ket qua nhan dang
	 */
	public boolean isFace(String filename) {
		try {
			Image image = ImageIO.read(new File(filename));
			ImageNeuralData input = new ImageNeuralData(image);
			input.downsample(new SimpleIntensityDownsample(), false, Const.FACE_HEIGHT, Const.FACE_WIDTH, 1, -1);
			int index = network.winner(input);
			if (index == 0) {
				return true;
			}
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Nhan dien anh la guong mat hay khong
	 * @param image Anh can nhan dien
	 * @return Ket qua nhan dien
	 */
	public boolean isFace(Image image) {
		try {
			ImageNeuralData input = new ImageNeuralData(image);
			input.downsample(new SimpleIntensityDownsample(), false, Const.FACE_HEIGHT, Const.FACE_WIDTH, 1, -1);
			int index = network.winner(input);
			if (index == 0) {
				return true;
			}
			return false;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Luu network xuong file
	 * @param filename Duong dan luu file 
	 */
	public void saveNetwork(String filename) {
		EncogPersistedCollection epc = new EncogPersistedCollection(filename);
		epc.create();
		epc.clear();
		epc.add(networkName, network);
	}
	
	/**
	 * Load network tu file
	 * @param filename Duong dan load file
	 */
	public void loadNetwork(String filename) {
		EncogPersistedCollection epc = new EncogPersistedCollection(filename);
		network = (BasicNetwork) epc.find(networkName);
	}

}
