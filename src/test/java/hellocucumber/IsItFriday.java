package hellocucumber;



public class IsItFriday {
    public static String isItFriday(String today) {
        String IsFriday = "Nope";
        if (today.equals("Friday")) {
            IsFriday = "Yes";
        }
        return IsFriday;
    }
}
