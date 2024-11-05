import java.util.Scanner;

class priority {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        String title[] = {"Processes", "BT", "Priority", "WT", "TAT"};

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        String processes[] = new String[n];
        int BT[] = new int[n];
        int priority[] = new int[n];
        int WT[] = new int[n];
        int TAT[] = new int[n];

        // Input process details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter process name (e.g., p" + (i+1) + "): ");
            processes[i] = scanner.next();

            System.out.print("Enter burst time for " + processes[i] + ": ");
            BT[i] = scanner.nextInt();

            System.out.print("Enter priority for " + processes[i] + ": ");
            priority[i] = scanner.nextInt();
        }

        WT[0] = 0;
        float total_BT = 0, total_WT = 0, total_TAT = 0;

        // Sort processes based on priority
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (priority[i] > priority[j]) {
                    // Swap process details
                    String temp = processes[i];
                    processes[i] = processes[j];
                    processes[j] = temp;

                    int temp1 = BT[i];
                    BT[i] = BT[j];
                    BT[j] = temp1;

                    int temp2 = priority[i];
                    priority[i] = priority[j];
                    priority[j] = temp2;
                }
            }
        }

        // Calculate waiting time and turnaround time
        for (int i = 1; i < n; i++) {
            WT[i] = BT[i-1] + WT[i-1];
        }

        for (int i = 0; i < n; i++) {
            TAT[i] = BT[i] + WT[i];
        }

        // Print table header
        for (int i = 0; i < 5; i++) {
            System.out.print(title[i] + "\t");
        }
        System.out.println("\n-----------------------------------------------------");

        // Print process details
        for (int i = 0; i < n; i++) {
            System.out.println(processes[i] + "\t\t" + BT[i] + "\t" + priority[i] + "\t\t" + WT[i] + "\t" + TAT[i]);
            total_BT += BT[i];
            total_WT += WT[i];
            total_TAT += TAT[i];
        }

        // Calculate and print averages
        float avg_BT = total_BT / n;
        float avg_WT = total_WT / n;
        float avg_TAT = total_TAT / n;

        System.out.println("\n");
        System.out.println("Total Burst Time = " + total_BT);
        System.out.println("Total Waiting Time = " + total_WT);
        System.out.println("Total Turn Around Time = " + total_TAT);
        System.out.println("Average Burst Time = " + avg_BT);
        System.out.println("Average Waiting Time = " + avg_WT);
        System.out.println("Average Turn Around Time = " + avg_TAT);

        scanner.close();
    }
}