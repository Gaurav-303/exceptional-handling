package RealTimeExample;


class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) {
        super(message);
    }
}


class CalculationException extends RuntimeException {
    public CalculationException(String message) {
        super(message);
    }
}


enum PolicyType {
    LIFE, HEALTH, VEHICLE
}


class Customer {
    private String name;
    private Integer age;
    private Boolean hasHealthIssues;
    private String vehicleType;

    public Customer(String name, Integer age, Boolean hasHealthIssues, String vehicleType) {
        this.name = name;
        this.age = age;
        this.hasHealthIssues = hasHealthIssues;
        this.vehicleType = vehicleType;
    }

    public String getName() { return name; }
    public Integer getAge() { return age; }
    public Boolean getHasHealthIssues() { return hasHealthIssues; }
    public String getVehicleType() { return vehicleType; }
}


class PremiumCalculator {

    public double calculatePremium(Customer customer, PolicyType type)
            throws DataNotFoundException {

        double premium;

        switch (type) {
            case LIFE:
                premium = calculateLifePremium(customer);
                break;
            case HEALTH:
                premium = calculateHealthPremium(customer);
                break;
            case VEHICLE:
                premium = calculateVehiclePremium(customer);
                break;
            default:
                throw new CalculationException("Unknown policy type");
        }

        return premium;
    }


    private double calculateLifePremium(Customer customer) throws DataNotFoundException {
        if (customer.getAge() == null) {
            throw new DataNotFoundException("Age data missing for life insurance.");
        }
        try {
            return 5000 + (customer.getAge() * 20);
        } catch (Exception e) {
            throw new CalculationException("Error in life insurance calculation.");
        }
    }


    private double calculateHealthPremium(Customer customer) throws DataNotFoundException {
        if (customer.getHasHealthIssues() == null) {
            throw new DataNotFoundException("Health data missing for health insurance.");
        }
        try {
            return customer.getHasHealthIssues() ? 8000 : 4000;
        } catch (Exception e) {
            throw new CalculationException("Error in health insurance calculation.");
        }
    }


    private double calculateVehiclePremium(Customer customer) throws DataNotFoundException {
        if (customer.getVehicleType() == null) {
            throw new DataNotFoundException("Vehicle type data missing for vehicle insurance.");
        }
        try {
            switch (customer.getVehicleType().toLowerCase()) {
                case "car":
                    return 6000;
                case "bike":
                    return 3000;
                case "truck":
                    return 10000;
                default:
                    throw new CalculationException("Unsupported vehicle type: " + customer.getVehicleType());
            }
        } catch (Exception e) {
            throw new CalculationException("Error in vehicle insurance calculation.");
        }
    }
}


public class PremiumCalculation {
    public static void main(String[] args) {
        PremiumCalculator calculator = new PremiumCalculator();

        // Example customers
        Customer c1 = new Customer("Alice", 30, null, null);       // Missing health data
        Customer c2 = new Customer("Bob", 45, true, null);         // Valid health policy
        Customer c3 = new Customer("Charlie", null, false, "car"); // Missing age for life
        Customer c4 = new Customer("David", 25, false, "bike");    // Valid vehicle policy

        process(calculator, c1, PolicyType.HEALTH);
        process(calculator, c2, PolicyType.HEALTH);
        process(calculator, c3, PolicyType.LIFE);
        process(calculator, c4, PolicyType.VEHICLE);
    }

    private static void process(PremiumCalculator calculator, Customer customer, PolicyType type) {
        try {
            double premium = calculator.calculatePremium(customer, type);
            System.out.println("Premium for " + customer.getName() + " (" + type + "): " + premium);
        } catch (DataNotFoundException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (CalculationException e) {
            System.out.println("Critical Calculation Error: " + e.getMessage());
        }
    }
}

