package ge.nika;

import org.springframework.data.jpa.repository.JpaRepository;

interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}
