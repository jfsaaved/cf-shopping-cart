package test.jfsaaved.cf.shopping.cart.item;

import io.jfsaaved.cf.shopping.cart.item.ItemController;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemControllerTest {

    @Test
    public void itSaysHello() throws Exception {

        ItemController controller = new ItemController("A welcome message");

        assertThat(controller.test()).isEqualTo("A welcome message");
    }

}
