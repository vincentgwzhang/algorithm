package interview.murtiplythread;

public class Person {
	private Integer age;
	
	
	public static void main(String[] args) {
		int[] cc = new int[10];
		aaa(cc);
		System.out.println(cc[2]);
	}
	
	public static void aaa(int [] cc) {
		cc[2] = 3;
	}
}
