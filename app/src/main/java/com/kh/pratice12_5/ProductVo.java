package com.kh.pratice12_5;

public class ProductVo {
    private int number;
    private String name;
    private String product;
    private int count;

    public ProductVo() {
    }

    public ProductVo(int number, String name, String product, int count) {
        this.number = number;
        this.name = name;
        this.product = product;
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", product='" + product + '\'' +
                ", count=" + count +
                '}';
    }
}
