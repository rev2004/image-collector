package team.nm.nnet.core;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Observable;

import org.encog.neural.activation.ActivationSigmoid;
import org.encog.neural.data.NeuralDataSet;
import org.encog.neural.data.basic.BasicNeuralData;
import org.encog.neural.data.basic.BasicNeuralDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.Train;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.strategy.ResetStrategy;
import org.encog.util.simple.EncogUtility;

import team.nm.nnet.util.IOUtils;
import team.nm.nnet.util.ImageProcess;
import team.nm.nnet.util.Matrix;

public class FaceClassify extends Observable implements Runnable{

	/**
	 * Lưu network cua hệ thống
	 */
	private BasicNetwork network;
	
	/**
	 * Lưu dữ liệu train cho network
	 */
	private NeuralDataSet dataSet;
	
	/**
	 * Luu đối tượng train cho hệ thống
	 */
	private ResilientPropagation train;
	
	public FaceClassify() {
		network = EncogUtility.simpleFeedForward(Const.NUMBER_OF_INPUT_NEURAL, Const.NUMBER_OF_HIDDEN_NEURAL, 0, Const.NUMBER_OF_OUTPUT_NEURAL, true);

		dataSet = new BasicNeuralDataSet();
	}
	
	/**
	 * Nạp vào thư mục chứa các ảnh la face
	 * @param folder Đường dẫn đến thư mục
	 */
	public void addTrainFaceData(String folder) {
		double[] ideal = {1};
		List<String> listFilenames = IOUtils.listFileName(folder);
		int listCount = listFilenames.size();
		for (int i = 0; i < listCount; i ++) {
			BufferedImage image = ImageProcess.load(folder + "\\" + listFilenames.get(i));
			image = ImageProcess.resize(image, Const.FACE_WIDTH, Const.FACE_HEIGHT);
			Matrix matrix = ImageProcess.imageToMatrix(image);
			matrix = ImageProcess.nomalizeMatrix(matrix);
			double[] input = ImageProcess.matrixToArray(matrix);
			dataSet.add(new BasicNeuralData(input), new BasicNeuralData(ideal));	
		}
	}
	
	/**
	 * Nạp vào thư mục chứa các ảnh hong là face
	 * @param folder Đường dẫn đến thư mục
	 */
	public void addTrainNonFaceData(String folder) {
		double[] ideal = {0};
		List<String> listFilenames = IOUtils.listFileName(folder);
		int listCount = listFilenames.size();
		for (int i = 0; i < listCount; i ++) {
			BufferedImage image = ImageProcess.load(folder + "\\" + listFilenames.get(i));
			image = ImageProcess.resize(image, Const.FACE_WIDTH, Const.FACE_HEIGHT);
			Matrix matrix = ImageProcess.imageToMatrix(image);
			matrix = ImageProcess.nomalizeMatrix(matrix);
			double[] input = ImageProcess.matrixToArray(matrix);
			dataSet.add(new BasicNeuralData(input), new BasicNeuralData(ideal));	
		}
	}
	
	/**
	 * Phai implement phuong thuc nay moi co the chay
	 */
	@Override
	public void run() {
		train = new ResilientPropagation(getNetwork(), dataSet);
		train.addStrategy(new ResetStrategy(0.25, 50));
		EncogUtility.trainConsole(train ,network, dataSet, 1);
		
		
		//Thông báo cho lơp quan sát biết đã có sự thay đổi
		setChanged();
		notifyObservers();
	}

	public void setNetwork(BasicNetwork network) {
		this.network = network;
	}

	public BasicNetwork getNetwork() {
		return network;
	}
}
