package jobcafe.model;


public enum TicketStatus {
    CLOSED("CLOSED"),
    OPEN("OPEN");
    private String status;

    TicketStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
