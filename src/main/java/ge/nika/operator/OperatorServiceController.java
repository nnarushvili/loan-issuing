package ge.nika.operator;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
public class OperatorServiceController {

    private final OperatorRepository registrationRepository;

    public OperatorServiceController(OperatorRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @PostMapping("/operators")
    public Operator addOperator(@RequestBody @NotNull @Valid Operator operator) throws NoSuchAlgorithmException {
        return registrationRepository.save(operator.hashPassword());
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
