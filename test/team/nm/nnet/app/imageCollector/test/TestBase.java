package team.nm.nnet.app.imageCollector.test;

import sol.hawking.snatcher.core.Application;


public class TestBase {
	
	static Application app;
	
	static{
		System.out.println("//////////  Starting Image Collector Application  ///////////");
		app = new Application("team/nm/nnet/app/imageCollector/config/application.cfg.xml");
        app.setPropertiesLocations(new String[]{"/team/nm/nnet/app/imageCollector/config/application.properties"});
		app.start();
		System.out.println("//////////  Image Collector Application Started ///////////");
	}
}
