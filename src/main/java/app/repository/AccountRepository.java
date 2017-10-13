/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.repository;

import app.model.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.tokens.Token.ID;

/**
 * @author Team 20 HBV501G - Fall 2017
 * @author Bjarki Viðar Kristjánsson (bvk1@hi.is)
 * @author Hinrik Snær Guðmundsson (hsg30@hi.is)
 * @author Huy Van Nguyen (hvn1@hi.is)
 * @author Valentin Oliver Loftsson (vol1@hi.is)
 *
 * Repository class for Account. Responsible for fetching and saving data in the database
 */
public interface AccountRepository extends JpaRepository<Account, String> {

    /**
     * Find all account in database
     * @return list of all accounts in database
     */
    @Override
    public List<Account> findAll();


    /**
     * Save an account to database
     * @param account   the account to be saved
     * @return          if successful, the saved account is returned, else null
     */
    @Override
    public Account save(Account account);

    /**
     * Find an account by email
     * @param email   the account email
     * @return        the account with corresponding email if found. Null else.
     */
    public Account findByEmail(String email);
}
