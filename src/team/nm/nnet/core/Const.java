package team.nm.nnet.core;

/**
 * Store all constraints
 * @author MinhNhat
 *
 */
public class Const {
    /**
     * Height of face image
     */
    public static final int FACE_HEIGHT = 30;

    /**
     * Width of face image
     */
    public static final int FACE_WIDTH = 20;

    /**
     * Number of input layer
     */
    public static final int NUMBER_OF_INPUT_NEURAL = FACE_WIDTH * FACE_HEIGHT;

    /**
     * Number of hidden neural
     */
    public static final int NUMBER_OF_HIDDEN_NEURAL = NUMBER_OF_INPUT_NEURAL + NUMBER_OF_INPUT_NEURAL / 2;

    /**
     * Number of output neural
     */
    public static final int NUMBER_OF_OUTPUT_NEURAL = 1;
}
