package mongodb;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBean(MongoTemplate.class)
@MockBean(GridFsTemplate.class)
class MongoDbServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

	@Autowired
	private MongoDbService svc;

    @Test
    void findAll() {
        when(mongoTemplate.findAll(any(), anyString())).thenReturn(List.of(new Customer("Alice", "Smith", Map.of(), Map.of())));
		then(svc.findAll().get(0).firstName).isEqualTo("Alice");
    }

    @Test
    void findByFirstName() {
        when(mongoTemplate.find(any(Query.class), any(), anyString())).thenReturn(List.of(new Customer("Alice", "Smith", Map.of(), Map.of())));
        then(svc.findByFirstName("Alice").get(0).firstName).isEqualTo("Alice");
    }

    @Test
    void findByLastName() {
        when(mongoTemplate.find(any(Query.class), any(), anyString())).thenReturn(List.of(new Customer("Alice", "Smith", Map.of(), Map.of())));
		then(svc.findByLastName("Smith").get(0).firstName).isEqualTo("Alice");
    }

    @Test
    void findByKeyValue() {
        when(mongoTemplate.find(any(Query.class), any(), anyString()))
                .thenReturn(List.of(new Customer("Bob", "Smith", Map.of("keyName", "keyValue"), Map.of("keyName",Map.of("keyName", "keyValue")))));
        then(svc.findByKeyValue("references",Map.of("keyName", "keyValue")).get(0).firstName).isEqualTo("Bob");
    }
}