package src;

import src.controllers.*;
import src.entities.Customer;
import src.entities.Staff;

import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

/**
 * Main App for interacting with MOBLIMA
 *
 * @author Aryan
 */
public class App {
    /**
     * Main method for UI
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to the application. The first step would be to\n" +
                "load the database if not created\n" +
                "1. Create database (if this is your first time running, you should choose this)\n" +
                "2. Load database (if you've created the database once previously, then choose this option)\n");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            DataCreator.main(args);
        }
        MovieController mvc = new MovieController(Paths.get("").toAbsolutePath().toString() + "/data/movies.dat");
        ClientController cl = new ClientController(Paths.get("").toAbsolutePath().toString() + "/data/customers.dat", Paths.get("").toAbsolutePath().toString() + "/data/staffs.dat");
        //invalid constructor for ci controller, need two parameters
        CinemaController ci = new CinemaController(Paths.get("").toAbsolutePath().toString() + "/data/cinemas.dat", Paths.get("").toAbsolutePath().toString() + "/data/seats.dat");
        HolidayController h = new HolidayController(Paths.get("").toAbsolutePath().toString() + "/data/holidays.dat");


        App.welcomeScreen();
        String username;
        int option = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("How can we help you today?");
            System.out.println("1. Login as Staff\n" +
                    "2. Login as a Customer\n" +
                    "3. Exit");


            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Please enter your username");
                    username = sc.nextLine();
                    Staff s = null;
                    for (Staff staff : ClientController.getStaffList()) {
                        if (Objects.equals(staff.getUsername(), username)) s = staff;
                    }
                    if (s == null) {
                        System.out.println("Username does not exist. Try again at a later time");
                        break;
                    }
                    s.login(username);
                    SessionController.staffUI(s);
                    break;
                case 2:
                    System.out.println("Please enter your username");
                    username = sc.nextLine();
                    Customer c = null;
                    for (Customer customer : ClientController.getCustomerList()) {
                        if (customer.getUsername().equals(username)) c = customer;
                    }
                    if (c == null) {
                        System.out.println("Username does not exist. Try again at a later time");
                        break;
                    }
                    c.login(username);
                    SessionController.customerUI(c);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Make a valid choice");
                    break;
            }
        } while (option != 3);

    }

    /**
     * Welcome screen printout method
     */
    private static void welcomeScreen() {
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                                     ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("║      ▐▐          ▐▐     ▐▐▐▐▐▐      ▐▐▐▐▐▐▐▐    ▐▐          ▐▐    ▐▐          ▐▐         ▐▐▐▐       ║");
        System.out.println("║      ▐▐ ▐▐    ▐▐ ▐▐    ▐▐    ▐▐     ▐▐    ▐▐    ▐▐          ▐▐    ▐▐ ▐▐    ▐▐ ▐▐        ▐▐  ▐▐      ║");
        System.out.println("║      ▐▐  ▐▐  ▐▐  ▐▐   ▐▐      ▐▐    ▐▐    ▐▐    ▐▐          ▐▐    ▐▐  ▐▐  ▐▐  ▐▐       ▐▐    ▐▐     ║");
        System.out.println("║      ▐▐   ▐▐▐▐   ▐▐  ▐▐        ▐▐   ▐▐▐▐▐▐▐▐    ▐▐          ▐▐    ▐▐   ▐▐▐▐   ▐▐      ▐▐▐▐▐▐▐▐▐▐    ║");
        System.out.println("║      ▐▐          ▐▐   ▐▐      ▐▐    ▐▐    ▐▐    ▐▐          ▐▐    ▐▐          ▐▐     ▐▐        ▐▐   ║");
        System.out.println("║      ▐▐          ▐▐    ▐▐    ▐▐     ▐▐    ▐▐    ▐▐          ▐▐    ▐▐          ▐▐    ▐▐          ▐▐  ║");
        System.out.println("║      ▐▐          ▐▐     ▐▐▐▐▐▐      ▐▐▐▐▐▐▐▐    ▐▐▐▐▐▐▐▐▐▐  ▐▐    ▐▐          ▐▐   ▐▐            ▐▐ ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("║                         Welcome to Movie Booking and Listing Application                            ║");
        System.out.println("║                                                                                                     ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }
}
