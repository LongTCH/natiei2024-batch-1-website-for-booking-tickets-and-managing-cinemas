package cinemas.dtos;

public class ShowtimeSeatDto {
    private int id;
    private String name;
    private boolean available;
    private String type;
    private boolean selected;
    private int price;

    public ShowtimeSeatDto(int id, String name, boolean available, String type, boolean selected, int price) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.type = type;
        this.selected = selected;
        this.price = price;
    }
    public ShowtimeSeatDto(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
