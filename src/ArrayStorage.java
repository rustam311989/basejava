/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null)
                storage[i] = null;
            else
                return;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        int numberOfDelResume = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                numberOfDelResume = i;
                break;
            }
        }
        for (int i = numberOfDelResume; i < size; i++) {
            storage[i] = storage[i + 1];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = size();
        Resume[] all = new Resume[size];
        for (int i = 0; i < size; i++) {
            all[i] = storage[i];
        }
        return all;
    }

    int size() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                return i;
        }
        return storage.length;
    }
}
