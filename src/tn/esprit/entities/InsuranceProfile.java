package tn.esprit.entities;

import java.math.BigDecimal;
import java.sql.Date;
import tn.esprit.services.UserService;

public class InsuranceProfile {
    private int profileInsuranceId;
    private int userId;
    private InsuranceType insuranceType;
    private BigDecimal insuranceCost;
    private Date insuranceExpDate;
    private String insuranceTel;
    private String insuranceCompany;
    private InsuranceStatus insuranceStatus;

    public enum InsuranceType {
        BASIC, PREMIUM
    }

    public enum InsuranceStatus {
        ACTIVE, NOT_ACTIVE
    }

    public InsuranceProfile() {
    }

    public InsuranceProfile(int profileInsuranceId, int userId, InsuranceType insuranceType,
                            BigDecimal insuranceCost, Date insuranceExpDate, String insuranceTel,
                            String insuranceCompany, InsuranceStatus insuranceStatus) {
        this.profileInsuranceId = profileInsuranceId;
        this.userId = userId;
        this.insuranceType = insuranceType;
        this.insuranceCost = insuranceCost;
        this.insuranceExpDate = insuranceExpDate;
        this.insuranceTel = insuranceTel;
        this.insuranceCompany = insuranceCompany;
        this.insuranceStatus = insuranceStatus;
    }

    public int getProfileInsuranceId() {
        return profileInsuranceId;
    }

    public void setProfileInsuranceId(int profileInsuranceId) {
        this.profileInsuranceId = profileInsuranceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public Date getInsuranceExpDate() {
        return insuranceExpDate;
    }

    public void setInsuranceExpDate(Date insuranceExpDate) {
        this.insuranceExpDate = insuranceExpDate;
    }

    public String getInsuranceTel() {
        return insuranceTel;
    }

    public void setInsuranceTel(String insuranceTel) {
        this.insuranceTel = insuranceTel;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public InsuranceStatus getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(InsuranceStatus insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public String getPatientName() {
    // Implement the logic to retrieve the patient name
    // from the user's table (first name + last name)
    // based on the userId or any other relevant information

    UserService userService = new UserService();
    User user = userService.getUserById(userId);

    if (user != null) {
        return user.getFirst_name() + " " + user.getLast_name();
    }

    return null;
}

}
