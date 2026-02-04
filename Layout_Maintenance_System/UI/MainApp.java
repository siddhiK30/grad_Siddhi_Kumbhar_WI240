package UI;

import java.util.Scanner;

import Service.AdminService;
import Service.OwnerService;
import DAO.UserDAO;
import Entity.SiteStatus;
import Entity.SiteType;


public class MainApp {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            AdminService admin = new AdminService();
            OwnerService owner = new OwnerService();
            UserDAO userDAO = new UserDAO();

            System.out.println("====== LAYOUT MAINTENANCE SYSTEM ======");
            System.out.println("Login As:");
            System.out.println("1. Admin");
            System.out.println("2. Site Owner");
            System.out.print("Enter choice: ");

            int ch = readInt(sc);

            if (ch == 1) {
                int op;
                do {
                    System.out.println("\n------ ADMIN MENU ------");
                    System.out.println("1. Add Owner");
                    System.out.println("2. Edit Owner");
                    System.out.println("3. Remove Owner");
                    System.out.println("4. Add Site");
                    System.out.println("5. Edit Site");
                    System.out.println("6. Remove Site");
                    System.out.println("7. Generate Maintenance");
                    System.out.println("8. Collect Maintenance");
                    System.out.println("9. View All Pending Maintenance");
                    System.out.println("10. View Pending Maintenance by Site");
                    System.out.println("11. Show All Requests");
                    System.out.println("12. Approve Request");
                    System.out.println("13. Reject Request");
                    System.out.println("0. Exit");
                    System.out.print("Select option: ");

                    op = readInt(sc);
                    sc.nextLine(); 

                    try {
                        switch (op) {
                            case 1 -> {
                                System.out.print("Enter Owner ID: ");
                                int ownerId = readInt(sc);
                                sc.nextLine();

                                System.out.print("Enter Owner Name: ");
                                String name = sc.nextLine();

                                System.out.print(
                                        "Preferred Site Type (VILLA/APARTMENT/INDEPENDENT_HOUSE/OPEN_SITE): "
                                );
                                SiteType type = SiteType.valueOf(sc.nextLine().trim().toUpperCase());

admin.addOwnerWithSitePreference(ownerId, name, type);
                            }

                            case 2 -> {
                                System.out.print("Enter Owner ID: ");
                                int id = readInt(sc);
                                sc.nextLine();
                                System.out.print("Enter New Owner Name: ");
                                String name = sc.nextLine();
                                admin.editOwner(id, name);
                            }

                            case 3 -> {
                                System.out.print("Enter Owner ID: ");
                                int id = readInt(sc);
                                admin.removeOwner(id);
                            }

                            case 4 -> {
                                System.out.print("Enter Site Type (VILLA/APARTMENT/INDEPENDENT_HOUSE/OPEN_SITE): ");
                                SiteType type = SiteType.valueOf(sc.nextLine().trim().toUpperCase());

                                System.out.print("Enter Length: ");
                                int len = readInt(sc);
                                System.out.print("Enter Width: ");
                                int wid = readInt(sc);
                                sc.nextLine();

                                System.out.print("Enter Status (FREE/OCCUPIED): ");
                                String status = sc.nextLine().trim();

                                System.out.print("Enter Owner ID (0 if none): ");
                                int ownerId = readInt(sc);

admin.addSite(type, len, wid, SiteStatus.valueOf(status), ownerId);
                            }

                            case 5 -> {
                                System.out.print("Enter Site ID: ");
                                int siteId = readInt(sc);
                                sc.nextLine();
                                System.out.print("Enter New Status: ");
                                String status = sc.nextLine();
                                System.out.print("Enter New Length: ");
                                int len = readInt(sc);
                                System.out.print("Enter New Width: ");
                                int wid = readInt(sc);
                                admin.editSite(siteId, status, len, wid);
                            }

                            case 6 -> {
                                System.out.print("Enter Site ID: ");
                                int siteId = readInt(sc);
                                admin.removeSite(siteId);
                            }

                            case 7 -> {
                                admin.generateMaintenance();
                            }

                            case 8 -> {
                                System.out.print("Enter Site ID: ");
                                int siteId = readInt(sc);
                                admin.collectMaintenance(siteId);
                            }

                            case 9 -> {
                                admin.viewAllPendingMaintenance();
                            }

                            case 10 -> {
                                System.out.print("Enter Site ID: ");
                                int siteId = readInt(sc);
                                admin.viewPendingBySite(siteId);
                            }

                            case 11 -> {
                                
                                try {
                                    admin.getClass().getMethod("showRequestAll").invoke(admin);
                                } catch (NoSuchMethodException nsme) {
                                    System.out.println("AdminService does not implement showRequestAll()");
                                }
                            }

                            case 12 -> {
                                System.out.print("Enter Request ID: ");
                                int reqId = readInt(sc);
                                admin.approveRequest(reqId);
                            }

                            case 13 -> {
                                System.out.print("Enter Request ID: ");
                                int reqId = readInt(sc);
                                admin.rejectRequest(reqId);
                            }

                            case 0 -> System.out.println("Exiting Admin Menu");

                            default -> System.out.println("Invalid option");
                        }
                    } catch (Exception e) {
                        System.out.println("Operation failed: " + e.getMessage());
                        e.printStackTrace(System.out);
                    }

                } while (op != 0);

            } else {
                System.out.print("\nEnter Owner ID: ");
                int oid = readInt(sc);

                if (!userDAO.ownerExists(oid)) {
                    System.out.println("Owner does not exist");
                    return;
                }

                int op;
                do {
                    System.out.println("\n------ OWNER MENU ------");
                    System.out.println("1. View My Site");
                    System.out.println("2. Request Site Update");
                    System.out.println("3. View Maintenance");
                    System.out.println("4. Pay Maintenance");
                    System.out.println("0. Exit");
                    System.out.print("Select option: ");

                    op = readInt(sc);
                    sc.nextLine();

                    try {
                        switch (op) {
                            case 1 -> owner.viewMySite(oid);

                            case 2 -> {
                                System.out.print("Enter Site ID: ");
                                int siteId = readInt(sc);
                                sc.nextLine();
                                System.out.print("Enter New Status: ");
                                String status = sc.nextLine();
                                owner.requestSiteUpdate(siteId, status);
                            }

                            case 3 -> owner.viewMaintenance(oid);

                            case 4 -> {
                                System.out.print("Enter Site ID: ");
                                int siteId = readInt(sc);
                                System.out.print("Enter Payment Amount (enter 0 to mark full paid): ");
                                int amount = readInt(sc);
                                if (amount <= 0) {
                                    // full payment variant
                                    owner.payMaintenance(siteId);
                                } else {
                                    // partial payment variant (method overloaded in OwnerService)
                                    owner.payMaintenance(siteId, amount);
                                }
                            }

                            case 0 -> System.out.println("Exiting Owner Menu");

                            default -> System.out.println("Invalid option");
                        }
                    } catch (Exception e) {
                        System.out.println("Operation failed: " + e.getMessage());
                        e.printStackTrace(System.out);
                    }

                } while (op != 0);
            }
        } catch (Exception ex) {
            System.out.println("Fatal error: " + ex.getMessage());
            ex.printStackTrace(System.out);
        }
    }

    private static int readInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.nextLine();
            System.out.print("Please enter a valid integer: ");
        }
        return sc.nextInt();
    }
}