/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package team.nm.nnet.core;

import java.util.List;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.SupervisedTrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import java.util.Observable;

/**
 * Classify 
 * @author MinhNhat
 */
public class FaceClassify extends Observable implements Runnable{
    private NeuralNetwork neuralNetwork;
    
    private Thread thread;
    
    /**
     * Ket qua mong muon xuat ra cho face
     */
    private double[] desireFaceOutput = new double[] {1};
    
    /**
     * Ket qua mong muon xuat ra cho none face
     */
    private double[] desireNoneFaceOutput = new double[] {0};
    
    /**
     * Luu du lieu cho viec train
     */
    private TrainingSet trainSet = new TrainingSet();
    

    /**
     * Create Instance of faceclassify
     */
    public FaceClassify() {
        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.TANH,
                                                Const.NUMBER_OF_INPUT_NEURAL,
                                                Const.NUMBER_OF_HIDDEN_NEURAL,
                                                Const.NUMBER_OF_OUTPUT_NEURAL);
    }

    @Override
    public void run() {
    	neuralNetwork.learnInSameThread(trainSet);
    	System.out.println("Xong");
    	setChanged();
    	notifyObservers();
    }
    
    /**
     * Train cho neural network bỏ vào trong thread
     */
    public void train() {
    	thread = new Thread(this);
    	thread.start();
    }
    
    
    /**
     * Nap vao cac face de train
     * @param listFace Danh sach face can train
     */
    public void addFacesToTrain(List<double[]> listFace) {
    	int listCount = listFace.size();
    	for (int i = 0; i < listCount; i ++) {
    		SupervisedTrainingElement trainingElement = 
    			new SupervisedTrainingElement(listFace.get(i), desireFaceOutput);
    		trainSet.addElement(trainingElement);
    	}
    	
    }
    
    /**
     * Nap vao none face de train
     * @param listNoneFace Danh sach none face can nap
     */
    public void addNonFaceToTrain(List<double[]> listNoneFace) {
    	int listCount = listNoneFace.size();
    	for (int i = 0; i < listCount; i ++) {
    		SupervisedTrainingElement trainElement = 
    			new SupervisedTrainingElement(listNoneFace.get(i), desireNoneFaceOutput);
    		trainSet.addElement(trainElement);
    	}
    }
    

    /**
     * Save network to file
     * @param filename Filename to save network with extention .nnet
     */
    public void saveNetwork(String filename) {
        neuralNetwork.save(filename);
    }

    /**
     * Load network from file
     * @param filename Filename to load network
     */
    public void loadNetwork(String filename) {
        neuralNetwork = NeuralNetwork.load(filename);
    }

    /**
     * Classify the face
     * @param input Face to classify
     * @return Result of the classify
     */
    public boolean faceClassify(double[] input) {
        neuralNetwork.setInput(input);
        neuralNetwork.calculate();
        double[] result = neuralNetwork.getOutputAsArray();
        if (result[0] <= 0.5F) {
            return false;
        }
        return true;
    }
    
    /**
     * Reset lai trang thai train set
     */
    public void resetTrainSet() {
    	trainSet = new TrainingSet();
    }
}
