package com.example.lab5_sql;

public class OperatingSystem {
    private long id; // Unique subject ID
    private String name;

    public OperatingSystem(long id, String name, String company, String version, String rating, String architecture) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.version = version;
        this.rating = rating;
        this.architecture = architecture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    private String company;
    private String version;
    private String rating;
    private String architecture;

    // Constructors, getters, setters, and other methods go here
}
