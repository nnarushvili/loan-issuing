package ge.nika;

import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
public class LoanServiceController {

    private final LoanApplicationRepository loanApplicationRepository;

    public LoanServiceController(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @GetMapping("/loanapplications")
    public List<LoanApplication> getLoanApplications() {
        return loanApplicationRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @PostMapping("/loanapplications")
    public LoanApplication createLoanApplication(@RequestBody @NotNull @Valid LoanApplication loanApplication) {
        return loanApplicationRepository.save(loanApplication.calculateScoreAndSetStatus());
    }

    @DeleteMapping("/loanapplications/{id}")
    public void deleteLoanApplication(@PathVariable Long id) {
        loanApplicationRepository.deleteById(id);
    }

    @PutMapping("/loanapplications/{id}")
    public LoanApplication updateLoanApplication(@PathVariable Long id, @RequestBody @NotNull @Valid LoanApplicationUpdateDTO dto) {
        return loanApplicationRepository.findById(id)
                .map(
                        loanApplication -> {
                            loanApplication.setLoanScore(dto.getLoanScore());
                            if (loanApplication.getStatus() == LoanApplicationStatus.MANUAL) {
                                loanApplication.setStatus(dto.getStatus());
                            }
                            return loanApplicationRepository.save(loanApplication);
                        }
                )
                .orElseThrow(LoanApplicationNotFoundException::new);
    }

}
