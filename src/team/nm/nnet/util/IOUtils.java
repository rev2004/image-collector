package team.nm.nnet.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

	/**
	 * Lay cac file name trong thu muc
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
    
    public static boolean exists(String filename) {
    	File file = new File(filename);
    	return file.exists();
    }
}
