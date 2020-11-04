package ge.nika;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;

@RestController
public class OperatorRegistrationController {

    private final OperatorRepository registrationRepository;

    public OperatorRegistrationController(OperatorRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @PostMapping("/operators")
    public Operator addOperator(@RequestBody @NotNull @Valid Operator operator) throws NoSuchAlgorithmException {
        return registrationRepository.save(operator.hashPassword());
    }
}
