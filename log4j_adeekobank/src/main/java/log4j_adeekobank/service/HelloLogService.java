package log4j_adeekobank.service;

import org.apache.log4j.Logger;

public class HelloLogService {

	
	
	
	private static Logger log = Logger.getLogger(HelloLogService.class);
	
	public void hellolog() {
		log.info("Hello from log service");
		log.info("bye bye from log service helloLog()");
	}
	public void hellolog(String message) {
		log.info(message);

	}
	
}
