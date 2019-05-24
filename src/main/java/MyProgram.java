

public class MyProgram {


	public static void main(String [ ] args) {

		hello("Pizza arrived!");
		hello("Maiki");
		hello("Teacher");

		Square s = new Square(5);
		System.out.println("Площадб квадрата со сторонами " + s.l +  " = " + s.area());


		Rectangle r = new Rectangle(5, 9);
		System.out.println("Площадь прямоугольника со сторонами " + r.a +  " + " + r.b + " = " + r.area());

		Point point1 = new Point();
		Point point2 = new Point();
		point1.x = 2;
		point1.y = 4;
		point2.x = 5;
		point2.y = 7;
		System.out.println("Расстояние между двумя точками  = " + Point.distance(point1, point2) );
	}


	public static void hello(String thing) {
		System.out.println("Kavabanga!" + thing + "!");


		}
// выполнено задание 2




}
