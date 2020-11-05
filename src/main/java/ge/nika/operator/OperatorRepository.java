package ge.nika.operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {

    public Optional<Operator> findByUsername(String username);
}
