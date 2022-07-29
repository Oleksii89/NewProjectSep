package dto;

public class Order {
    private String status;
    private Long courierId;
    private String customerName;
    private String customerPhone;
    private String comment;
    private Long id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order {" + '\n' +
                "status=" + status + '\n' +
                "courierId=" + courierId + '\n' +
                "customerName=" + customerName + '\n' +
                "customerPhone=" + customerPhone + '\n' +
                "comment=" + comment + '\n' +
                "id=" + id +
                "}";
    }
}
