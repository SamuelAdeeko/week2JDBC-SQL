package log4j_adeekobank.main;

import org.apache.log4j.Logger;

import log4j_adeekobank.service.HelloLogService;

public class HelloLog4j {
	
	private static Logger log = Logger.getLogger(HelloLog4j.class);
	
	
	public static void main(String[] args) {
	
		log.trace("Hello from trace");
		log.debug("Hello from DEBUG");
		log.info("Hello from INFO");
		log.warn("Hello from WARN");
		log.error("Hello from ERROR");
		log.fatal("Hello from FATAL");
		
		HelloLogService service =new HelloLogService();
		service.hellolog();
		service.hellolog("Invalid loggin information");
	}

}
