import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //name
        System.out.print("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please enter your last name: ");
        String lastName = scanner.nextLine();
        String fullName = firstName + " " + lastName;

        //weight
        int weight;
        while (true) {
            try {
                System.out.print("Enter your weight in pounds (e.g. 175): ");
                weight = Integer.parseInt(scanner.nextLine());
                if (weight > 0) break;
                System.out.println("Weight must be a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        //height
        int height;
        while (true) {
            try {
                System.out.print("Enter your height in inches (e.g. 62): ");
                height = Integer.parseInt(scanner.nextLine());
                if (height > 0) break;
                System.out.println("Height must be a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        //metric support
        double heightMeters = height * 0.0254;
        double weightKg = weight * 0.4536;

        //bmi
        double bmi = weightKg / (heightMeters * heightMeters);
        String bmiCategory;
        if (bmi < 18.5) {
            bmiCategory = "Underweight";
        } else if (bmi < 25.0) {
            bmiCategory = "Normal weight";
        } else if (bmi < 30.0) {
            bmiCategory = "Overweight";
        } else {
            bmiCategory = "Obese";
        }

        //gender
        String gender;
        while (true) {
            System.out.print("Please enter your gender (M/F): ");
            gender = scanner.nextLine().toUpperCase();
            if (gender.equals("M") || gender.equals("F")) break;
            System.out.println("Invalid gender. Please enter M or F.");
        }

        //age
        double age;
        while (true) {
            try {
                System.out.print("Please enter your age in years: ");
                age = scanner.nextDouble();
                if (age > 0 && age < 120) break;
                System.out.println("Please enter a valid age.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear buffer
            }
        }

        //activity
        System.out.println("\nSelect your activity level:");
        System.out.println("[A] Resting (sleeping, reclining)");
        System.out.println("[B] Sedentary (restricted mobility, minimal movement)");
        System.out.println("[C] Light (office work, seldom walking)");
        System.out.println("[D] Moderate (fairly active, light manual labour, riding bike)");
        System.out.println("[E] Very active (manual labour, team sports)");
        System.out.println("[F] Full time athlete, consistent hard manual labour");

        String activityLevel;
        while (true) {
            System.out.print("Enter the letter corresponding to your activity level: ");
            activityLevel = scanner.next().toUpperCase();
            if ("ABCDEF".contains(activityLevel)) break;
            System.out.println("Invalid activity level.");
        }

        //bmr
        double bmr;
        if (gender.equals("M")) {
            bmr = 10 * weightKg + 6.25 * (height * 2.54) - 5 * age + 5;
        } else {
            bmr = 10 * weightKg + 6.25 * (height * 2.54) - 5 * age - 161;
        }

        //activity multiplying
        double activityMultiplier = getActivityMultiplier(gender, activityLevel);
        double tdee = bmr * activityMultiplier;

        //calculate things
        System.out.println("\n--- Health & Fitness Profile ---");
        System.out.println("Name: " + fullName);
        System.out.println("Gender: " + gender);
        System.out.printf("Height: %.2f m\n", heightMeters);
        System.out.printf("Weight: %.2f kg\n", weightKg);
        System.out.printf("BMI: %.2f\n", bmi);
        System.out.println("BMI Category: " + bmiCategory);
        System.out.printf("BMR: %.0f calories\n", bmr);
        System.out.println("Activity Level: " + activityLevel);
        System.out.printf("TDEE: %.0f calories\n", tdee);
    }

    private static double getActivityMultiplier(String gender, String level) {
        if (gender.equals("M")) {
            return switch (level) {
                case "A" -> 1.0;
                case "B" -> 1.3;
                case "C" -> 1.6;
                case "D" -> 1.7;
                case "E" -> 2.1;
                case "F" -> 2.4;
                default -> 1.0;
            };
        } else {
            return switch (level) {
                case "A" -> 1.0;
                case "B" -> 1.3;
                case "C" -> 1.5;
                case "D" -> 1.6;
                case "E" -> 1.9;
                case "F" -> 2.2;
                default -> 1.0;
            };
        }
    }
}
