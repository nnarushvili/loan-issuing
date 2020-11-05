package ge.nika.operator;

import ge.nika.loanapplication.LoanApplication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
public class Operator implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "operator_loan_application", joinColumns = {@JoinColumn(name = "operator_id")}, inverseJoinColumns = {@JoinColumn(name = "loan_application_id")})
    private Set<LoanApplication> loanApplications;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return id.equals(operator.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loanApplications=" + loanApplications +
                '}';
    }

    public Operator() {
    }

    public Operator(@NotNull String username, @NotNull String password, @NotNull String firstName, @NotNull String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public Set<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(Set<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    public void addLoanApplication(LoanApplication loanApplication) {
        this.loanApplications.add(loanApplication);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_OPERATOR"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Operator hashPassword() throws NoSuchAlgorithmException {
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String hashedPassword = passwordEncoder.encode(this.password);
//        setPassword(hashedPassword);
        return this;
    }
}
