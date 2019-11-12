package test.jfsaaved.cf.shopping.cart.item;

import io.jfsaaved.cf.shopping.cart.Application;
import io.jfsaaved.cf.shopping.cart.item.InMemoryItemRepository;
import io.jfsaaved.cf.shopping.cart.item.Item;
import io.jfsaaved.cf.shopping.cart.item.ItemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class ItemApiTest {



}

