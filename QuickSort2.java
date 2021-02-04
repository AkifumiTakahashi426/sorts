/** ソートのアルゴリズム(その3 クイックソート 降順) */

//クイックソートは、配列の中で基準値を定め、「基準値より大きい数」と「基準値より小さい数」で分け、
//それぞれにまた基準値を定めて分けて…と、配列を細分化していくソートのやり方。

public class QuickSort2 {

	/** 作業領域用データ */
	private static int[] tmp = null;

	/** 処理の開始のメソッド(確認用) */
	public static void main(String[] args) {

		// 確認用のサンプルデータ
		int[] data = { 10, 75, 24, 32, 98, 72, 88, 43, 60, 55 };

		print(data);

		//再帰的なメソッド「ソート」の呼び出し
		sort(data);

		//ソートの結果の確認
		print(data);
	}

	/** 配列のサイズから範囲を指定してソートを開始 */
	public static void sort(int[] data) {

		sort(data, 0, data.length - 1);//(データ配列、最小値=0、最大値=データの長さ-1(データの一番後ろの番号)）
	}

	/** 与えられた範囲の値を２グループに分け、
	  自分自身の仕事を再帰的に呼び出す */
	public static void sort(int[] data, int min, int max) {//配列データ、最小値(一周目は0)、最大値(一周目は9)


		//両端の値が一致したらこれ以上並び換える必要はない
		if (data[max] == data[min]) {
			return;
		}

		//作業領域の確保(最初の呼び出しの時のみ)
		if (tmp == null)//tmpがなければ(一度作ってしまえば常にfalseになる)
			tmp = new int[data.length];//データの長さ分の容量をもった配列tmpを宣言。

		//※クイックソートの基準値を、今回は平均値にする。
		//平均値より大きいグループと小さいグループに分けていく。
		float m = average(data, min, max);//平均値を計算し変数ｍに代入
		System.out.println("平均:"+m);
		int largeCount = 0;//左から順に入れていくための変数
		int smallCount = 0;//右から順に入れていくための変数

		//配列の中の最小値からスタートし、配列の最大値(=最後尾)になるまで繰り返す
		//例えば[6]から[10]までの場合、6.7.8.9.10の5回繰り返すことになる。
		//（配列の中で指定したブロック(細分化したブロック)の要素一つ一つをチェックするイメージ。）
		for (int i = min; i <= max; i++) {

			if (data[i] > m) {//指定した番号の配列内データが基準値(今回は平均)より大きければ
				tmp[min + largeCount] = data[i];//配列tmpの左側から順に入れていく。2つ目ならば0+1の[1](2番目)に入る。
				largeCount++;

			} else {//指定した番号の配列内データが基準値(今回は平均)より小さければ
				tmp[max - smallCount] = data[i];//配列tmpの右側から入れていく。二つ目ならば9-1の[8](9番目)に入る。
				smallCount++;//カウント1つ追加

			}
		}
		//整列した配列tmpでdata配列を上書きする。
		for (int i = min; i <= max; i++) {//作業中のブロックの最初の位置から最後の位置まで。
			data[i] = tmp[i];
		}

		//それぞれのグループに対して同じことを繰り返す
		//-1とか+1しているのは、Countがブロックの長さを表しているが、
		//実際引数として渡すのは配列番号だから。(例えば配列数が4つなら、[0]～[3]で後ろは3になる)。


		print(data);

		sort(data, min, min + largeCount - 1);
		sort(data, max - smallCount + 1, max);

	}

	//////////////ここまでメソッドsort////////////////



	/** 与えられた範囲の値の平均値を求めるメソッド */
	private static float average(int[] data, int min, int max) {

		float sum = 0.0f;
		for (int i = min; i <= max; i++) {
			sum += data[i];
		}
		return sum / (max - min + 1);
	}

	/** 現在の作業状況を出力するメソッド */
	public static void print(int[] data) {

		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + ",");
		}
		System.out.println(""); //改行
	}

}
