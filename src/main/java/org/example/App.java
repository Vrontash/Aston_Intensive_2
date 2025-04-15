package org.example;

import org.example.dao.UserDaoImpl;
import org.example.model.User;
import org.example.util.HibernateUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App 
{
    private static final UserDaoImpl userDao = new UserDaoImpl();
    private static final Scanner scanner = new Scanner(System.in);
    private static final Pattern agePattern = Pattern.compile("\\d+");
    private static final Pattern emailPattern = Pattern.compile("\\w+@\\w+.\\w+");
    public static void main( String[] args )
    {
        boolean isRunning = true;
        while (isRunning){
            System.out.println("Available actions:");
            System.out.println("1. Create user");
            System.out.println("2. Show all users");
            System.out.println("3. Find user by Id");
            System.out.println("4. Update user");
            System.out.println("5. Delete user");
            System.out.println("6. Exit");
            int choiceOfAction = Integer.parseInt(scanner.nextLine());
            switch (choiceOfAction){
                case 1:
                    createUser();
                    break;
                case 2:
                    showUsers();
                    break;
                case 3:
                    findUser();
                    break;
                case 4:
                    updateUser();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Action is not recognised!");;
            }
        }
        HibernateUtil.closeSessionFactory();
    }
    private static void createUser() {
        System.out.println("Creating user...");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!emailPattern.matcher(email).matches()) {
            System.out.println("Invalid Email!");
            return;
        }

        System.out.print("Enter age: ");
        String ageString = scanner.nextLine();
        if (!agePattern.matcher(ageString).matches()){
            System.out.println("Invalid age!");
            return;
        }
        int age = Integer.parseInt(ageString);

        User user = new User(name, email, age);
        userDao.save(user);
        System.out.println("User created with Id: " + user.getId());
    }
    private static void showUsers() {
        System.out.println("Showing all users... ");
        List<User> users = userDao.findAll();
        for (User user:users)
            System.out.println(user);
    }
    private static void findUser() {
        System.out.println("Finding user...");
        System.out.print("Enter user Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<User> user = userDao.findById(id);
        user.ifPresentOrElse(
                userData -> System.out.println("User found:" + userData),
                ()->System.out.println("User not found!")
        );
    }
    private static void updateUser() {
        System.out.println("Updating user...");

        System.out.print("Enter user's Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<User> userOpt = userDao.findById(id);
        if (userOpt.isEmpty()) {
            System.out.println("User not found!");
            return;
        }
        User user = userOpt.get();

        System.out.println("Old name: " + user.getName()+"\nIf you don't want to change the name press Enter");
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        if(!newName.trim().isEmpty() )
            user.setName(newName);

        System.out.println("Old email: " + user.getEmail()+"\nIf you don't want to change the email press Enter");
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        if(!newEmail.trim().isEmpty())
            if (!emailPattern.matcher(newEmail).matches()) {
                System.out.println("Invalid Email!");
                return;
            }
        user.setEmail(newEmail);

        System.out.println("Old age: " + user.getAge()+"\nIf you don't want to change the age press Enter");
        System.out.print("Enter age: ");
        String newAge = scanner.nextLine();
        if(!newAge.trim().isEmpty()) {
            if (!agePattern.matcher(newAge).matches()) {
                System.out.println("Invalid age!");
                return;
            }
            user.setAge(Integer.parseInt(newAge));
        }
        userDao.update(user);
        System.out.println("User updated");
    }
    private static void deleteUser() {
        System.out.println("Deleting user...");
        System.out.print("Enter user Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<User> userOpt = userDao.findById(id);
        if (userOpt.isEmpty()) {
            System.out.println("User not found");
            return;
        }
        userDao.delete(id);
        System.out.println("User deleted");
    }







}
