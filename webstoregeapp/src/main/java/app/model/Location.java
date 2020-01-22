package app.model;

/**
 * Class represents Location table from database
 */
public class Location {

    private int locationId;
    private int itemId;
    private String sector;
    private String line;
    private String shelf;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }


    @Override
    public String toString() {
        return "Sektor " + sector  +
                ", aleja " + line +
                ", półka " + shelf;
    }
}
