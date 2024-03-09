import controller.UserController;
import model.User;
import view.View;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    private final static UserController userController = new UserController();
    public static void main(String[] args) {
        boolean br=true;
        Scanner scanner = new Scanner(System.in);
        do{
            View.ui();
            switch (View.option()){
                case "1"->{
                    //display
                    System.out.println("✨ Display All User ✨");
                    userController.getAllUsers().forEach(System.out::println);
                }
                case "2"->{
                    //add
                    System.out.println("👤 Add User 👤");
                    System.out.print("Enter User Name: ");
                    String userName = new Scanner(System.in).nextLine();
                    System.out.print("Enter User Email: ");
                    String userEmail = new Scanner(System.in).nextLine();
                    System.out.print("Enter User Password: ");
                    String userPassword = new Scanner(System.in).nextLine();

                    System.out.print("Is Deleted? ( 1-[True] | 0-[False] ) : ");
                    String chooseisDeleted = new Scanner(System.in).nextLine();
                    boolean isDeleted=false;
                    if (chooseisDeleted.equals("1")){
                        isDeleted=true;
                    }else if (chooseisDeleted.equals("0")){
                        isDeleted=false;
                    }
                    else {
                        System.out.println("Invalid....");
                        break;
                    }
                    System.out.print("Is Verified? ( 1-[True] | 0-[False] ) : ");
                    String chooseisVerified = new Scanner(System.in).nextLine();
                    boolean isVerified=false;
                    if (chooseisVerified.equals("1")){
                        isVerified=true;
                    }else if (chooseisVerified.equals("0")){
                        isVerified=false;
                    }
                    else {
                        System.out.println("Invalid....");
                        break;
                    }
                    String userUUID = UUID.randomUUID().toString();
                    System.out.println("Are you sure ?? [Y/N] :");
                    String choose = new Scanner(System.in).next();
                    if (choose.equalsIgnoreCase("y")){
                        User newUser = new User(null, userUUID, userName, userEmail, userPassword, isDeleted, isVerified);
                        userController.insertUser(newUser);
                        System.out.println("✅ User added successfully!");
                    }
                }
                case "3"->{
                    //delete
                    System.out.print("Enter User ID to Delete : ");
                    String userId = new Scanner(System.in).next();
                    System.out.println("Are you sure ?? [Y/N] :");
                    String choose = new Scanner(System.in).next();
                    if (choose.equalsIgnoreCase("y")){
                        userController.deleteUser(Integer.valueOf(userId));
                        System.out.println("✅ User deleted successfully!");
                    }

                }
                case "4"->{
                    //update
                    System.out.println("🔄 Update User 🔄");
                    System.out.print("Enter User ID to Update: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character


                    // Retrieve user details
                    User existingUser = userController.searchUserByID(userId);
                    if (existingUser != null) {
                        System.out.print("Enter New User Name: ");
                        String newName = new Scanner(System.in).nextLine();
                        System.out.print("Enter New User Email: ");
                        String newEmail = new Scanner(System.in).nextLine();
                        System.out.print("Enter New User Password: ");
                        String newPassword = new Scanner(System.in).nextLine();
                        System.out.print("Is Deleted? ( 1-[True] | 0-[False] ) : ");
                        String chooseisDeleted = new Scanner(System.in).nextLine();
                        boolean isDeleted=false;
                        if (chooseisDeleted.equals("1")){
                            isDeleted=true;
                        }else if (chooseisDeleted.equals("0")){
                            isDeleted=false;
                        }
                        else {
                            System.out.println("Invalid....");
                            break;
                        }
                        System.out.print("Is Verified? ( 1-[True] | 0-[False] ) : ");
                        String chooseisVerified = new Scanner(System.in).nextLine();
                        boolean isVerified=false;
                        if (chooseisVerified.equals("1")){
                            isVerified=true;
                        }else if (chooseisVerified.equals("0")){
                            isVerified=false;
                        }
                        else {
                            System.out.println("Invalid....");
                            break;
                        }
                        System.out.println("Are you sure ?? [Y/N] :");
                        String choose = new Scanner(System.in).next();
                        if (choose.equalsIgnoreCase("y")){
                            // Update user details
                            existingUser.setUserName(newName);
                            existingUser.setUserEmail(newEmail);
                            existingUser.setUserPassword(newPassword);
                            existingUser.setIsDeleted(isDeleted);
                            existingUser.setIsVerified(isVerified);

                            // Perform update operation
                            userController.updateUser(existingUser);
                            System.out.println("✅ User updated successfully!");
                        }
                    } else {
                        System.out.println("❌ User not found!");
                    }
                }
                case "5"->{
                    //search
                    try {
                        System.out.print("Enter User ID to Search: ");
                        int searchId = new Scanner(System.in).nextInt();
                        User searchedUser = userController.searchUserByID(searchId);
                        if (searchedUser != null) {
                            System.out.println(searchedUser);
                        } else {
                            System.out.println("❌ User not found!");
                        }
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                }
                case "6"->{
                    br=false;
                    System.out.println("Exit The Application.......");
                }
                default -> System.out.println("❌ Invalid Input!");
            }
        }while(br);
    }
}
