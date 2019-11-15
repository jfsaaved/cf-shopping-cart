package io.jfsaaved.cf.shopping.cart.item;


import java.math.BigDecimal;

public class Item {

    private long id;
    private String name;
    private BigDecimal price;

    public Item() {

    }

    public Item(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Item(long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o){

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item otherItem = (Item) o;

        if(this.id != otherItem.id) return false;
        if(!this.name.equals(otherItem.getName())) return false;
        return this.price.equals(getPrice());

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());

        return result;
    }

    @Override
    public String toString(){
        return "ID: " + id + " NAME: " + name + " PRICE: " + price;
    }

}
