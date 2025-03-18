public class TicketReservationSystem {
    TicketNode head = null;

    static class TicketNode {
        int ticketId;
        String customerName;
        String movieName;
        int seatNumber;
        String bookingTime;
        TicketNode next;

        TicketNode(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
            this.ticketId = ticketId;
            this.customerName = customerName;
            this.movieName = movieName;
            this.seatNumber = seatNumber;
            this.bookingTime = bookingTime;
            this.next = null;
        }
    }

    public void addTicket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        TicketNode newTicket = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = newTicket;
            head.next = head;
        } else {
            TicketNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTicket;
            newTicket.next = head;
        }
    }

    public void removeTicket(int ticketId) {
        if (head == null) return;
        TicketNode temp = head, prev = null;
        while (temp.ticketId != ticketId) {
            if (temp.next == head) return;
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            TicketNode last = head;
            while (last.next != head) {
                last = last.next;
            }
            if (head == head.next) {
                head = null;
            } else {
                head = head.next;
                last.next = head;
            }
        } else {
            prev.next = temp.next;
        }
    }

    public void displayTickets() {
        if (head == null) return;
        TicketNode temp = head;
        do {
            System.out.println("Ticket ID: " + temp.ticketId + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Booking Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public int countTickets() {
        if (head == null) return 0;
        int count = 0;
        TicketNode temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket(1, "Alice", "Inception", 12, "10:30 AM");
        system.addTicket(2, "Bob", "Interstellar", 15, "2:00 PM");
        system.addTicket(3, "Charlie", "The Matrix", 20, "5:30 PM");

        System.out.println("All Tickets:");
        system.displayTickets();

        System.out.println("Total Tickets: " + system.countTickets());

        system.removeTicket(2);
        System.out.println("\nAfter Removing Ticket 2:");
        system.displayTickets();
        System.out.println("Total Tickets: " + system.countTickets());
    }
}