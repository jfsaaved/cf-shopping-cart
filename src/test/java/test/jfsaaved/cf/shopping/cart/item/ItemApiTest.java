package test.jfsaaved.cf.shopping.cart.item;

import com.jayway.jsonpath.DocumentContext;
import io.jfsaaved.cf.shopping.cart.Application;
import io.jfsaaved.cf.shopping.cart.item.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collection;

import static com.jayway.jsonpath.JsonPath.parse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
public class ItemApiTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private Item item = new Item("book 1", BigDecimal.valueOf(13));

    @Test
    public void testCreate() throws Exception {
        ResponseEntity<String> createResponse = restTemplate.postForEntity("/items", item, String.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        DocumentContext createJson = parse(createResponse.getBody());
        assertThat(createJson.read("$.id", Long.class)).isGreaterThan(0);
        assertThat(createJson.read("$.name", String.class)).isEqualTo("book 1");
        assertThat(createJson.read("$.price", BigDecimal.class)).isEqualTo(BigDecimal.valueOf(13));

    }

    @Test
    public void testList() throws Exception {
        Long id = createItem();
        ResponseEntity<String> createResponse = restTemplate.getForEntity("/items", String.class);

        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext listJson = parse(createResponse.getBody());

        Collection itemEntries = listJson.read("$[*]", Collection.class);
        assertThat(itemEntries.size()).isEqualTo(1);

        assertThat(listJson.read("$[0].id", Long.class)).isEqualTo(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Long id = createItem();
        Item updatedItem = new Item("book 3", BigDecimal.valueOf(13));

        ResponseEntity<String> response = restTemplate.exchange("/item/" + id, HttpMethod.PUT,
                new HttpEntity<>(updatedItem, null), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext jsonContext = parse(response.getBody());
        assertThat(jsonContext.read("$.id", Long.class)).isEqualTo(id);
        assertThat(jsonContext.read("$.name", String.class)).isEqualTo("book 3");
        assertThat(jsonContext.read("$.price", BigDecimal.class)).isEqualTo(BigDecimal.valueOf(13));
    }

    @Test
    public void testDelete() throws Exception {
        Long id = createItem();

        ResponseEntity<String> response = restTemplate.exchange("/item/" + id, HttpMethod.DELETE,
                null, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        ResponseEntity<String> otherResponse = restTemplate.getForEntity("/item/" + id, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private Long createItem() {
        HttpEntity<Item> entity = new HttpEntity<>(item);
        ResponseEntity<Item> response = restTemplate.exchange("/items",
                HttpMethod.POST, entity, Item.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        return response.getBody().getId();
    }

}

