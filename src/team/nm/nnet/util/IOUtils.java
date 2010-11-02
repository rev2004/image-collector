package team.nm.nnet.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	/**
	 * Lấy các file name trong thư mục
	 * @param directoryPath Thu muc can lay
	 * @return Danh sach file name
	 */
    public static List<String> listFileName(String directoryPath) {        
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();
        List<String> children = null;
        if(files != null) {
        	children = new ArrayList<String>();
        	for(File file : files) {
        		if(file.isFile()) {
        			children.add(file.getName());
        		}
        	}
        }
        return children;
    }
    
    /**
     * Xac dinh file co ton tai hay khong
     * @param filename File name can xac dinh
     * @return Ket qua
     */
    public static boolean exists(String filename) {
    	File file = new File(filename);
    	return file.exists();
    }
    
    /**
     * List cac folder con trong thu muc. Cac folder con cap 1 thoi nha
     * @param parentFolder Duong dan den folder cha
     * @return Ket qua kiem duoc
     */
    public static List<String> listSubFolder(String parentFolder) {
    	File parentDir = new File(parentFolder);
    	File[] listChildren = parentDir.listFiles();
    	List<String> listSubFolder = new ArrayList<String>();
    	if (listChildren != null) {
    		for (File fileI : listChildren) {
    			if (fileI.isDirectory()) {
    				listSubFolder.add(fileI.getName());
    			}
    		}
    	}
    	return listSubFolder;
    }
}
