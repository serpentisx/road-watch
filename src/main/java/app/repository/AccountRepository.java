
package app.repository;

import app.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 * @date Last updated on 12 November 2017
 *
 * Repository class for Account. Responsible for fetching and saving data.
 */
public interface AccountRepository extends JpaRepository<Account, String> {

    @Override
    public List<Account> findAll();

    @Override
    public Account save(Account account);

    /**
     * Find an account by email
     * @param email     the account email
     * @return          the account having the corresponding email, null if not found
     */
    public Account findByEmail(String email);
}
