package TutorSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class SubjectSorter {
    private static final List<String> AL_SUBJECTS = Arrays.asList(
            "Maths",
            "Biology",
            "Physics",
            "Chemistry"
    );

    private static final List<String> OL_SUBJECTS = Arrays.asList(
            "Maths",
            "Science",
            "Sinhala",
            "Buddhist",
            "ICT",
            "History",
            "Music",
            "Civics"
    );

    public static Map<String, List<String>> getSubjectCategories() {
        Map<String, List<String>> categories = new HashMap<>();
        categories.put("AL", AL_SUBJECTS);
        categories.put("OL", OL_SUBJECTS);
        return categories;
    }

    public static List<String> getSortedSubjects(List<Booking> bookings) {
        // Create a set to store unique subjects
        Set<String> subjects = new HashSet<>();

        // Add all AL and OL subjects
        subjects.addAll(AL_SUBJECTS);
        subjects.addAll(OL_SUBJECTS);

        // Add subjects from existing bookings
        for (Booking booking : bookings) {
            subjects.add(booking.getSubject());
        }

        // Convert to array for merge sort
        String[] subjectArray = subjects.toArray(new String[0]);

        // Perform merge sort
        mergeSort(subjectArray, 0, subjectArray.length - 1);

        // Convert back to list
        return List.of(subjectArray);
    }

    private static void mergeSort(String[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(String[] arr, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        String[] L = new String[n1];
        String[] R = new String[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // Merge the temporary arrays
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
