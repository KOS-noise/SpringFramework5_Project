package mylab.order.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:mylab-order-di.xml")
public class OrderSpringTest {

    @Autowired
    private ShoppingCart cart;

    @Autowired
    private OrderService service;

    @Test
    public void testShoppingCart() {
        assertNotNull(cart, "ShoppingCart 객체는 Null이 아니어야 합니다.");
        assertEquals(2, cart.getProducts().size(), "장바구니에는 2개의 상품이 있어야 합니다.");
        assertEquals("노트북", cart.getProducts().get(0).getName());
        assertEquals("스마트폰", cart.getProducts().get(1).getName());
    }

    @Test
    public void testOrderService() {
        assertNotNull(service, "OrderService 객체는 Null이 아니어야 합니다.");
        assertEquals(950000.0, service.calculateOrderTotal(), "총 주문 금액이 일치해야 합니다.");
    }
}