package Person;

        import java.text.Collator;
        import java.util.Locale;
        import java.util.Random;

public enum Nationality {
    Polish(new Locale("pl", "PL")),
    Ukrainian(new Locale("uk", "UA")),
    Belarusian(new Locale("be", "BY")),
    Slovak(new Locale("sk", "SK")),
    Lithuanian(new Locale("lt", "LT")),
    Latvian(new Locale("lv", "LV")),
    British(new Locale("en", "GB")),
    Indian(new Locale("hi", "IN")),
    Chinese(new Locale("zh", "CN")),
    Vietnamese(new Locale("vi", "VN"));

    private Locale locale;
    Nationality(Locale l) {
        locale = l;
    }

    public static Nationality getNationality(){
        Random random = new Random();
        Nationality[] nationalities = Nationality.values();
        int i = random.nextInt(nationalities.length);
        return nationalities[i];
    }

    public Locale getLocale() {
        return locale;
    }

    @Override
    public String toString() {
        return locale.getDisplayCountry();
    }
}
