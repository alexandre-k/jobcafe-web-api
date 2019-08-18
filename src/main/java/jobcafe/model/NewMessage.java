package jobcafe.model;

import java.util.Date;

import lombok.Data;

@Data
public class NewMessage {
    JUser author;
    String content;
    Integer ticketId;
    Date createdDate;
}
