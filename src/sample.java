import java.util.ArrayList;
import java.util.Scanner;
public class Sample {
public static void main(String[] args)
{
	Scanner saa = new Scanner(System.in);
	int y = saa.nextInt();
	String qq = "goasdk";
	ArrayList<String> s = new ArrayList<String>();
	s.add("sddsd");
	s.add("dd");
	System.out.println("Hello " + y + "sdsd"+ qq.length());
	for (int i=0;i<6;i++)
	{
		y = Call(i);
		System.out.println(y);
	}
}

public static int Call(int i)
{
	return (i+23);
}

}
