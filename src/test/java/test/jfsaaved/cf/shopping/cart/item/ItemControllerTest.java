package test.jfsaaved.cf.shopping.cart.item;

import io.jfsaaved.cf.shopping.cart.item.Item;
import io.jfsaaved.cf.shopping.cart.item.ItemController;
import io.jfsaaved.cf.shopping.cart.item.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    private ItemRepository itemRepository;
    private ItemController itemController;

    @Before
    public void setUp() {
        itemRepository = mock(ItemRepository.class);
        itemController = new ItemController(itemRepository);
    }

    @Test
    public void testCreate() {
        Item itemToCreate = new Item("book 1", BigDecimal.valueOf(13));

        Long itemId = 1L;

        Item expectedResult = new Item(itemId, "book 1", BigDecimal.valueOf(13));
        doReturn(expectedResult).when(itemRepository).create(any(Item.class));

        ResponseEntity response = itemController.create(itemToCreate);
        verify(itemRepository).create(itemToCreate);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(expectedResult);
    }

    @Test
    public void testRead() {
        Long itemId = 1L;
        Item expected = new Item(itemId,"book 1", BigDecimal.valueOf(13));
        doReturn(expected).when(itemRepository).find(itemId);

        ResponseEntity response = itemController.read(itemId);
        verify(itemRepository).find(itemId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    public void testRead_NotFound() {
        Long nonExistentId = 2L;
        doReturn(null).when(itemRepository).find(nonExistentId);

        ResponseEntity response = itemController.read(nonExistentId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testList(){
        Collection<Item> expected = new ArrayList<>();
        expected.add(new Item(1L, "book 1", BigDecimal.valueOf(13)));
        expected.add(new Item(2L, "book 2", BigDecimal.valueOf(13)));
        doReturn(expected).when(itemRepository).list();

        ResponseEntity response = itemController.list();
        verify(itemRepository).list();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    public void testUpdate() {
        Long itemId = 1L;
        Item expected = new Item(itemId, "book 1", BigDecimal.valueOf(13));

        doReturn(expected).when(itemRepository).update(eq(itemId), any(Item.class));

        ResponseEntity response = itemController.update(itemId, expected);
        verify(itemRepository).update(itemId, expected);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }

    @Test
    public void testUpdate_NotFound() {

    }

    @Test
    public void testDelete() {
        long itemId = 1L;
        ResponseEntity response = itemController.delete(itemId);
        verify(itemRepository).delete(itemId);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}
