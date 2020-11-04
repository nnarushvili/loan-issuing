package ge.nika;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
public class LoanServiceController {

    private final LoanApplicationRepository loanApplicationRepository;

    public LoanServiceController(LoanApplicationRepository loanApplicationRepository) {
        this.loanApplicationRepository = loanApplicationRepository;
    }

    @PostMapping("/loanapplications")
    public LoanApplication createLoanApplication(@RequestBody @NotNull @Valid LoanApplication loanApplication) {
        return loanApplicationRepository.save(loanApplication);
    }

    @GetMapping("/loanapplications")
    public List<LoanApplication> getLoanApplications() {
        return loanApplicationRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
    }

    @DeleteMapping("/loanapplications/{id}")
    public void deleteLoanApplication(@PathVariable Long id) {
        loanApplicationRepository.deleteById(id);
    }

    @PatchMapping("/loanapplications/{id}")
    public LoanApplication updateLoanApplication(@PathVariable Long id,@RequestBody @NotNull @Valid LoanApplicationUpdateDTO dto) {
        Optional<LoanApplication> loanApplication = loanApplicationRepository.findById(id);
        if (loanApplication.isPresent()) {
            //TODO: finish implementation
            return null;
        }
        throw new LoanApplicationNotFoundException();
    }

}
