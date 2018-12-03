package com.company;

public class ParallelStreams {

	/*
	 * A parallel stream has a much higher overhead compared to a sequential one. 
	 * Coordinating the threads takes a significant amount of time. 
	 * I would use sequential streams by default and only consider parallel ones if
	 * I have a massive amount of items to process 
	 * (or the processing of each item takes time and is parallelizable)
	 * I have a performance problem in the first place
	 * I don't already run the process in a multi-thread environment 
	 * (for example: in a web container, if I already have many requests to process in parallel, 
	 * adding an additional layer of parallelism inside each request could have more 
	 * negative than positive effects)
	 *  Only a measurement will tell you if the parallelism is worth it or not.
	 */
}
