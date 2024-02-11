package StringCalculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	public static int add(String numbers) {
		if(numbers.isEmpty()) {
			return 0;
		}

		String delimiter = ",|\\n";
		String numbersWithoutDelimiter = numbers;
		if(numbers.startsWith("//")){
			Matcher matcher = Pattern.compile("//(.)\n(.*)").matcher(numbers);
			if(matcher.matches()) {
				delimiter = matcher.group(1);
				numbersWithoutDelimiter = matcher.group(2);
			}
		}
		String[] numberArray = numbersWithoutDelimiter.split(delimiter);
		int sum=0;
		StringBuilder negativeNumbers = new StringBuilder();
		for(String number : numberArray) {
			int num = Integer.parseInt(number.trim());
			if(num<0) {
				if(negativeNumbers.length()>0) {
					negativeNumbers.append(",");
				}
				negativeNumbers.append(num);
			}
			if(num<=1000) {
             sum+=num;
			}
		}
		if(negativeNumbers.length()>0) {
			throw new IllegalArgumentException("negative numbers not allowed:"+negativeNumbers.toString());
		}
		return sum;
	}
	public static void main(String[] args) {
		System.out.println(add(""));
		System.out.println(add("1"));
		System.out.println(add("1,5"));
		System.out.println(add("1\n2,3"));
		System.out.println(add("//;\n1;2"));
	}
}
