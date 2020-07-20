/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int indexDelResume = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                indexDelResume = i;
                break;
            }
        }
        if(indexDelResume != 0) {
            for (int i = indexDelResume; i < size; i++) {
                storage[i] = storage[i + 1];
            }
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        for (int i = 0; i < size; i++) {
            allResume[i] = storage[i];
        }
        return allResume;
    }

    int size() {
        return size;
    }
}
