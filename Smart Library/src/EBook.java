// Classi Concrete (Specializzazione) - 1

public class EBook extends LibraryResource implements Downloadable {
    public EBook(String title, int popularityIndex) {
        super(title, popularityIndex);
    }

    @Override
    public String getUsageTerms() {
        return "Digital license: single user access.";
    }

    @Override
    public boolean isAvailableForOffline() {
        return false;
    }
}
