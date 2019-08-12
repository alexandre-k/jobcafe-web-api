package jobcafe.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Date;

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
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Ticket ticket;
    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
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
