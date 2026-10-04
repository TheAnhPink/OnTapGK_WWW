package iuh.fit.demobai3tuan345voirestapi.entity;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;

@SessionScoped
public class CartItemBean implements Serializable {
    private Product product;
    private int quantityTrongGio;

    public CartItemBean() {
    }

    public CartItemBean(Product product, int quantityTrongGio) {
        this.product = product;
        this.quantityTrongGio = quantityTrongGio;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantityTrongGio() {
        return quantityTrongGio;
    }

    public void setQuantityTrongGio(int quantityTrongGio) {
        this.quantityTrongGio = quantityTrongGio;
    }
    public double tinhTongTien1ItemTrongGio(){
        return product.getPrice()* quantityTrongGio;
    }
    public void tangSoLuong(int sl){
        this.quantityTrongGio += sl;
    }

}
