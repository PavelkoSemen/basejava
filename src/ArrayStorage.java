import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage;
    private int cursor = 0;
    private static final int DEFAULT_QUANTITY_RESUME = 10000;

    public ArrayStorage() {
        this(new Resume[DEFAULT_QUANTITY_RESUME]);
    }

    public ArrayStorage(Resume[] storage) {
        this.storage = storage;
    }


    public void clear() {
        for (int i = 0; i < cursor; i++) {
            storage[cursor] = null;
        }
        cursor = 0;
    }

    public void save(Resume r) {
        if (r == null) {
            throw new IllegalArgumentException("The value cannot be null");
        }
        storage[cursor++] = r;
    }

    public Resume get(String uuid) {
        Resume foundResume = null;
        int index;
        if ((index = getObjectIndex(uuid)) != -1) {
            foundResume = storage[index];
        }

        return foundResume;
    }

    public void delete(String uuid) {
        int index;
        if ((index = getObjectIndex(uuid)) != -1) {
            delete(index);
        }
    }

    public void delete(int index) {
        if (index >= cursor && index < 0)
            throw new IndexOutOfBoundsException("Invalid index");
        System.arraycopy(storage, index + 1, storage, index, cursor - index - 1);

        storage[--cursor] = null;
    }

    private int getObjectIndex(String uuid) {

        for (int index = 0; index < cursor; index++) {
            if (storage[index].toString().equals(uuid)) {
                return index;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, cursor);
    }

    public int size() {
        return cursor;
    }
}
