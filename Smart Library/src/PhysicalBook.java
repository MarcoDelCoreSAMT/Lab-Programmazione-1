// Classi Concrete (Specializzazione) - 2

public class PhysicalBook extends LibraryResource {
    private String shelfLocation;

    public PhysicalBook(String title, int popularityIndex, String shelfLocation) {
        super(title, popularityIndex);
        this.shelfLocation = shelfLocation;
    }

    @Override
    public String getUsageTerms(){
        return "Physical copy at location: " + shelfLocation + ".";
    }
}
