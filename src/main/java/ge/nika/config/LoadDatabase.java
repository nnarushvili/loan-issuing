package ge.nika.config;

import ge.nika.loanapplication.LoanApplication;
import ge.nika.loanapplication.LoanApplicationRepository;
import ge.nika.operator.Operator;
import ge.nika.operator.OperatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OperatorRepository operatorRepository, LoanApplicationRepository loanApplicationRepository) {
        return args -> {
            log.info("Preloading " + operatorRepository.save(new Operator("admin", "admin", "Nika", "Narushvili")));
            log.info("Preloading " + loanApplicationRepository.save(new LoanApplication("Nika", "Narushvili", LocalDate.parse("01/03/1998",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")), "MyEmployer", BigDecimal.valueOf(10000), BigDecimal.valueOf(2000), BigDecimal.valueOf(15000), 15L)
            .calculateScoreAndSetStatus()));

            log.info("Preloading " + loanApplicationRepository.save(new LoanApplication("George", "Smith", LocalDate.parse("01/03/1975",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy")), "Super Big Company", BigDecimal.valueOf(100000.5), BigDecimal.valueOf(2000), BigDecimal.valueOf(150000), 15L)
                    .calculateScoreAndSetStatus()));
        };
    }

}
