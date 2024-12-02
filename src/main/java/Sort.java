import aston.lesson02.CustomArrayList;

import java.util.*;

public class Sort {
    public static void main(String[] args) throws Throwable {


        String hello = "hello hello";
        String hello2 = hello.intern();
        List<String> list = new LinkedList<>();
//
        int[] array = new int[]{5, 6, 1, 4, 2, 3};

        CustomArrayList<Integer> customArrayList = new CustomArrayList<>();
        customArrayList.add(4);
        customArrayList.add(2);
        customArrayList.add(1);
        customArrayList.add(6);
        customArrayList.add(5);
        customArrayList.add(3);

        customArrayList.sort();
        System.out.println(customArrayList);

//        System.out.println(bubbleSort(array));

//        System.out.println(mergeSort(array, 0, array.length - 1));

//        System.out.println(quickSort(array, 0, array.length - 1));

//        System.out.println(heapSort(array));
//        System.out.println(hello2);

        Map<String, Integer> nonSorterMap = Map.of(
                "Two", 2,
                "Three", 3,
                "Five", 5,
                "One", 1,
                "Four", 4
        );

        Map<String, Integer> sorterMap = sortedByValue(nonSorterMap);

        sorterMap.entrySet().forEach(System.out::println);

    }

    private static Map<String, Integer> sortedByValue(Map<String, Integer> nonSorterMap) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(nonSorterMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static String bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return Arrays.toString(array);
    }

    public static String mergeSort(int[] array, int start, int end) {
        if (array == null || array.length < 2) {
            return Arrays.toString(array);
        }
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
        return Arrays.toString(array);
    }

    public static void merge(int[] array, int start, int mid, int end) throws RuntimeException {
        int n1 = mid - start + 1;
        int n2 = end - mid;

        int[] left = new int[n1];
        int[] right = new int[n2];
        List<String> list = new ArrayList<>();

        System.arraycopy(array, start, left, 0, n1);
        System.arraycopy(array, mid + 1, right, 0, n2);
        int i = 0, j = 0;
        int k = start;

        while (i < n1 && j < n2) {
            if (left[i] < right[j]) {
                array[k] = left[i];
                i++;
            } else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = left[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    //        int[] array = new int[]{5, 6, 1, 4, 2, 3};
    //    quickSort(array, 0, array.length - 1)
    public static String quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
        return Arrays.toString(array);
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];    // 3
        int i = (low - 1); // -1
        for (int j = low; j < high; j++) {      // j=0
            if (array[j] <= pivot) {         // 5 <= 6
                i++;        // 0
                int temp = array[i];        // temp = 5
                array[i] = array[j];        // i=0 j=1
                array[j] = temp;        // j=1
            }
        }
        int temp = array[i + 1];        // i=0 temp=5
        array[i + 1] = array[high];     // array[0] = 1
        array[high] = temp;             // array[5] = 5
        return i + 1;       // 0
    }

    public static String heapSort(int array[]) {
        int n = array.length;

        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру heapify на уменьшенной куче
            heapify(array, i, 0);
        }
        return Arrays.toString(array);
    }

    public static void heapify(int array[], int n, int i) {
        int largest = i; // Инициализируем наибольший элемент как корень
        int left = 2 * i + 1; // левый = 2*i + 1
        int right = 2 * i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // Если самый большой элемент не корень
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(array, n, largest);
        }
    }
}