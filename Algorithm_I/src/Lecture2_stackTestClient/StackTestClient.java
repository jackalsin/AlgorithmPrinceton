package Lecture2_stackTestClient;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Stack;

/**
 * @author jacka
 * @version 1.0 on 6/9/2016.
 */
public class StackTestClient {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(stack.pop());
            else
                stack.push(s);
        }
    }
}
