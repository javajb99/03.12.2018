package demo;

public class StaticMethod {

	public static void foo(ClientFacade f4)
	{
		((AdminFacade)f4).CreateCompany("Bezeq");
	}
}
