package jobcafe.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String title;
    @OneToOne
    @JoinColumn(referencedColumnName = "email")
    private JUser owner;
    @OneToOne
    @JoinColumn(referencedColumnName = "email")
    private JUser operator;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private TicketCategory category;
    @NotNull
    private boolean isOpen;
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    public Ticket(String title, JUser owner, TicketCategory category) {
        this.title = title;
        this.owner = owner;
        this.category = category;
        this.isOpen = true;
    }
}
