package com.API;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PersonalLoan {

    public static class loansInReviewClass {
        String id;
        UUID uuid;
        LoanStatus status;
        LoanType productType;
        String sourceSystem;
        Boolean hasOpenBackendCounter;
        LoanPurpose purpose;
        Date createDate;
        String postIssuanceStatus;

        public Boolean getHasOpenBackendCounter() {
            return hasOpenBackendCounter;
        }

        public String getId() {
            return id;
        }

        public String getPostIssuanceStatus() {
            return postIssuanceStatus;
        }

        public Date getCreateDate() {
            return createDate;
        }

        public LoanType getProductType() {
            return productType;
        }

        public LoanPurpose getPurpose() {
            return purpose;
        }

        public String getSourceSystem() {
            return sourceSystem;
        }

        public LoanStatus getStatus() {
            return status;
        }

        public UUID getUuid() {
            return uuid;
        }
    }

    public static class loanAccountSummaryAtoClass {
        String loanAccountNumber;
        String postIssuanceLoanStatus;
        Integer daysPastDue;
        String loanProductType;

        public Integer getDaysPastDue() {
            return daysPastDue;
        }

        public String getLoanAccountNumber() {
            return loanAccountNumber;
        }

        public String getLoanProductType() {
            return loanProductType;
        }

        public String getPostIssuanceLoanStatus() {
            return postIssuanceLoanStatus;
        }
    }

    String firstName;
    String userId;
    UUID userUuid;
    List<String> loanApplications = new ArrayList<>();
    List<loansInReviewClass> loansInReview = new ArrayList<>();
    List<loanAccountSummaryAtoClass> loanAccountSummaryAto = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public String getUserId() {
        return userId;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public List<loanAccountSummaryAtoClass> getLoanAccountSummaryAto() {
        return loanAccountSummaryAto;
    }

    public List<loansInReviewClass> getLoansInReview() {
        return loansInReview;
    }

    public List<String> getLoanApplications() {
        return loanApplications;
    }
}
