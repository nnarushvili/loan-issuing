package ge.nika.test;

import ge.nika.loanapplication.LoanApplication;
import ge.nika.loanapplication.LoanApplicationStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanApplicationTester {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAuthorizedLoanApplicationsCall() throws  Exception{
        mockMvc.perform(get("/loanapplications").header("Authorization", "Basic b3AxOnBhc3Mx"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void testLoanApplicationScoreCalculation() throws Exception {
        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setBirthDate(LocalDate.parse("01/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        loanApplication.setFirstName("Nika");
        loanApplication.setSalary(BigDecimal.valueOf(10000));
        loanApplication.setMonthlyLiability(BigDecimal.valueOf(2000));

        loanApplication = loanApplication.calculateScoreAndSetStatus();

        assertEquals(loanApplication.getLoanScore(), BigDecimal.valueOf(10970.0));
    }

    @Test
    public void testLoanApplicationStatusCalculation() throws Exception {
        LoanApplication loanApplication = new LoanApplication();

        loanApplication.setBirthDate(LocalDate.parse("01/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        loanApplication.setFirstName("Nika");
        loanApplication.setSalary(BigDecimal.valueOf(10000));
        loanApplication.setMonthlyLiability(BigDecimal.valueOf(2000));

        loanApplication = loanApplication.calculateScoreAndSetStatus();

        assertEquals(loanApplication.getStatus(), LoanApplicationStatus.APPROVED);

        loanApplication = new LoanApplication();

        loanApplication.setBirthDate(LocalDate.parse("01/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        loanApplication.setFirstName("Nika");
        loanApplication.setSalary(BigDecimal.valueOf(1));
        loanApplication.setMonthlyLiability(BigDecimal.valueOf(5000));

        loanApplication = loanApplication.calculateScoreAndSetStatus();

        assertEquals(loanApplication.getStatus(), LoanApplicationStatus.REJECTED);

        loanApplication = new LoanApplication();

        loanApplication.setBirthDate(LocalDate.parse("01/03/1998", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        loanApplication.setFirstName("Nika");
        loanApplication.setSalary(BigDecimal.valueOf(500));
        loanApplication.setMonthlyLiability(BigDecimal.valueOf(1));

        loanApplication = loanApplication.calculateScoreAndSetStatus();

        assertEquals(loanApplication.getStatus(), LoanApplicationStatus.MANUAL);
    }

}
