package RealTimeExample;

import java.util.*;

class FeedbackNotFoundException extends Exception {
    public FeedbackNotFoundException(String message) {
        super(message);
    }
}

class InvalidFeedbackContentException extends RuntimeException {
    public InvalidFeedbackContentException(String message) {
        super(message);
    }
}

class Feedback {
    private int feedbackId;
    private String customerName;
    private String content;

    public Feedback(int feedbackId, String customerName, String content) {
        this.feedbackId = feedbackId;
        this.customerName = customerName;
        this.content = content;
    }

    public int getFeedbackId() { return feedbackId; }
    public String getCustomerName() { return customerName; }
    public String getContent() { return content; }

    @Override
    public String toString() {
        return "Feedback{id=" + feedbackId + ", customer='" + customerName + "', content='" + content + "'}";
    }
}

class CustomerFeedbackSystem {
    private Map<Integer, Feedback> feedbackDB = new HashMap<>();
    private static final Set<String> bannedWords = new HashSet<>(Arrays.asList("bad", "worst", "abuse"));

    public void submitFeedback(int id, String name, String content) {
        validateContent(content);
        Feedback feedback = new Feedback(id, name, content);
        feedbackDB.put(id, feedback);
        System.out.println("Feedback submitted successfully: " + feedback);
    }

    public Feedback getFeedback(int id) throws FeedbackNotFoundException {
        Feedback feedback = feedbackDB.get(id);
        if (feedback == null) {
            throw new FeedbackNotFoundException("Feedback ID " + id + " not found.");
        }
        return feedback;
    }

    public void displayAllFeedback() {
        if (feedbackDB.isEmpty()) {
            System.out.println("No feedback available.");
        } else {
            System.out.println("Customer Feedback:");
            for (Feedback f : feedbackDB.values()) {
                System.out.println(" - " + f);
            }
        }
    }

    private void validateContent(String content) {
        for (String word : bannedWords) {
            if (content.toLowerCase().contains(word)) {
                throw new InvalidFeedbackContentException("Feedback contains inappropriate content: " + word);
            }
        }
    }
}

public class CustomerFeedback {
    public static void main(String[] args) {
        CustomerFeedbackSystem system = new CustomerFeedbackSystem();

        processFeedbackSubmission(system, 1, "Alice", "Great service, very helpful!");
        processFeedbackSubmission(system, 2, "Bob", "Worst experience ever!");
        processFeedbackSubmission(system, 3, "Charlie", "Quick response and friendly staff.");

        system.displayAllFeedback();

        processFeedbackRetrieval(system, 1);
        processFeedbackRetrieval(system, 5);
    }

    private static void processFeedbackSubmission(CustomerFeedbackSystem system, int id, String name, String content) {
        try {
            system.submitFeedback(id, name, content);
        } catch (InvalidFeedbackContentException e) {
            System.out.println("Error submitting feedback: " + e.getMessage());
        }
    }

    private static void processFeedbackRetrieval(CustomerFeedbackSystem system, int id) {
        try {
            Feedback feedback = system.getFeedback(id);
            System.out.println("Retrieved: " + feedback);
        } catch (FeedbackNotFoundException e) {
            System.out.println("Error retrieving feedback: " + e.getMessage());
        }
    }
}

