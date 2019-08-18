package jobcafe.model;

import lombok.Data;

@Data
public class NewTicket {
    String title;
    String owner;
    String category;
    String content;
}
