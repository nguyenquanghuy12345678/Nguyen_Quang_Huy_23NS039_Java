package Model;


public class Food {
    private int idOrder;
    private String name;
    private String idPhong;
    private String nameFood;
    private int amount;
    private boolean thanhToan;

    public Food(int idOrder, String name, String idPhong, String nameFood, int amount, boolean thanhToan) {
        this.idOrder = idOrder;
        this.name = name;
        this.idPhong = idPhong;
        this.nameFood = nameFood;
        this.amount = amount;
        this.thanhToan = thanhToan;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(boolean thanhToan) {
        this.thanhToan = thanhToan;
    }
}
