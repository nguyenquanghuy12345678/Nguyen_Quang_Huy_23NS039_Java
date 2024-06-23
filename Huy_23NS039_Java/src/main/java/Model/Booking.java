package Model;

class Booking {
    private String roomNumber;
    private double totalPrice;

    public Booking(String roomNumber, double totalPrice) {
        this.roomNumber = roomNumber;
        this.totalPrice = totalPrice;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Phòng số: " + roomNumber + ", Tổng giá: $" + totalPrice;
    }
}

// Class Invoice để lưu thông tin về hóa đơn
class Invoice {
    private int invoiceNumber;
    private String customerName;
    private double totalAmount;

    public Invoice(int invoiceNumber, String customerName, double totalAmount) {
        this.invoiceNumber = invoiceNumber;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        return "Hóa đơn số: " + invoiceNumber + ", Tên khách hàng: " + customerName + ", Tổng số tiền: $" + totalAmount;
    }
}