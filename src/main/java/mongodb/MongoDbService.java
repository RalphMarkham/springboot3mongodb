package mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void populatDb() {
        if (!mongoTemplate.collectionExists("rst-987")) {
			mongoTemplate.createCollection("rst-987", CollectionOptions.empty());
		}

		mongoTemplate.remove(new Query(), "rst-987");

		mongoTemplate.save(new Customer("Alice", "Smith"), "rst-987");
		mongoTemplate.save(new Customer("Bob", "Smith"), "rst-987");
    }

    public List<Customer> findAll() {
        return mongoTemplate.findAll(Customer.class, "rst-987");
    }

    public List<Customer> findByFirstName(String firstName) {
        return findByField("firstName", firstName);
    }

    public List<Customer> findByLastName(String lastName) {
		return findByField("lastName", lastName);
    }

    public List<Customer> findByField(String fieldName, String firstName) {
        Query query = new Query(Criteria.where(fieldName).is(firstName));
		return mongoTemplate.find(query,Customer.class,"rst-987");
    }
}
