package jobcafe.model;

import java.net.URI;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(referencedColumnName = "email")
    private JUser author;
    @NotBlank
    private String content;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
    private Ticket ticket;
    @Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;
    private URI attachedFile;

    public Message(JUser author, String content, Ticket ticket, URI attachedFile) {
        this.author = author;
        this.content = content;
        this.ticket = ticket;
        this.attachedFile = attachedFile;
    }


    public Message(JUser author, String content, Ticket ticket) {
        this.author = author;
        this.content = content;
        this.ticket = ticket;
    }
}
