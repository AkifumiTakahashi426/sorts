//３つを比べて、その中で一番大きいものを親と入れ替えて、それがさらにその上と比べて大きいなら
//また上にあげて･･･という動作を1つの一連の動作として、それをそれぞれの3つで行う。

public class HeapSort {

	public static void sort(int[] array) {
		int n = array.length;

		for (int i = n / 2-1 ; i >= 0; i--) {//３つ(親、左子、右子)で比べる回数が繰り返し回数
			heap(array, n, i);
		}

		for (int i = n - 1; i >= 0; i--) {
			if (array[0] > array[i]) {
				int tmp = array[0];
				array[0] = array[i];
				array[i] = tmp;

				heap(array, i - 1, 0);
			}

		}
	}

	//取り出したい値（今回は一番大きい値）を一番上まであげて、一番最後に入れる
	public static void heap(int[] array, int n, int root) {
		int largest = root;//３つのうち最大の数が入っている配列番号（最初は親にしておく）
		int left = 2 * root+1;//左の子
		int right = 2 * root+2;//右の子

		if (left < n && array[left] > array[largest]) {//一番大きい数字が入ってる配列番号をlargestに入れる
			largest = left;
		}

		if (right < n && array[right] > array[largest]) {//右子が大きければlargestは右子の配列番号になる
			largest = right;
		}

		if (largest != root) {//入れ替え動作。大きい数字が親でないなら、配列を入れ替え
			int swap = array[root];
			array[root] = array[largest];
			array[largest] = swap;

			heap(array, n, largest);//上に上がってもう一度(取り出したい値が一つ上に行ったので、次は一つ上で検証する）
		}
	}

	public static void main(String[] args) {
		int[] test = { 10, 75, 24, 98, 32, 72, 88 };
		sort(test);
		for (int i = 0; i < test.length; i++) {
			System.out.println((i + 1) + ":" + test[i]);
		}
	}
}
