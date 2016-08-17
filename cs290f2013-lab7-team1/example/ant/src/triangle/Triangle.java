package triangle;

public class Triangle {

	public static enum Type {
		INVALID, SCALENE, EQUILATERAL, ISOSCELES
	};
	
	public static Type classify(int a, int b, int c) {
		int trian;
		if (a <= 0 || b <= 0 || c <= 0)
			return Type.INVALID;
		trian = 0;
		if (a == b)
			trian = trian + 1;
		if (a == c)
			trian = trian + 2;
		if (b == c)
			trian = trian + 3;
		if (trian == 0)
			if (a + b <= c || a + c <= b || b + c <= a)
				return Type.INVALID;
			else
				return Type.SCALENE;
		if (trian > 3)
			return Type.EQUILATERAL;
		if (trian == 1 && a + b > c)
			return Type.ISOSCELES;
		else if (trian == 2 && a + c > b)
			return Type.ISOSCELES;
		else if (trian == 3 && b + c > a)
			return Type.ISOSCELES;
		return Type.INVALID;
	}

	public static void main(String[] args)
	{
		Triangle t = new Triangle();
		Type type = t.classify(1,1,1);
		System.out.println("The type of triangle is " + type);

		type = t.classify(0,0,0);
		System.out.println("The type of triangle is " + type);

		type = t.classify(1,2,3);
		System.out.println("The type of triangle is " + type);

		type = t.classify(3,4,5);
		System.out.println("The type of triangle is " + type);

		type = t.classify(6,5,6);
		System.out.println("The type of triangle is " + type);

	}
}
