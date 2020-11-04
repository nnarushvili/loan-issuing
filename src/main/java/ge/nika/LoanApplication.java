package ge.nika;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_application")
public class LoanApplication {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(min = 1, max = 255)
    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull
    @Column(name = "employer", nullable = false)
    private String employer;

    @NotNull
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @NotNull
    @Column(name = "monthly_liability", nullable = false)
    private BigDecimal monthlyLiability;

    @NotNull
    @Column(name = "requested_amount", nullable = false)
    private BigDecimal requestedAmount;

    @NotNull
    @Column(name = "requested_term", nullable = false)
    private Long requestedTermInDays;

    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status", nullable = false)
    private LoanApplicationStatus status;

    @Column(name = "loan_score", nullable = false)
    private BigDecimal loanScore;

    public LoanApplication() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getMonthlyLiability() {
        return monthlyLiability;
    }

    public void setMonthlyLiability(BigDecimal monthlyLiability) {
        this.monthlyLiability = monthlyLiability;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Long getRequestedTermInDays() {
        return requestedTermInDays;
    }

    public void setRequestedTermInDays(Long requestedTermInDays) {
        this.requestedTermInDays = requestedTermInDays;
    }

    public LoanApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(LoanApplicationStatus status) {
        this.status = status;
    }

    public BigDecimal getLoanScore() {
        return loanScore;
    }

    public void setLoanScore(BigDecimal loanScore) {
        this.loanScore = loanScore;
    }
}
