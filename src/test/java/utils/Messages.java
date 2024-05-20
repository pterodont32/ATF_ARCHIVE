package utils;

public enum Messages {
    EMAIL_ALREADY_EXISTS("There is already an account with this email address. If you are sure that it is your email address, click here to get your password and access your account."),
    FAIL_LOG_IN("The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later."),
    PASSWORD_CONFIRMATION_ERROR("Please enter the same value again."),

    //not errors
    WELCOME_TEXT("Welcome, victor victor!"),
    PASSWORD_WEAK("Weak"),

   //API MESSAGES
    USER_IS_NOT_PRESENT("doesn't exist!");

    private final String message;
    Messages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}