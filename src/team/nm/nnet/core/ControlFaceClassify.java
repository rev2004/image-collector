package team.nm.nnet.core;

import java.util.Observable;
import java.util.Observer;

import org.encog.persist.EncogPersistedCollection;

public class ControlFaceClassify implements Observer{
	
	/**
	 * Lưu đối tương faceclassify của lớp
	 */
	private FaceClassify faceClassify;
	
	/**
	 * Phương thức khởi tạo cho đối tượng
	 * @param faceClassify Lớp faceclassify cần chuyền vào
	 */
	public ControlFaceClassify(FaceClassify faceClassify) {
		this.setFaceClassify(faceClassify);
	}
	
	/**
	 * Phương thức này thực thi khi quan sát thấy sự thay đổi
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		
		save();
		
	}

	public void setFaceClassify(FaceClassify faceClassify) {
		this.faceClassify = faceClassify;
	}

	public FaceClassify getFaceClassify() {
		return faceClassify;
	}
	
	/**
	 * Lưu network xuống file xml
	 */
	public void save() {
		String filename = System.getProperty("user.dir");
		filename += "\\ref\\outputNetwork\\network.eg";
		EncogPersistedCollection en = new EncogPersistedCollection(filename);
		en.create();
		en.add("network", faceClassify.getNetwork());
		System.out.println("Save file to: " + filename);
	}
}
