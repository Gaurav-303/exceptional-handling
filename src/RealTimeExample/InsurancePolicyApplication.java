package RealTimeExample;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
class PoorDrivingRecordException extends Exception {
    public PoorDrivingRecordException(String message) {
        super(message);
    }
}

class HealthIssueException extends Exception {
    public HealthIssueException(String message) {
        super(message);
    }
}
class Applicant {
    private int age;
    private boolean poorDrivingHistory;
    private boolean healthIssues;

    public Applicant(int age, boolean poorDrivingHistory, boolean healthIssues) {
        this.age = age;
        this.poorDrivingHistory = poorDrivingHistory;
        this.healthIssues = healthIssues;
    }

    public int getAge() {
        return age;
    }

    public boolean hasPoorDrivingHistory() {
        return poorDrivingHistory;
    }

    public boolean hasHealthIssues() {
        return healthIssues;
    }
}
class PolicyApplication {

    public void apply(Applicant applicant) throws InvalidAgeException,
            PoorDrivingRecordException,
            HealthIssueException {

        if (applicant.getAge() < 18 || applicant.getAge() > 70) {
            throw new InvalidAgeException("Application rejected: Invalid age (" + applicant.getAge() + ")");
        }

        if (applicant.hasPoorDrivingHistory()) {
            throw new PoorDrivingRecordException("Application rejected: Poor driving history.");
        }

        if (applicant.hasHealthIssues()) {
            throw new HealthIssueException("Application rejected: Health issues found.");
        }


        System.out.println("Policy application approved for applicant (Age: " + applicant.getAge() + ")");
    }
}

public class InsurancePolicyApplication {
    public static void main(String[] args) {
        PolicyApplication policyApp = new PolicyApplication();


        Applicant a1 = new Applicant(16, false, false);
        Applicant a2 = new Applicant(30, true, false);
        Applicant a3 = new Applicant(40, false, true);
        Applicant a4 = new Applicant(35, false, false);


        processApplication(policyApp, a1);
        processApplication(policyApp, a2);
        processApplication(policyApp, a3);
        processApplication(policyApp, a4);
    }

    private static void processApplication(PolicyApplication policyApp, Applicant applicant) {
        try {
            policyApp.apply(applicant);
        } catch (InvalidAgeException | PoorDrivingRecordException | HealthIssueException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
}
