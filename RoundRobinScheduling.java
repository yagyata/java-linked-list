public class RoundRobinScheduling {
    ProcessNode head = null;
    int timeQuantum = 4; // Fixed time quantum

    static class ProcessNode {
        int processId;
        int burstTime;
        int priority;
        ProcessNode next;

        ProcessNode(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.priority = priority;
            this.next = null;
        }
    }

    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newProcess = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
        } else {
            ProcessNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    public void removeProcess(int processId) {
        if (head == null) return;
        ProcessNode temp = head, prev = null;
        while (temp.processId != processId) {
            if (temp.next == head) return;
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            ProcessNode last = head;
            while (last.next != head) last = last.next;
            if (head == head.next) head = null;
            else {
                head = head.next;
                last.next = head;
            }
        } else prev.next = temp.next;
    }

    public void executeProcesses() {
        if (head == null) return;
        ProcessNode current = head;
        int time = 0;
        while (head != null) {
            System.out.println("Executing Process: " + current.processId + " for time quantum " + timeQuantum);
            if (current.burstTime > timeQuantum) {
                time += timeQuantum;
                current.burstTime -= timeQuantum;
                current = current.next;
            } else {
                time += current.burstTime;
                removeProcess(current.processId);
                if (head == null) break;
                current = head;
            }
        }
        System.out.println("All processes executed.");
    }

    public void viewProcesses() {
        if (head == null) return;
        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    public static void main(String[] args) {
        RoundRobinScheduling scheduler = new RoundRobinScheduling();
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);
        scheduler.addProcess(4, 6, 2);

        System.out.println("Initial Processes:");
        scheduler.viewProcesses();
        System.out.println();

        scheduler.removeProcess(3);
        scheduler.viewProcesses();

        System.out.println("\nExecuting Processes:");
        scheduler.executeProcesses();
    }
}
