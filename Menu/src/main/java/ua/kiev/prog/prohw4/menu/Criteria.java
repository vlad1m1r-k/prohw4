package ua.kiev.prog.prohw4.menu;

public class Criteria {
    private Integer priceFrom;
    private Integer priceTo;
    private Boolean discount;
    private Integer weightTo;

    public Criteria() {
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public void setPriceTo(Integer priceTo) {
        this.priceTo = priceTo;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    public void setWeightTo(Integer weightTo) {
        this.weightTo = weightTo;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public Integer getPriceTo() {
        return priceTo;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public Integer getWeightTo() {
        return weightTo;
    }

    public boolean isDataPresent() {
        return priceFrom != null || priceTo != null || discount || weightTo != null;
    }
}
