package io.jfsaaved.cf.shopping.cart.item;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController {

    private String message;

    public ItemController(@Value("${welcome.message}")  String message) {
        this.message = message;
    }

    @GetMapping("/")
    public String test(){
        if(message == null) message = "Message not set";
        return message;
    }

}
