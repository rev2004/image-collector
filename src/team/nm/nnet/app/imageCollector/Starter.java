package team.nm.nnet.app.imageCollector;

import sol.hawking.snatcher.core.Application;
import team.nm.nnet.app.imageCollector.layout.Capture;

public class Starter {

	public static void main(String[] args) {
		System.out.println("//////////  Starting Image Collector Application  ///////////");
		Application app = new Application("team/nm/nnet/app/imageCollector/config/application.cfg.xml");
        app.setPropertiesLocations(new String[]{"/team/nm/nnet/app/imageCollector/config/application.properties"});
		app.start();
		
		Capture capture = (Capture) app.getBean("capture");
		capture.setTitle("NM team");
		capture.show();
		
		System.out.println("//////////  Image Collector Application Started ///////////");
	}

}
