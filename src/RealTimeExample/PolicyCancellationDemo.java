package RealTimeExample;

import java.util.*;

class CancellationNotAllowedException extends Exception {
    public CancellationNotAllowedException(String message) {
        super(message);
    }
}

class PolicyNotFoundException extends Exception {
    public PolicyNotFoundException(String message) {
        super(message);
    }
}

class Policy {
    private int policyId;
    private String holderName;
    private boolean active;
    private boolean cancellable;

    public Policy(int policyId, String holderName, boolean active, boolean cancellable) {
        this.policyId = policyId;
        this.holderName = holderName;
        this.active = active;
        this.cancellable = cancellable;
    }

    public int getPolicyId() { return policyId; }
    public String getHolderName() { return holderName; }
    public boolean isActive() { return active; }
    public boolean isCancellable() { return cancellable; }

    public void cancel() { this.active = false; }

    @Override
    public String toString() {
        return "Policy{id=" + policyId + ", holder='" + holderName + "', active=" + active + ", cancellable=" + cancellable + "}";
    }
}

class PolicyCancellation {
    private Map<Integer, Policy> policyDB = new HashMap<>();

    public void addPolicy(Policy policy) {
        policyDB.put(policy.getPolicyId(), policy);
    }

    public void cancelPolicy(int policyId) throws PolicyNotFoundException, CancellationNotAllowedException {
        Policy policy = policyDB.get(policyId);

        if (policy == null) {
            throw new PolicyNotFoundException("Policy ID " + policyId + " not found.");
        }

        if (!policy.isActive()) {
            throw new CancellationNotAllowedException("Policy ID " + policyId + " is already inactive.");
        }

        if (!policy.isCancellable()) {
            throw new CancellationNotAllowedException("Policy ID " + policyId + " cannot be cancelled as per terms.");
        }

        policy.cancel();
        System.out.println("Policy cancelled successfully: " + policy);
    }

    public void displayAllPolicies() {
        if (policyDB.isEmpty()) {
            System.out.println("No policies available.");
        } else {
            System.out.println("Policies:");
            for (Policy p : policyDB.values()) {
                System.out.println(" - " + p);
            }
        }
    }
}

public class PolicyCancellationDemo {
    public static void main(String[] args) {
        PolicyCancellation system = new PolicyCancellation();

        system.addPolicy(new Policy(101, "Alice", true, true));
        system.addPolicy(new Policy(102, "Bob", true, false));
        system.addPolicy(new Policy(103, "Charlie", false, true));

        system.displayAllPolicies();

        processCancellation(system, 101);
        processCancellation(system, 102);
        processCancellation(system, 103);
        processCancellation(system, 200);

        system.displayAllPolicies();
    }

    private static void processCancellation(PolicyCancellation system, int policyId) {
        try {
            system.cancelPolicy(policyId);
        } catch (PolicyNotFoundException | CancellationNotAllowedException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
