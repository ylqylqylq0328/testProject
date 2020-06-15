package com.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

public class APITest {

    final String endpoint = "https://credapi.credify.tech/api/brportorch/v2/login";

    @Test
    public void testCaseSuccess() throws IOException {

        final String urlParameters = "{\"username\":\"coding.challenge.login@upgrade.com\",\"password\":\"On$3XcgsW#9q\"}";

        final HttpUtils.HttpResponse response = HttpUtils.getHttpContent( endpoint, urlParameters );
        assertEquals(HttpURLConnection.HTTP_OK,response.getResponseCode());

        ObjectMapper mapper = new ObjectMapper();

        PersonalLoan expectedPersonalLoan = mapper.readValue(getFileFromResources("correctResponse.json"), PersonalLoan.class);
        PersonalLoan actuallyPersonalLoan = mapper.readValue(response.getContent(), PersonalLoan.class);
        assertAll(
                () -> assertEquals(expectedPersonalLoan.getFirstName(),actuallyPersonalLoan.getFirstName()),
                () -> assertEquals(expectedPersonalLoan.getUserId(),actuallyPersonalLoan.getUserId()),
                () -> assertEquals(expectedPersonalLoan.getUserUuid(),actuallyPersonalLoan.getUserUuid()),
                () -> assertEquals(expectedPersonalLoan.getLoanApplications().size(),actuallyPersonalLoan.getLoanApplications().size() ),
                () -> assertEquals(expectedPersonalLoan.getLoansInReview().size(),actuallyPersonalLoan.getLoansInReview().size()),
                () -> assertEquals(expectedPersonalLoan.getLoanAccountSummaryAto().size(),actuallyPersonalLoan.getLoanAccountSummaryAto().size())
        );
        Iterator<String> expectedLoanApplications = expectedPersonalLoan.getLoanApplications().iterator();
        Iterator<String> actuallyLoanApplications = actuallyPersonalLoan.getLoanApplications().iterator();
        while (expectedLoanApplications.hasNext() && actuallyLoanApplications.hasNext()) {
            assertEquals(expectedLoanApplications.next(),actuallyLoanApplications.next());
        }
        Iterator<PersonalLoan.loansInReviewClass> expectedLoansInReviewClass = expectedPersonalLoan.getLoansInReview().iterator();
        Iterator<PersonalLoan.loansInReviewClass> actuallyLoansInReviewClass = actuallyPersonalLoan.getLoansInReview().iterator();
        while (expectedLoansInReviewClass.hasNext() && actuallyLoansInReviewClass.hasNext()) {
            PersonalLoan.loansInReviewClass eachExpectedLoansInReviewClass = expectedLoansInReviewClass.next();
            PersonalLoan.loansInReviewClass eachActuallyLoansInReviewClass = actuallyLoansInReviewClass.next();
            assertAll(
                    () -> assertEquals(eachExpectedLoansInReviewClass.getId(), eachActuallyLoansInReviewClass.getId()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getUuid(), eachActuallyLoansInReviewClass.getUuid()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getStatus(), eachActuallyLoansInReviewClass.getStatus()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getProductType(), eachActuallyLoansInReviewClass.getProductType()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getSourceSystem(), eachActuallyLoansInReviewClass.getSourceSystem()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getHasOpenBackendCounter(), eachActuallyLoansInReviewClass.getHasOpenBackendCounter()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getPurpose(), eachActuallyLoansInReviewClass.getPurpose()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getCreateDate(), eachActuallyLoansInReviewClass.getCreateDate()),
                    () -> assertEquals(eachExpectedLoansInReviewClass.getPostIssuanceStatus(), eachActuallyLoansInReviewClass.getPostIssuanceStatus())
            );
        }
        Iterator<PersonalLoan.loanAccountSummaryAtoClass> expectedLoanAccountSummaryAtoClass = expectedPersonalLoan.getLoanAccountSummaryAto().iterator();
        Iterator<PersonalLoan.loanAccountSummaryAtoClass> actuallyLoanAccountSummaryAtoClass = actuallyPersonalLoan.getLoanAccountSummaryAto().iterator();
        while (expectedLoanAccountSummaryAtoClass.hasNext() && actuallyLoanAccountSummaryAtoClass.hasNext()) {
            PersonalLoan.loanAccountSummaryAtoClass eachExpectedLoanAccountSummaryAtoClass = expectedLoanAccountSummaryAtoClass.next();
            PersonalLoan.loanAccountSummaryAtoClass eachActuallyLoanAccountSummaryAtoClass = actuallyLoanAccountSummaryAtoClass.next();
            assertAll(
                    () -> assertEquals(eachExpectedLoanAccountSummaryAtoClass.getLoanAccountNumber(), eachActuallyLoanAccountSummaryAtoClass.getLoanAccountNumber()),
                    () -> assertEquals(eachExpectedLoanAccountSummaryAtoClass.getLoanProductType(), eachActuallyLoanAccountSummaryAtoClass.getLoanProductType()),
                    () -> assertEquals(eachExpectedLoanAccountSummaryAtoClass.getDaysPastDue(), eachActuallyLoanAccountSummaryAtoClass.getDaysPastDue()),
                    () -> assertEquals(eachExpectedLoanAccountSummaryAtoClass.getPostIssuanceLoanStatus(), eachActuallyLoanAccountSummaryAtoClass.getPostIssuanceLoanStatus())            );
        }
    }

    @Test
    public void testCaseFail401() throws IOException {

        final String urlParameters = "{\"username\":\"coding1.challenge.login@upgrade.com\",\"password\":\"On$3XcgsW#9q\"}";
        final HttpUtils.HttpResponse response = HttpUtils.getHttpContent( endpoint, urlParameters );
        assertEquals(HttpURLConnection.HTTP_UNAUTHORIZED,response.getResponseCode());
    }

    private File getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
