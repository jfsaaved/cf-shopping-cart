package io.jfsaaved.cf.shopping.cart.item;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcItemRepository implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcItemRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item create(Item item) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
           PreparedStatement statement = connection.prepareStatement(
                   "INSERT INTO items (id, name, price) VALUES(?, ?, ?)",
                   RETURN_GENERATED_KEYS
           );

           statement.setLong(1, item.getId());
           statement.setString(2, item.getName());
           statement.setBigDecimal(3, item.getPrice());

           return statement;
        }, generatedKeyHolder);

        return find(generatedKeyHolder.getKey().longValue());
    }

    @Override
    public Item find(Long id) {
        return jdbcTemplate.query("SELECT * FROM items WHERE id = ?", new Object[] {id}, extractor);
    }

    @Override
    public List<Item> list() {
        return jdbcTemplate.query("SELECT * FROM items", mapper);
    }

    @Override
    public Item update(Long id, Item item) {
        jdbcTemplate.update("UPDATE items SET name = ?, price = ? WHERE id = ?",
                item.getName(), item.getPrice(), id);

        return find(id);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM items WHERE id = ?", id);
    }

    private final RowMapper<Item> mapper = (rs, rowNum) -> new Item(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getBigDecimal("price")
    );

    private final ResultSetExtractor<Item> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;

}
