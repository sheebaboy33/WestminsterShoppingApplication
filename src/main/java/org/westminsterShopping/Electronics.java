package org.westminsterShopping;

public class Electronics extends Product{
    private String brand;
    private int warrantyPeriodInWeeks;

    public Electronics(String productId, String productName, double price,
                       String brand, int warrantyPeriodInWeeks) {
        super(productId, productName, price);
        this.brand = brand;
        this.warrantyPeriodInWeeks = warrantyPeriodInWeeks;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriodInWeeks() {
        return warrantyPeriodInWeeks;
    }

    public void setWarrantyPeriodInWeeks(int warrantyPeriodInWeeks) {
        this.warrantyPeriodInWeeks = warrantyPeriodInWeeks;
    }

    @Override
    public String toString() {
        return "Product Type: Electronics " +
                super.toString() +
                "Brand: '" + brand + '\'' +
                "Warranty Period In Weeks: " + warrantyPeriodInWeeks;
    }
}
