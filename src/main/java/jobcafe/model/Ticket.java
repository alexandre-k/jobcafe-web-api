package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
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
    @NotBlank
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date createdDate;

    public Ticket(String title, JUser owner, TicketCategory category) {
        this.title = title;
        this.owner = owner;
        this.category = category;
        this.status = TicketStatus.OPEN;
    }
}
