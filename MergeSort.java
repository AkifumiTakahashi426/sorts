public class MergeSort {

	public static void main(String[] args) {
		int[] array = { 11, 3, 28, 14, 23, 7, 4, 1, 5, 20 };

		MergeSort.mergeSort(array);
	}

	public static void mergeSort(int[] a) {
		if (a.length > 1) {//配列の長さが2以上なら（分割しきってなかったら）
			// 配列の分割
			int a1_len = a.length / 2;//配列の長さを2分割する。
			int a2_len = a.length - a1_len;//残り半分（奇数の場合はこっちのほうが大きくなる）
			int[] a1 = new int[a1_len];//前半分用のarray
			int[] a2 = new int[a2_len];//後ろ半分用のarray
			for (int i = 0; i < a1_len; i++) {//前半格納
				a1[i] = a[i];
			}
			for (int i = 0; i < a2_len; i++) {//後半格納
				a2[i] = a[a1_len + i];
			}

			// 配列のソート（マージソートを再帰呼び出し）
			mergeSort(a1);//分割が終わるまでは分割をし続ける。
			mergeSort(a2);

			// ↓配列のマージ。分割が終わればマージしていく。ここに来た時点でmargeSort何個か呼ばれてるので、
			//margeはmargeSortがもどっていく(？)度に呼ばれる。
			merge(a1, a2, a);//残った2つの配列（a1a2。最初はそれぞれ1つだけ)と、その数分用意されてる配列
		}
	}

	private static void merge(int[] a1, int[] a2, int[] a) {
		// マージする2配列を表示
		System.out.print("a1={");
		for (int k = 0; k < a1.length; k++) {
			System.out.print(a1[k] + ", ");
		}
		System.out.print("}, a2={");
		for (int k = 0; k < a2.length; k++) {
			System.out.print(a2[k] + ", ");
		}
		System.out.print("}");

		int a_i = 0;
		int a1_i = 0;
		int a2_i = 0;
		while (true) {
			// どちらが小さいかを判断し、小さい方をマージ後の配列に追加
			if (a1[a1_i] < a2[a2_i]) {//a1の最初の数字と、a2の最初の数字を比較
				a[a_i++] = a1[a1_i++];//a2のほうが大きかったら
			} else {
				a[a_i++] = a2[a2_i++];//a2のほうが多い（もしくは等しい）なら
			}
			// どちらかの配列が最後まで行ったらループ終了（最後どちらかの配列の最後の要素を残して終了）
			if (a1_i == a1.length || a2_i == a2.length) {
				break;
			}
		}
		// 残っている方の配列をマージ後配列に追加（実際にはwhile文はどちらかしか実行されない）
		//上の処理で残ったやつを配列に入れて終了
		while (a1_i < a1.length) {//a1に要素が残ってたら
			a[a_i++] = a1[a1_i++];
		}
		while (a2_i < a2.length) {//a2に要素が残ってたら
			a[a_i++] = a2[a2_i++];
		}

		// マージ後の配列を表示
		System.out.print(" => a={");
		for (int k = 0; k < a.length; k++) {
			System.out.print(a[k] + ", ");
		}
		System.out.println("}");
	}
}