package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import stream.People.City;
import stream.People.Gender;

public class StreamChapter2 {

	public static boolean isMature(People t) {
        if(t.getAge()>=18)return true;
       return false;
}

	
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

		// (2) Obtain stream from List<People>
		Stream<People> streamPeople = peoplesList.stream();
		Stream<People> parallelStreamPeople = peoplesList.parallelStream();
		
		// (3) allMatch = everything match. anyMatch = at least one match
		System.out.println(peoplesList.stream().allMatch(p->p.getName().startsWith("!")));
		System.out.println(peoplesList.stream().allMatch(p->p.getName().startsWith("!h")));
		System.out.println(peoplesList.stream().anyMatch(p->p.getName().startsWith("!h")));
		System.out.println(peoplesList.stream().anyMatch(p->p.getName().startsWith("!hx")));
		
		System.out.println(peoplesList.stream().anyMatch(StreamChapter2::isMature));
		System.out.println(peoplesList.stream().allMatch(StreamChapter2::isMature));
		
		/*
		 * [1] LAZILY executed stream operations:
			filter(…)     
			map(…)
			peek(…)
			flatMap(…)
			empty()
			distinct()
			limit(…)
			skip(…)
			sorted(), sorted(…)
			
			[2]
			The stream pipeline is executed when TERMINAL operation is invoked
			Each element in the stream source is passed through the pipeline
			
			[3]
			Basically, stream sources (Lists, arrays, IO streams…) shouldn’t change during streaming.
			Non-concurrent sources will throw ConcurrentModificationException
			It is possible to change concurrent sources (like ConcurrentLinkedList) during streaming
			Concurrent collections uses Lock API in order to perform effective atomic locks when 
			serving multiple threads

		 */

		// peek , filter, map, flatmap
		/* 
		 * Peek acts just like forEach(..) – but with one major difference:
		   peek() takes a Consumer<T> and returns a stream with the SAME elements 
		   Consumer code is executed on each element on its way to the new stream
		   This  is a big difference since peek() performs LAZILY
		 */
		System.out.println(peoplesList.stream().
				peek(p -> {p.getName().startsWith("!h"); }).findFirst().get().getName());
		System.out.println("Females: "+peoplesList.stream().
				filter (p->p.getGender()==Gender.F ).count());
		IntStream allFemaleAgesIntStream = peoplesList.stream().filter(p->p.getGender()==Gender.F).
				mapToInt(p -> p.getAge()); // mapToInt(apply) creates IntStream
		double allFemaleAgesAverage = allFemaleAgesIntStream.average().getAsDouble();
		System.out.println(allFemaleAgesAverage);
		
		// [1]
		// flat map Returns a stream consisting of the results of replacing each element of this stream 
		// with the contents of a mapped stream produced by applying the provided mapping function 
		// to each element. 
		// people.stream().flatMap(p -> Stream<String>.of(p.getPhones())...
		// [2]
		// Both map and flatMap can be applied to a Stream<T> and they both return a Stream<R>. 
		// The difference is that the map operation produces one output value for each input value, 
		// whereas the flatMap operation produces an arbitrary number (zero or more) values 
		// for each input value.
		Stream<String> names = peoplesList.stream().
				flatMap(p -> Stream.of(p.getName())); // gets an array of string
		System.out.println(names.count());
		Stream phones2 = peoplesList.stream().			
		 		flatMap(p -> Stream.of(p.getPhones())); // gets an array of array list
		System.out.println(phones2.findFirst().get());

	}
	
}
