// La Gerarchia (Ereditarietà)

public abstract class LibraryResource {
    private String title;
    private int popularityIndex;

    public LibraryResource(String title, int popularityIndex) {
        this.title = title;
        this.popularityIndex = Math.max(0, Math.min(100, popularityIndex));
    }

    public String getTitle() {
        return title;
    }

    public int getPopularityIndex() {
        return popularityIndex;
    }

    public int boostPopularity(int points){
        if (points < 0) {
            return popularityIndex;
        }
        popularityIndex = Math.min(100, popularityIndex + points);
        return popularityIndex;
    }

    public abstract String getUsageTerms();
}