package model;

public class Payment {
    private String paymentId;
    private String studentName;
    private double amount;
    private String date;
    private String cardNumber;
    private String expiry;
    private String cvc;

    public Payment(String paymentId, String studentName, double amount, String date, String cardNumber, String expiry, String cvc) {
        this.paymentId = paymentId;
        this.studentName = studentName;
        this.amount = amount;
        this.date = date;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvc = cvc;
    }

    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public String getExpiry() { return expiry; }
    public void setExpiry(String expiry) { this.expiry = expiry; }
    public String getCvc() { return cvc; }
    public void setCvc(String cvc) { this.cvc = cvc; }

    @Override
    public String toString() {
        return paymentId + "," + studentName + "," + amount + "," + date + "," + cardNumber + "," + expiry + "," + cvc;
    }
}
