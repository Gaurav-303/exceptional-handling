package custom_exception;

    class NegativeNumberException extends RuntimeException {
        public NegativeNumberException(String message) {
            super(message);
        }
    }

