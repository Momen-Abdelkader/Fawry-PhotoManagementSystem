import java.time.LocalDate;
import java.util.*;

public class PhotoManager {
    private final Map<String, Photo> idToPhoto;
    private final Map<LocalDate, Set<Photo>> dateToPhotos;
    private final Map<String, Set<Photo>>  locationToPhotos;
    private final Map<String, Set<Photo>> tagToPhotos;

    public PhotoManager() {
        this.idToPhoto = new HashMap<>();
        this.dateToPhotos = new HashMap<>();
        this.locationToPhotos = new HashMap<>();
        this.tagToPhotos = new HashMap<>();
    }

    void uploadPhoto(Photo photo) {
        // update main table
        idToPhoto.put(photo.getId(), photo);

        // update date to photos
        if (!dateToPhotos.containsKey(photo.getDate())) {
            dateToPhotos.put(photo.getDate(), new HashSet<>());
        }
        dateToPhotos.get(photo.getDate()).add(photo);

        // update location to photos
        if (!locationToPhotos.containsKey(photo.getLocationName())) {
            locationToPhotos.put(photo.getLocationName(), new HashSet<>());
        }
        locationToPhotos.get(photo.getLocationName()).add(photo);

        // Update tags
        for (String tag : photo.getTags()) {
            String lowerCaseTag = tag.toLowerCase();
            if (!tagToPhotos.containsKey(lowerCaseTag)) {
                tagToPhotos.put(lowerCaseTag, new HashSet<>());
            }
            tagToPhotos.get(lowerCaseTag).add(photo);
        }
    }

    List<Photo> searchByDate(LocalDate date) {
       Set<Photo> photos = dateToPhotos.get(date);
       return photos != null ? new ArrayList<>(photos) : new ArrayList<>();
    }

    List<Photo> searchByLocation(String locationName) {
        Set<Photo> photos = locationToPhotos.get(locationName);
        return photos != null ? new ArrayList<>(photos) : new ArrayList<>();
    }

    List<Photo> searchByTag(String tag) {
        Set<Photo> photos = tagToPhotos.get(tag);
        return photos != null ? new ArrayList<>(photos) : new ArrayList<>();
    }

    List<Photo> searchByMultipleTags(Set<String> tags) {
        if (tags.isEmpty()) {
            return new ArrayList<>();
        }

        Set<String> lowerCaseTags = new HashSet<>();
        for (String tag : tags) {
            lowerCaseTags.add(tag.toLowerCase());
        }

        Iterator<String> it = lowerCaseTags.iterator();
        Set<Photo> photos = new HashSet<>(tagToPhotos.getOrDefault(it.next(), new HashSet<>()));

        while (it.hasNext()) {
            String currentTag = it.next();
            Set<Photo> tagPhotos = tagToPhotos.getOrDefault(currentTag, new HashSet<>());
            photos.retainAll(tagPhotos);
        }

        return new ArrayList<>(photos);
    }

}
