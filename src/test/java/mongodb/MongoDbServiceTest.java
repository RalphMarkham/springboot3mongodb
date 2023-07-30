package mongodb;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@MockBeans({ @MockBean(MongoTemplate.class), @MockBean(GridFsTemplate.class) })
class MongoDbServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

	@Autowired
	private MongoDbService svc;

    @Test
    void findAll() {
        when(mongoTemplate.findAll(any(), anyString())).thenReturn(List.of(new Customer("Alice", "Smith")));
		assertEquals("Alice", svc.findAll().get(0).firstName);
    }

    @Test
    void findByFirstName() {
        when(mongoTemplate.find(any(Query.class), any(), anyString())).thenReturn(List.of(new Customer("Alice", "Smith")));
		assertEquals("Alice", svc.findByFirstName("Alice").get(0).firstName);
    }

    @Test
    void findByLastName() {
        when(mongoTemplate.find(any(Query.class), any(), anyString())).thenReturn(List.of(new Customer("Alice", "Smith")));
		assertEquals("Alice", svc.findByLastName("Smith").get(0).firstName);
    }
}