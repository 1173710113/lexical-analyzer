package sdt;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

public class StackTest {

	@Test
	public void test() {
		Stack<String> stack = new Stack<String>();
		stack.push("P");
		stack.push("PSyn");
		stack.push("a");
		stack.push("Ssyn");
		System.out.println(stack.get(0));
		for(int i = 0; i < stack.size(); i++) {
			if(stack.get(i).equals("a")){
				System.out.println(stack.get(i));
				break;
			}
		}
	}

}
