package selenium.data;

import selenium.management.Utils;


public class UserDataBuilder {

    public static UserData userData(){
        UserData userData = new UserData("");
        userData.setEmail(Utils.emailGenerator());
        return userData;
    }
}
