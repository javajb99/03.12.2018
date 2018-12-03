package demo;

import java.util.Optional;

public class CouponSystem {

	
	
	public Optional<ClientFacade> login(String pwd)
	{
		if (pwd.equals("1234"))
		{
			return Optional.of(new AdminFacade());
		}
		return Optional.ofNullable(null);
	}
	
}
