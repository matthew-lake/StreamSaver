package streamSaver;

public class streamModel {

	private int result;

	public CalcModel() {
		result = 0;
	}

	public void add(int n) {
		result += n;
	}

	public int getResult() {
		return result;
	}

	public void clear() {
		result = 0;
	}
}
