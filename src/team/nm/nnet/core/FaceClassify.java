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

/**
 * Classify 
 * @author MinhNhat
 */
public class FaceClassify {
    private NeuralNetwork neuralNetwork;

    /**
     * Create Instance of faceclassify
     * @param inputNeural Number of neural in input player
     * @param hiddenNeural Number of neural in hidden layer
     * @param outputNeural Number of neural in output neural
     */
    public FaceClassify(int inputNeural, int hiddenNeural, int outputNeural) {
        neuralNetwork = new MultiLayerPerceptron(TransferFunctionType.TANH,
                                                Const.NUMBER_OF_HIDDEN_NEURAL,
                                                Const.NUMBER_OF_HIDDEN_NEURAL,
                                                Const.NUMBER_OF_OUTPUT_NEURAL);
    }

    /**
     * Train the network for classify face and none face
     * @param listOfImage List of face image to train
     * @param listOfDesireOutput list of desire image to train
     */
    public void train(List<double[]> listOfImage, List<double[]> listOfDesireOutput) {
        TrainingSet trainningSet = new TrainingSet();
        int listCout = listOfImage.size();
        for (int i = 0; i < listCout; i ++) {
            SupervisedTrainingElement trainningElement =
                    new SupervisedTrainingElement(listOfImage.get(i), listOfDesireOutput.get(i));
            trainningSet.addElement(trainningElement);
        }
        neuralNetwork.learnInSameThread(trainningSet);
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
}
