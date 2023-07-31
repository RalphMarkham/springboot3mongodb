package mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MongoDbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void populatDb() {
        if (!mongoTemplate.collectionExists("rst-987")) {
			mongoTemplate.createCollection("rst-987", CollectionOptions.empty());
		}

		mongoTemplate.remove(new Query(), "rst-987");
		mongoTemplate.save(new Customer("Alice", "Smith", Map.of(), Map.of()), "rst-987");
		mongoTemplate.save(new Customer("Bob", "Smith", Map.of("keyName", "keyValue"), Map.of("keyName",Map.of("keyName", "keyValue"))), "rst-987");
    }

    public List<Customer> findAll() {
        return mongoTemplate.findAll(Customer.class, "rst-987");
    }

    public List<Customer> findByFirstName(String firstName) {
        return findByKeyValue("firstName", firstName);
    }

    public List<Customer> findByLastName(String lastName) {
		return findByKeyValue("lastName", lastName);
    }

    public List<Customer> findByKeyValue(String key, Object value) {
        Query query = new Query(Criteria.where(key).is(value));
		return mongoTemplate.find(query, Customer.class,"rst-987");
    }
}
