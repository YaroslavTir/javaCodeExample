package ru.fls.privateoffice.util.dto;

/**
 * User: NFadin
 * Date: 19.12.13
 * Time: 18:43
 */
public class ExpressPasswordRestoreDTO extends BasePasswordRestoreDTO {

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
