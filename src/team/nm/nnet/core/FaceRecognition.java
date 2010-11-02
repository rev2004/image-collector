package team.nm.nnet.core;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import team.nm.nnet.util.IOUtils;
import team.nm.nnet.util.ImageProcess;

/**
 * Nhan dang guong mat
 * @author MinhNhat
 *
 */
public class FaceRecognition {

	/**
	 * Luu danh sach anh de train
	 */
	private List<Image> listImage;
	
	/**
	 * Luu thu tu thu muc chua anh
	 */
	private List<Integer> listIndex;
	
	/**
	 * Load cac anh can train bo vao listImage va thu tu thu muc bo vao listIndex
	 * @param trainFolder Duong dan den cau truc thu muc train
	 */
	public void loadImage(String trainFolder) {
		listImage = new ArrayList<Image>();
		listIndex = new ArrayList<Integer>();
		List<String> listSubFolder = IOUtils.listSubFolder(trainFolder);
		for (String folderI : listSubFolder) {
			String subFolderI = trainFolder + "\\" + folderI;
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
}
