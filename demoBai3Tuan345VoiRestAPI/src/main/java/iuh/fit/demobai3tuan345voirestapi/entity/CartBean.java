package iuh.fit.demobai3tuan345voirestapi.entity;

import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class CartBean implements Serializable {
    private List<CartItemBean> dsCartItem= new ArrayList<>();

    public CartBean(List<CartItemBean> dsCartItem) {
        this.dsCartItem = dsCartItem;
    }

    public CartBean() {
    }

    public List<CartItemBean> getDsCartItem() {
        return dsCartItem;
    }

    public void setDsCartItem(List<CartItemBean> dsCartItem) {
        this.dsCartItem = dsCartItem;
    }

    public void addProduct(Product p,int soLuong){
        for(CartItemBean item: dsCartItem){
            if(p.getId()==item.getProduct().getId()){
                item.tangSoLuong(soLuong);
                return;
            }
        }
        dsCartItem.add(new CartItemBean(p,soLuong));
    }

    public void updateQuantity(int id, int sl){
        for(CartItemBean item: dsCartItem){
            if(id==item.getProduct().getId()){
                item.setQuantityTrongGio(sl);
                return;
            }
        }
    }

    public void removeCartItem(int id){
        dsCartItem.removeIf(it-> id==it.getProduct().getId());
    }

    public void clear(){
        dsCartItem.clear();
    }

    public double getTongTienToanBoGioHang(){
        int tong=0;
        for(CartItemBean it: dsCartItem){
            tong+=it.tinhTongTien1ItemTrongGio();
        }
        return tong;
    }
}
