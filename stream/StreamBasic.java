package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamBasic {

	public static void go()
	{
		
		// (1) Create List<People> people with 10,000 people
		List<People> peoples = new ArrayList<>();
		ArrayList<String> phones = new ArrayList<>();
		phones.add("1");
		phones.add("2");
		phones.add("3");
		for (int i = 0; i < 10000; i++) {
			
		          int cityInx = (int) (Math.random() * 8);
		          int genderInx = (int) (Math.random() * 2);
		          int letterInx = (int) (Math.random() * 24);
		          peoples.add(new People
		        		  (i, "!" + (char) ('a' + letterInx) + "!",
		        				  (int) (Math.random() * 121), People.Gender.values()[genderInx],
		        				  People.City.values()[cityInx], (char) i + ".St", phones));
		}

		// (2) Obtain stream from List<People>
		Stream<People> streamPeople = peoples.stream();
		Stream<People> parallelStreamPeople = peoples.parallelStream();
		
		List<Integer> listInt = new ArrayList<Integer>();
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		listInt.add(3);
		//Stream<Integer> listStream = listInt.stream();
		System.out.println(listInt.stream().count());
		System.out.println(listInt.stream().count());
		System.out.println(listInt);
		
		System.out.println("distinct");
		listInt.stream().distinct().forEach(
				(Integer int1) -> { System.out.println(int1);});
		System.out.println("filter > 2");
		listInt.stream().filter(
				
				IntegerCommon :: get2Int
				
				).forEach(
						(Integer int1) -> { System.out.println(int1);});
		listInt.stream().max(
					Integer :: compareTo
				);
		List<Integer> emptyList = new ArrayList<Integer>();
		System.out.println(emptyList.stream().findAny());
		
		// 1 count - use once
		// 2 print
		// 3 distinct
		// 4 filter
		// 4.5 nonematch
		// 4.6 max
		// 5 findFirst - w/o get, perhaps ispresent...
		
		
		
		
		// method reference
		// 1 flapMapToInt
		// 2 limit
		// 3 sorted
		// 4 iterator
		// 5 average - get as double
		// 6 generate
		System.out.println("================");
		Stream<int[]> intStream = Stream.of(new int[] {1,2,3});
		IntStream myIntStream = intStream.flatMapToInt(
				x -> Arrays.stream(x));
		myIntStream.forEach((x) -> {System.out.println(x);});
        

	}
	
}
