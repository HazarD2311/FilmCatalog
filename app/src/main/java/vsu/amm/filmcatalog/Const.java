package vsu.amm.filmcatalog;

public class Const {

    public enum ErrorCode {
        OK,
        NO_CONNECTION,
        NO_CONTENT
    }

    public static final String API_KEY = "6ccd72a2a8fc239b13f209408fc31c33";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";
    public static final String LANGUAGE = "ru-RU";
    public static final String DATE_FORMAT = "dd MMMM yyyy";

    public static final String ERROR_MESSAGE = "Проверьте ваше интернет соединение и попробуйте ещё раз";


}
