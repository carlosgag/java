package com.toptal;

import java.util.HashMap;
import java.util.Map;

public class TopTal {
	int size = 10;
	boolean[][] board = new boolean[size][size];
	
	public TopTal(){
		board[0][0] = true;
	}

	public static void main(String[] args) {
		int[] A = { 5, 5, 1, 7, 2, 3, 5 };
		TopTal e = new TopTal();
		// e.print(A);
		// int X = 5;
		// int K = e.solution(X, A);
		// System.out.println(K);
		System.out.println(e.solution2(3, 4));
	}

	public int solution(int X, int[] A) {
		int N = A.length;
		int i = 0;
		int j = N - 1;
		int K = 0;
		int equalX = 0;
		int notEqualX = 0;
		if (N > 1) {
			while (i != j && i < j) {
				if (A[i] == X) {
					equalX++;
				}
				if (A[j] != X) {
					notEqualX++;
				}
				i++;
				j--;
				if (i == j) {
					// N is odd and we are at the end
					if (equalX == notEqualX) {
						K = i + 1;
					}
				} else if (i > j) {
					// N is even and we are at the end
					if (equalX == notEqualX) {
						K = i;
					}
				}
			}
		}
		return K;
	}

	public int solution2(int A, int B) {
		return knight(0, 0, A, B);
	}

	public int knight(int originX, int originY, int A, int B) {
		if (A == originX && B == originY) {
			System.out.println("level 0");
			return 0;
		} else {
			Map<Integer, Integer> n = neightbors(originX, originY);
			int best = Integer.MAX_VALUE;
			System.out.println("neightbors: ");
			for (Map.Entry<Integer, Integer> entry : n.entrySet()) {
				int i = entry.getKey();
				int j = entry.getValue();
				System.out.println(i + "," + j);
				board[i][j] = true;
				if (i == A && j == B) {
					System.out.println("found !! " + i + " - " + j);
					return 1;
				} else {
					int remaining = 1 + knight(i, j, A, B);
					if (remaining < best) {
						best = remaining;
					}
				}
			}
			for (boolean[] cs : board) {
				for (boolean c : cs) {
					System.out.print((c ? 1 : 0) + " ");
				}
				System.out.println();
			}
			return best;
		}
	}

	public Map<Integer, Integer> neightbors(int i, int j) {
		System.out.println("calculating neightbors of " + i + " - " + j);
		Map<Integer, Integer> n = new HashMap<>();
		if (i + 2 < size && j + 1 < size && board[i + 2][j + 1] == false) {
			n.put(i + 2, j + 1);
		}
		if (i + 2 < size && j - 1 >= 0 && board[i + 2][j - 1] == false) {
			n.put(i + 2, j - 1);
		}
		if (i - 2 >= 0 && j + 1 < size && board[i - 2][j + 1] == false) {
			n.put(i - 2, j + 1);
		}
		if (i - 2 >= 0 && j - 1 >= 0 && board[i - 2][j - 1] == false) {
			n.put(i - 2, j - 1);
		}
		//-----
		if (j + 2 < size && i + 1 < size && board[j + 2][i + 1] == false) {
			n.put(j + 2, i + 1);
		}
		if (j + 2 < size && i - 1 >= 0 && board[j + 2][i - 1] == false) {
			n.put(j + 2, i - 1);
		}
		if (j - 2 >= 0 && i + 1 < size && board[j - 2][i + 1] == false) {
			n.put(j - 2, i + 1);
		}
		if (j - 2 >= 0 && i - 1 >= 0 && board[j - 2][i - 1] == false) {
			n.put(j - 2, i - 1);
		}
		return n;
	}

	public void print(int[] A) {
		for (int i : A) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
