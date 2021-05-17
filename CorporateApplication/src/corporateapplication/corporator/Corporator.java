/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corporateapplication.corporator;

/**
 *
 * @author unique
 */
public class Corporator {

    private String id, corporateName, corporateId, accountantNumbers;

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

    public String getAccountantNumbers() {
        return accountantNumbers;
    }

    public void setAccountantNumbers(String accountantNumbers) {
        this.accountantNumbers = accountantNumbers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Corporator(String id, String corporateName, String corporateId, String accountantNumbers) {
        this.id = id;
        this.corporateName = corporateName;
        this.corporateId = corporateId;
        this.accountantNumbers = accountantNumbers;
    }

    public Corporator(String corporateName, String corporateId, String accountantNumbers) {
        this.corporateName = corporateName;
        this.corporateId = corporateId;
        this.accountantNumbers = accountantNumbers;
    }

    
    
}
