package ru.sevstal.sandbox;

public class MyProgram {


	public static  void main(String[] args) {

		hello("Pizza arrived!");
		hello("Maiki");
		hello("Teacher");

		Square s = new Square(5);
		System.out.println("������� �������� �� ��������� " + s.l +  " = " + s.area());


		Rectangle r = new Rectangle(5, 9);
		System.out.println("������� �������������� �� ��������� " + r.a +  " + " + r.b + " = " + r.area());

		Point p1 = new Point(8, 4);
		Point p2 = new Point(16, 4);

		System.out.println("���������� ����� ����� �������  = " + p1.distance(p2));
	}



	public static void hello(String thing) {
		System.out.println("Kavabanga!" + thing + "!");


		}
// ��������� ������� 2




}
