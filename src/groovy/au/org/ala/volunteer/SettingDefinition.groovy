package au.org.ala.volunteer

public class SettingDefinition<T> {

    public static def ForumMessageEditWindow = new SettingDefinition("forum.messageEditWindowSeconds", 15 * 60, "(Seconds) The amount of time someone has to edit their forum messages from the time they posted")
    public static def ForumNotificationsEnabled = new SettingDefinition("forum.notifications.enabled", true, "Whether or not notifications are sent out")
    public static def BatchForumNotificationMessages = new SettingDefinition("forum.notifications.batched", false, "Whether or not notifications are batch sent periodically or sent out directly on posting")
    public static def EnableMyDashboard = new SettingDefinition("dashboard.enabled", false, "Enable the user dashboard tab")
    public static def PicklistCollectionCodes = new SettingDefinition("picklist.collection.codes", new ArrayList<String>(), "List of collection codes used to partition picklist items")
    public static def InstitutionsEnabled = new SettingDefinition("institutions.enabled", true, "Whether or not the institutions tab is displayed")
    public static def IneligibleLeaderBoardUsers = new SettingDefinition<List<String>>("leaderboard.ineligibleUsers", new ArrayList<String>(), "The list of users not eligible for the leaderboard")

//    public static def NotificationEmailServerName = new SettingDefinition("notifications.emailServer.name", "localhost", "The name or address of the SMTP server to user to send out notifications")
//    public static def NotificationEmailServerPort = new SettingDefinition("notifications.emailServer.port", 25, "The port that the SMTP server is listening on")
//    public static def NotificationEmailServerUsername = new SettingDefinition("notifications.emailServer.username", "", "SMTP Server username")
//    public static def NotificationEmailServerPassword = new SettingDefinition("notifications.emailServer.password", "", "SMTP Server password")

    public String key
    public T defaultValue
    public String description

    public SettingDefinition(String key, T defaultValue, String description) {
        this.key = key
        this.defaultValue = defaultValue
        this.description = description
    }
}
