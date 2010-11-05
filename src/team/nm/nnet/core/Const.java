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
     * Number of hidden neural 1
     */
    public static final int FACE_CLASSIFY_NUMBER_OF_HIDDEN_NEURAL_1 = 300;

    /**
     * Number of hidden neural 2
     */
    public static final int FACE_CLASSIFY_NUMBER_OF_HIDDEN_NEURAL_2 = 150;
    
    /**
     * So luong neural output do de la 2 no se khong chay
     */
    public static final int FACE_CLASSIFY_NUMBER_OF_OUTPUT_NEURAL = 20;
    
    /**
     * Buoc nhay frame anh
     */
    public static final int JUMP_LENGHT = 5;
    
    /**
     * Luu duong dan file mask
     */
    public static final String MASK_IMAGE = System.getProperty("user.dir") + "/ref/imageStore/mask.jpg";
}
