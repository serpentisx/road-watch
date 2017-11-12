
package app.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Bjarki Viðar Kristjánsson
 * @date Last updated on 25 October 2017
 * 
 * Model for the LoginEvent
 */
@Entity
@Table (name="LoginEvent")
public class LoginEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loginEventId;
    
    @Column (name="stamp")
    private Instant timestamp;  // login event timestamp
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "email")
    private Account account;    // user account associated with the LoginEvent
    
    public LoginEvent(Account account){
        this.account = account;
        this.timestamp = Instant.now();
    }
    
    public LoginEvent () {}

    public Long getId() {
        return loginEventId;
    }

    public Account getAccount() {
        return account;
    }

    public Instant getTime() {
        return timestamp;
    }    
}