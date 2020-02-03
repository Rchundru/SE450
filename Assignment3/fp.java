package homework3;

import java.util.List;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class fp {
	
static <U,V> List<V> map(Iterable<U> l, Function<U,V> f) {
	List<V> list = new ArrayList();
	for(U i: l) {
		list.add(f.apply(i));
	}
	return list;
}


static <U,V> V foldLeft(V e, Iterable<U>l, BiFunction<V,U,V> f){
	for(U i: l) {
		e = f.apply(e, i);
	}
	return e;
}




static <U,V> V foldRight(V e, Iterable<U>l, BiFunction<U,V,V> f){
	Iterator<U> u = l.iterator();
	List<U> k = new ArrayList();
	while(u.hasNext()) {
		k.add(u.next());
	}
	for(int i = k.size()-1; i >= 0; i--) {
		e = f.apply(k.get(i), e);
	}
	return e;
}



static <U> Iterable<U> filter(Iterable<U> l, Predicate<U> p){
	List<U> list = new ArrayList();
	for( U i: l) {
		if(p.test(i))
		list.add(i);
	}
	return list;
	}

static <U> U minVal(Iterable<U> l, Comparator<U> c){
	// write using fold.  No other loops permitted. 
	BiFunction<U, U, U> function1 = (a,b) -> {
		U min=a;
		if(c.compare(b,a)<0) min=b;
		return min;
	};
	U minVal = l.iterator().next();
	minVal = foldLeft(minVal, l, function1);
	return minVal;
}

static <U extends Comparable<U>> int minPos(Iterable<U> l){
	// write using fold.  No other loops permitted. 
	class compareU implements Comparator<U> 
	{ 
		public boolean indexFound = false;
		@Override
		public int compare(U a, U b) {
			// TODO Auto-generated method stub
			return a.compareTo(b);
		} 
		
		public boolean getIndexFound() {
			return indexFound;
		}
		
		public void setIndexFound() {
			indexFound = true;
		}
	} 
	Comparator<U> c = (Comparator<U>) new compareU();
	compareU j = new compareU();
	U minVal = minVal(l, c);
	int index = 0;
	BiFunction<Integer, U,Integer> function1 = (a,b) -> {
			
		if(c.compare(b,minVal)!=0 && j.getIndexFound() == false) {
			a++;
		}
		else {
			j.setIndexFound();
		}
		return a;
	};
	index = foldLeft(index, l, function1);
	return index;
}

int sum(List<Integer> l) {
	BiFunction<Integer,Integer,Integer> b = (a,c) -> a+c;
	return foldLeft(0, l, b );
}

String s (List<String> l) {
	BiFunction<String, String, String> b = (a,c) -> a+c;
	return foldRight("", l, b);
}

	public static void main(String[] args) {
		// (1) Use map to implement the following behavior (described in Python).  i.e given a List<T> create a List<Integer> of the hashes of the objects.
		// names = ['Mary', 'Isla', 'Sam']
		// for i in range(len(names)):
		       // names[i] = hash(names[i])
		
		//**DONE IN TEST CLASS**
		
		// (2) Use foldleft to calculate the sum of a list of integers.
		// i.e write a method: int sum(List<Integer> l)
		
		//**DONE IN TEST CLASS**
		
		// (3) Use foldRight to concatenate a list of strings i.e write a method
		// String s (List<String> l)
		
		//**DONE IN TEST CLASS**
		
		// (4) consider an array of Persons. Use filter to 
		// print the names of the Persons whose salary is
		// greater than 100000
		
		//**DONE IN TEST CLASS**
		
		// (5) Use minVal to calculate the minimum of a List of 
		//       Integers
		
		//**DONE IN TEST CLASS**
		
        // (6) Use minVal to calculate the maximum of a List of 
		//       Integers
		
		//**DONE IN TEST CLASS**
		
		// (7)  Use minPos to calculate the position of the
		// minimum in  a List of  Integers
		
		//**DONE IN TEST CLASS**

		// (8)  Use minPos to calculate the position of the
		// maximum in  a List of  String
		
		//**DONE IN TEST CLASS**
		
	}

}

class Person{
	final int salary;
	final String name;
	
	Person(int salary, String name){
		this.salary = salary;
		this.name = name;
	}
	
	int getSalary() { return salary; }
	String name() { return name;}
	
}