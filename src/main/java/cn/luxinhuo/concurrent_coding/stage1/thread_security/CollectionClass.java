package cn.luxinhuo.concurrent_coding.stage1.thread_security;

import java.util.Stack;

public class CollectionClass {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.push(0);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.get(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
