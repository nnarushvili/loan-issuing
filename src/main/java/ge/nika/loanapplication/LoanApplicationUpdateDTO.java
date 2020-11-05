package ge.nika.loanapplication;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class LoanApplicationUpdateDTO {
    @NotNull
    private LoanApplicationStatus status;

    @NotNull
    private BigDecimal loanScore;

    public LoanApplicationUpdateDTO() {
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
