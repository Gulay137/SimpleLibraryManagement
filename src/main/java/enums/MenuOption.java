package enums;

public enum MenuOption {
    MANAGE_AUTHORS("Manage Authors"),
    MANAGE_BOOKS("Manage Books"),
    MANAGE_LIBRARIES("Manage Libraries"),
    EXIT("Exit");

    private final String description;

    MenuOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static void printMenu() {
        System.out.println("Please choose an option:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.ordinal() + 1 + ". " + option.getDescription());
        }
    }
}
