package ge.nika.loanapplication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LoanApplicationNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(LoanApplicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void employeeNotFoundHandler(LoanApplicationNotFoundException ex) {

    }
}
