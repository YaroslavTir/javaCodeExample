package ru.fls.privateoffice.util.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * User: NKarataeva
 * Date: 06.04.12
 * Time: 10:03
 */
public class AddressDTO {
    @NotNull
    @Digits(integer = 6, fraction = 0)
    private String index;
    @NotNull
    private String region;
    private String raion;
    @NotNull
    private String locality;
    @NotNull
    private String localityTypeKey;
    @NotNull
    private String localityTypeValue;
    @NotNull
    private String localityTypeDescription;
    @NotNull
    private String street;
    @NotNull
    private String streetType;
    @NotNull
    private String house;
    private String building;
    private String structure;
    private String apartment;

    public String getLocalityTypeKey() {
        return localityTypeKey;
    }

    public void setLocalityTypeKey(String localityTypeKey) {
        this.localityTypeKey = localityTypeKey;
    }

    public String getLocalityTypeValue() {
        return localityTypeValue;
    }

    public void setLocalityTypeValue(String localityTypeValue) {
        this.localityTypeValue = localityTypeValue;
    }

    public String getLocalityTypeDescription() {
        return localityTypeDescription;
    }

    public void setLocalityTypeDescription(String localityTypeDescription) {
        this.localityTypeDescription = localityTypeDescription;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRaion() {
        return raion;
    }

    public void setRaion(String raion) {
        this.raion = raion;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

}
