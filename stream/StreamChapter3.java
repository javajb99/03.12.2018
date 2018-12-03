package stream;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import stream.People.Address;
import stream.People.City;
import stream.People.Gender;

public class StreamChapter3 {

	public static void go()
	{
		List<People> peoplesList = new ArrayList<>();
		ArrayList<String> phones = new ArrayList<>();
		phones.add("1");
		phones.add("2");
		phones.add("3");		
		for (int i = 0; i < 10000; i++) {
		          int cityInx = (int) (Math.random() * 8);
		          int genderInx = (int) (Math.random() * 2);
		          int letterInx = (int) (Math.random() * 24);
		          peoplesList.add(new People(i, "!" + (char) ('a' + letterInx) + "!",
		          (int) (Math.random() * 121), Gender.values()[genderInx],
		         City.values()[cityInx], (char) i + ".St", phones));
		}
		
		// Collectos toSet toList toMap
		Set<People> babiesSet = peoplesList.stream().
				filter( p -> p.getAge() < 2 ).collect( Collectors.toSet() );
		System.out.println(babiesSet);
		List<People> babiesList = peoplesList.stream().
				filter( p -> p.getAge() < 2 ).collect( Collectors.toList() );
		Map<Integer, Address> babyAddressesMap = peoplesList.stream().
				filter( p -> p.getAge() < 2 )
                .collect( Collectors.toMap( p -> p.getID(), p -> p.getAddress() ) );
		
		/*
		 * Single Result’ collectors 
			Actually reduces the stream 
			Executes Function on each element and than fuses the outcome into single result
			Counting collector is the simplest – simply counts elements
			
			Creating ‘Single Result’ Collectors – occurs when resulting in Collector sum value
			All these methods return Collector.sum() which is the calculated result wrapped in:
			averagingDouble (ToDoubleFunction< ? super T >) – results in Double value 
			averagingInt (ToIntFunction< ? super T >)                – results in Integer value 
			averagingLong (ToLongFunction< ? super T >)        – results in Long value 
			summingInt(), summingLong(), summingDouble()…
			In order to simply count elements:
			counting() – results in long value which is the element count
		 */
		
		System.out.println(peoplesList.stream().filter( p -> p.getAge() < 18).
                collect(Collectors.averagingDouble( p -> p.getAge() )).doubleValue());
		
		/*
			Creating ‘Single Result’ Collectors – occurs when resulting in Collector sum value
			When summarizing we get much more detailed information 
			Result is not Collator.sum() simple value – but SummaryStatistics instread
			summarizingDouble (…) – returns DoubleSummaryStatistics 
			summarizingInt (…) - returns DoubleSummaryStatistics
			
			accept(T) – records new value to the statistics
			getAverage()
			getCount()
			getMax()
			getMin()
			getSum()

			summarizingLong (…) - returns DoubleSummaryStatistics 
		 */
		
		DoubleSummaryStatistics stats = peoplesList.stream()
	            .mapToDouble((x) -> x.getID() ).summaryStatistics();
		System.out.println(stats);
		System.out.println(stats.getMax());
		
		// groups all members in 120 age with the same city into groups
		// In this example we filter all persons which are 120 years old and count how many are living in each city:
		System.out.println(peoplesList.get(0));
		Map<City, List<People>> groups = peoplesList.stream().
				filter( p -> p.getAge() == 120 )
                .collect(Collectors.groupingBy( p -> p.getAddress().getCity() , Collectors.toList()));
		System.out.println(groups);
		// now counting the groups
		Map<City, Long> groupsCount = peoplesList.stream().filter(p->p.getAge()==120)
                .collect(Collectors.groupingBy(p->p.getAddress().getCity(),Collectors.counting()));
		System.out.println(groupsCount);
		// now couting wihtout the 120
		Map<City, Long> groupsCountWithotu120 = peoplesList.stream().
				collect(Collectors.groupingBy(p->p.getAddress().getCity(), Collectors.counting()));
		System.out.println(groupsCountWithotu120);		

		// Here we show average ages by gender
		// sending Function as ClassName::MethodName
		Map<Gender, Double> groupsAge = peoplesList.parallelStream().collect(
				Collectors.groupingBy(People::getGender,
				Collectors.averagingInt(People::getAge)));
		System.out.println(groupsAge);
		
		// Here we divide persons from city ‘A’ into 2 groups: 
		// false - younger than 60
		// true – all the rest:
		Map<Boolean,List<People>> part=peoplesList.stream().
				filter(p -> p.getAddress().getCity().equals(City.A))
                .collect(Collectors.partitioningBy(p->p.getAge()>60));

		// Example of creating a long String with all person names :
		System.out.println(peoplesList.stream().map(People::getName).collect(Collectors.joining()));
		
		// Mapping all person names and reducing into collection made of 2nd letter in each:
		// a  is current reduced value
		// b  is current element in the stream
		// b.name 2nd letter is added to a
		//   if not present already 
		System.out.println(peoplesList.stream().map(p -> p.getName())
				.reduce("", (a, b) -> {
				       	              if(a.indexOf(b.charAt(1))==-1)
						a += b.charAt(1);
						return a;
			}));



		

		
	}
	
}
