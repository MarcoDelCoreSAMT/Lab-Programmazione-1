// Esercizio: 10. Air-Manager
// Autore: Marco Del Core

import java.util.ArrayList;
import java.util.List;

// Il Gestore (Collezioni e Logica)

public class SmartLibrary {
    private List<LibraryResource> resources = new ArrayList<>();

    public void addResource(LibraryResource res){
        resources.add(res);
    }

    public boolean removeResource(LibraryResource res){
        return resources.remove(res);
    }

    public int getResourceCount(){
        return resources.size();
    }

    public List<LibraryResource> getDownloadableResources(){
        List<LibraryResource> ris = new ArrayList<>();

        for(LibraryResource res: resources){
            if (res instanceof Downloadable){
                ris.add(res);
            }
        }
        return ris;
    }


}
