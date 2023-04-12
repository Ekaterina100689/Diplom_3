package defaults;

import model.User;

public final class Defaults {
    public static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    public static final String USER_AUTH_URI_SUBPATH = "/api/auth";
    public static final String MY_USER_EMAIL = "kate777by@yandex.ru" ;
    public static final String MY_USER_PASSWORD = "kate777password" ;
    public static final User MY_USER = new User("Kate", MY_USER_EMAIL, MY_USER_PASSWORD);
}
