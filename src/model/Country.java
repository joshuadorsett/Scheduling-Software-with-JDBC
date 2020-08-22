package model;

import java.time.LocalDateTime;

public class Country {
    public int countryId;
    public String countryName;
    public String author;
    public LocalDateTime lastTimestamp;

    public Country(int countryId, String countryName, String author, LocalDateTime lastTimestamp) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.author = author;
        this.lastTimestamp = lastTimestamp;
    }
}
