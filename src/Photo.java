import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Photo {
    private final String id;
    private final String photoName;
    private final LocalDate date;
    private final String locationName;
    private final Set<String> tags;

    public Photo(String id, String photoName, LocalDate date, String locationName, Set<String> tags) {
        this.id = id;
        this.photoName = photoName;
        this.date = date;
        this.locationName = locationName;
        this.tags = new HashSet<>(tags);
    }

    public String getId() { return id; }
    public String getPhotoName() { return photoName; }
    public LocalDate getDate() { return date; }
    public String getLocationName() { return locationName; }
    public Set<String> getTags() { return new HashSet<>(tags); }
}
