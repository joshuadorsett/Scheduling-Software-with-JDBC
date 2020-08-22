package model;

import java.time.LocalDate;

public class Country {
    public int countryId;
    public String countryName;
    public String author;
    public LocalDate lastDate;

    public Country(int countryId, String countryName, String author, LocalDate lastDate) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.author = author;
        this.lastDate = lastDate;
    }
}
