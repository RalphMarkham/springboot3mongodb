package mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Map;

@SpringBootApplication
public class MongodbApplication implements CommandLineRunner {


	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoDbService svc;

	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		svc.populatDb();
		
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : svc.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(svc.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : svc.findByLastName("Smith")) {
			System.out.println(customer);
		}

		System.out.println("Customers found with findByKeyValue('references',Map.of('keyName', 'keyValue')):");
		System.out.println("--------------------------------");
		for (Customer customer : svc.findByKeyValue("references", Map.of("keyName", "keyValue"))) {
			System.out.println(customer);
		}
	}

}
