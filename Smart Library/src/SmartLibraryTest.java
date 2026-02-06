import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SmartLibraryTest {

    // ===========================================================
    // === Test su classe LibraryResource (Logica di Business) ===
    // ===========================================================

    @Test
    public void shouldIncreasePopularityWhenPointsArePositive(){
        // Arrange
        EBook eBook = new EBook("DottovGvimaldelli", 10);
        // Act
        int result = eBook.boostPopularity(10);
        // Assert
        assertEquals(20, result);
    }

    @Test
    public void shouldCapPopularityAtOneHundred(){
        // Arrange
        EBook eBook = new EBook("Ramonelli", 95);
        // Act
        int result = eBook.boostPopularity(20);
        // Assert
        assertEquals(100, result);
    }

    @Test
    public void shouldNotChangePopularityWhenPointsAreNegative(){
        // Arrange
        EBook eBook = new EBook("Sotirios", 40);
        // Act
        int result = eBook.boostPopularity(-2);
        // Assert
        assertEquals(40, result);
    }

    // ============================================
    // === Test su classe Downloadable (Soglie) ===
    // ============================================

    @Test
    public void shouldReturnFalseWhenPopularityIsBelowThreshold(){
        // Arrange
        EBook eBook = new EBook("Catenelli", 29);
        // Act
        boolean avaiable = eBook.isAvailableForOffline();
        // Assert
        assertFalse(avaiable);
    }

    @Test
    public void shouldReturnTrueWhenPopularityIsAtThreshold(){
        // Arrange
        EBook eBook = new EBook("Bohdan", 30);
        // Act
        boolean avaiable = eBook.isAvailableForOffline();
        // Assert
        assertFalse(avaiable);
    }

    // ===============================================================
    // === Test su classe SmartLibrary (Integrazione e Collezioni) ===
    // ===============================================================

    @Test
    public void shouldIncreaseSizeWhenResourceIsAdded(){
        // Arrange
        SmartLibrary library = new SmartLibrary();
        EBook ebook = new EBook("Java OOP", 50);

        // Act
        library.addResource(ebook);

        // Assert
        assertEquals(1, library.getResourceCount());

    }

    @Test
    public void shouldOnlyReturnDownloadableResources(){
        SmartLibrary library = new SmartLibrary();
        EBook ebook = new EBook("EBook Java", 60);
        PhysicalBook book = new PhysicalBook("Paper Java", 80, "A3");

        library.addResource(ebook);
        library.addResource(book);

        // Act
        var downloadable = library.getDownloadableResources();

        // Assert
        assertEquals(1, downloadable.size());
        assertTrue(downloadable.get(0) instanceof Downloadable);
    }
}