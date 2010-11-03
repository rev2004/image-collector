package team.nm.nnet.core;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

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
import team.nm.nnet.util.ImageProcess;
import team.nm.nnet.util.LogicUtils;

/**
 * Nhan dang guong mat
 * @author MinhNhat
 *
 */
public class FaceRecognition {

	/**
	 * Ten cua network khi luu xuong file
	 */
	private String networkName = "facerecognition";
	
	/**
	 * Luu danh sach anh de train
	 */
	private List<Image> listImage;
	
	/**
	 * Luu thu tu thu muc chua anh
	 */
	private List<Integer> listIndex;
	
	/**
	 * Luu du lieu cho viec train
	 */
	private ImageNeuralDataSet dataSet = new ImageNeuralDataSet(new SimpleIntensityDownsample(), false, 1, -1);

	/**
	 * Mang neural
	 */
	private BasicNetwork network;
	
	/**
	 * Luu so luong face can hoc
	 * (so folder trong bo train)
	 */
	private int outputNetwork;
	
	/**
	 * Load cac anh can train bo vao listImage va thu tu thu muc bo vao listIndex
	 * @param trainFolder Duong dan den cau truc thu muc train
	 */
	public void loadImage(String trainFolder) {
		listImage = new ArrayList<Image>();
		listIndex = new ArrayList<Integer>();
		List<String> listSubFolder = IOUtils.listSubFolder(trainFolder);
		int countTrainFolder = 0;
		for (String folderI : listSubFolder) {
			String subFolderI = trainFolder + "\\" + folderI;
			if (LogicUtils.isNumber(folderI)) {
				countTrainFolder ++;
				int indexI = Integer.parseInt(folderI);
				List<String> listFile = IOUtils.listFileName(subFolderI);
				for (String fileI : listFile) {
					String filename = subFolderI + "\\" + fileI;
					Image image = ImageProcess.load(filename);
					listImage.add(image);
					listIndex.add(indexI);
				}
			}
		}
		outputNetwork = countTrainFolder;
		createDataSet();
	}
	
	/**
	 * Tao dataset khi load cac anh len
	 */
	private void createDataSet() {
		int count = listImage.size();
		for (int i = 0; i < count; i ++) {
			ImageNeuralData data = new ImageNeuralData(listImage.get(i));
			double[] array = new double[outputNetwork];
			NeuralData ideal = new BasicNeuralData(array);
			ideal.setData(listIndex.get(i), 1);
			dataSet.add(data, ideal);
		}
	}
	
	/**
	 * Cho network hoc
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
	
	/**
	 * Nhan dang anh
	 * @param image Anh can nhan dang
	 * @return Ket qua nhan dang
	 */
	public int recognition(Image image) {
		ImageNeuralData input = new ImageNeuralData(image);
		input.downsample(new SimpleIntensityDownsample(), false, Const.FACE_HEIGHT, Const.FACE_WIDTH, 1, -1);
		int index = network.winner(input);
		return index;
	}
	
}
