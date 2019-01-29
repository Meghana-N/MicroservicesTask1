package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**This is main class that runs the application,standalone
 * Also implments 2 interface which us used to add the default values
 * to the variables to the entity variables
 */

@EnableEurekaClient
@SpringBootApplication
//@PropertySource("classpath:property.properties")//Sets the path of the resources file to be used in this class
public class MuzixApplication {

//	@Value("6")			//Adding directly by using @Value
//	private int trackid;
//
//	@Value("${trackname}")//Adding value by using @Value and using property file
//	private String trackname;
//
//	@Value("Comments")		//Adding comments directly using @Value
//	private String comments;
//
//	@Autowired
//	TrackRepository trackRepository;
//
//	@Autowired
//	Environment env;		//Initialising and Autowiring Enviornment varaibles

	//This method runs the applcation
	public static void main(String[] args) {
		SpringApplication.run(MuzixApplication.class, args);
	}
//
//	//This is for setting values to Track each time the Application is refreshed
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//		Track track= new Track(trackid,trackname,comments);
//		trackRepository.save(track);
//	}
//
//	//This is setting values ato track at command line by using the getproperty of enviorment
//	@Override
//	public void run(String... args) throws Exception {
//		Track track= new Track(Integer.parseInt(env.getProperty("trackid")),env.getProperty("trackname"),env.getProperty("comments"));
//		trackRepository.save(track);
//	}
}

