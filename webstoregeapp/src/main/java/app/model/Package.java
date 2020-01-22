package app.model;

import java.util.Date;

/**
 * Class represents Package table from database
 */
public class Package {

    private int packageId;
    private int orderId;
    private float cost;
    private boolean isPayed;
    private boolean isDelivered;
    private Date dateOfProcess;
    private Date dateOfShipment;
    private Date dateOdDelivery;


    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public Date getDateOfProcess() {
        return dateOfProcess;
    }

    public void setDateOfProcess(Date dateOfProcess) {
        this.dateOfProcess = dateOfProcess;
    }

    public Date getDateOfShipment() {
        return dateOfShipment;
    }

    public void setDateOfShipment(Date dateOfShipment) {
        this.dateOfShipment = dateOfShipment;
    }

    public Date getDateOdDelivery() {
        return dateOdDelivery;
    }

    public void setDateOdDelivery(Date dateOdDelivery) {
        this.dateOdDelivery = dateOdDelivery;
    }

    @Override
    public String toString() {
        return "Package{" +
                "packageId=" + packageId +
                ", orderId=" + orderId +
                ", cost=" + cost +
                ", isPayed=" + isPayed +
                ", isDelivered=" + isDelivered +
                ", dateOfProcess=" + dateOfProcess +
                ", dateOfShipment=" + dateOfShipment +
                ", dateOdDelivery=" + dateOdDelivery +
                '}';
    }
}
