import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public String toString() {
        return String.format("Photo {id = '%s', filename = '%s', date = %s, location = '%s', tags = %s}",
                id, photoName, date, locationName, tags);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Photo photo = (Photo) other;
        return Objects.equals(id, photo.getId())
                && Objects.equals(photoName, photo.photoName)
                && Objects.equals(date, photo.date)
                && Objects.equals(locationName, photo.locationName)
                && Objects.equals(tags, photo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoName, date, locationName, tags);
    }
}
