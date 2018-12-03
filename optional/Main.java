package demo;
import java.util.Optional;
import java.util.function.Consumer;

public class Main {

	public static void main(String[] args) {


		Optional<Double> optioanlDouble = Optional.empty();
		
		
		System.out.println(optioanlDouble.isPresent());
		
		Optional<Double> optioanlDouble2 = Optional.of(5.5d);
		
		System.out.println(optioanlDouble2.isPresent());
		
		//optioanlDouble.
		System.out.println( optioanlDouble.orElse(34.0d));
		
		System.out.println( optioanlDouble2.orElse(34.0d));
		
		System.out.println(optioanlDouble);
		System.out.println(optioanlDouble2);
		System.out.println(optioanlDouble2.get());
		Double d = optioanlDouble2.get();
		
		Optional<ClientFacade> facade = (new CouponSystem()).login("12345");
		//System.out.println(facade);
		
		facade.ifPresent( 
				f4 -> 
				{
					((AdminFacade)f4).CreateCompany("Bezeq");
					// more work
				} );
		
		facade.ifPresent( StaticMethod::foo );
		
		facade.ifPresent( new Consumer<ClientFacade>() {

			@Override
			public void accept(ClientFacade f) {

			}
			
		});
		
		

		
		
	}

}
