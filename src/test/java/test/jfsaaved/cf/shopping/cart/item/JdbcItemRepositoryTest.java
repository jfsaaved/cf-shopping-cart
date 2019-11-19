package test.jfsaaved.cf.shopping.cart.item;



import com.mysql.cj.jdbc.MysqlDataSource;
import io.jfsaaved.cf.shopping.cart.item.Item;
import io.jfsaaved.cf.shopping.cart.item.ItemRepository;
import io.jfsaaved.cf.shopping.cart.item.JdbcItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class JdbcItemRepositoryTest {

    private ItemRepository repository;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(System.getenv("SPRING_DATASOURCE_URL"));

        repository = new JdbcItemRepository(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("DROP items");
    }

    @Test
    public void testCreateReturnsItem(){
        Item item = new Item("book 1", BigDecimal.valueOf(13));
        Item actual = repository.create(item);

        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo("book 1");
        assertThat(actual.getPrice()).isEqualTo(BigDecimal.valueOf(13));
    }

    @Test
    public void testCreateAddsToRecord() {
        Item item  = new Item("book 1", BigDecimal.valueOf(13));
        Item expected = repository.create(item);

        Map<String, Object> actual = jdbcTemplate.queryForMap("SELECT * FROM items WHERE id = ?", expected.getId());

        assertThat(actual.get("id")).isEqualTo(expected.getId());
        assertThat(actual.get("name")).isEqualTo(expected.getName());
        assertThat(actual.get("price")).isEqualTo(expected.getPrice());
    }

    @Test
    public void testFindARecord() {
        jdbcTemplate.execute("INSERT INTO items (id, name, price) VALUES(55, 'book 1', 13)");

        Map<String, Object> actual = jdbcTemplate.queryForMap("SELECT * FROM items WHERE id = ?", 55L);

        assertThat(actual.get("id")).isEqualTo(55L);
        assertThat(actual.get("name")).isEqualTo("book 1");
        assertThat(actual.get("price")).isEqualTo(BigDecimal.valueOf(13));
    }

    @Test
    public void list(){
        Item item1 = new Item(14L, "book 1", BigDecimal.valueOf(13));
        Item expected1 = repository.create(item1);

        Item item2 = new Item(15L, "book 2", BigDecimal.valueOf(16));
        Item expected2 = repository.create(item2);

        List<Item> result = repository.list();

        assertThat(result.get(0).getId()).isEqualTo(expected1.getId());
        assertThat(result.get(0).getName()).isEqualTo(expected1.getName());
        assertThat(result.get(0).getPrice()).isEqualTo(expected1.getPrice());
        assertThat(result.get(1).getId()).isEqualTo(expected2.getId());
        assertThat(result.get(1).getName()).isEqualTo(expected2.getName());
        assertThat(result.get(1).getPrice()).isEqualTo(expected2.getPrice());
    }

    @Test
    public void testUpdateReturnsItem() {
        jdbcTemplate.execute("INSERT INTO items (id, name, price) VALUES(14, 'book 1', 13)");

        Long id = 14L;
        Item item = new Item(id,"book 3", BigDecimal.valueOf(15));
        Item actual = repository.update(id, item);

        assertThat(actual.getId()).isEqualTo(id);
        assertThat(actual.getName()).isEqualTo(item.getName());
        assertThat(actual.getPrice()).isEqualTo(item.getPrice());
    }

    @Test
    public void testUpdateChangesTheRecord() {
        jdbcTemplate.execute("INSERT INTO items (id, name, price) VALUES(14, 'book 1', 13)");

        Long id = 14L;
        Item item = new Item(id,"book 3", BigDecimal.valueOf(15));
        Item expected = repository.update(id, item);

        Map<String, Object> actual = jdbcTemplate.queryForMap("SELECT * FROM items WHERE id = ?", id);

        assertThat(actual.get("id")).isEqualTo(id);
        assertThat(actual.get("name")).isEqualTo(expected.getName());
        assertThat(actual.get("price")).isEqualTo(expected.getPrice());
    }

    @Test
    public void testDelete() {
        jdbcTemplate.execute("INSERT INTO items (id, name, price) VALUES (14, 'book 1', 13)");
        repository.delete(14L);
        assertThat(repository.list().size()).isEqualTo(0);
    }

}
