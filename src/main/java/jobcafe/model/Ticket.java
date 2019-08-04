package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;

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

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String title;
    @OneToOne
    @JoinColumn(referencedColumnName = "email")
    private User owner;
    @OneToOne
    @JoinColumn(referencedColumnName = "email")
    private User operator;
    @OneToOne
    @JoinColumn(referencedColumnName = "label")
    private TicketCategory category;
    @NotBlank
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
