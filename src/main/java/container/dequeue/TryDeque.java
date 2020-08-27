package container.dequeue;

import java.util.*;

/**
 * 双向队列(Deque),是Queue的一个子接口，双向队列是指该队列两端的元素既能入队(offer)也能出队(poll),
 * 如果将Deque限制为只能从一端入队和出队，则可实现栈的数据结构。对于栈而言，有入栈(push)和出栈(pop)，遵循先进后出原则
 */
public class TryDeque {
    public static void main(String[] args) {
        Map<String,Integer> map = new TreeMap<>();
        Set<String> set = new TreeSet<>();
        List<String> list=new LinkedList<>();
        List<String> list2=new ArrayList<>();
        List<String> list3=new Vector<>();
        Queue<String> priqueue=new PriorityQueue<>();
        Deque<String> deque = new ArrayDeque<>(); /*Adding element to deque*/
        deque.add("One");
        deque.addFirst("Two");
        deque.addLast("Three");
        deque.offer("Four");
        deque.offerFirst("Five");
        deque.offerLast("Six");
        deque.push("Seven");
        System.out.println("Elements in deque: " + deque);
        System.out.println(); /*Retrieving elements from deque*/
        System.out.println("element() : " + deque.element());
        System.out.println("getFirst() : " + deque.getFirst());
        System.out.println("getLast() : " + deque.getLast());
        System.out.println("peek() : " + deque.peek());
        System.out.println("peekFirst() : " + deque.peekFirst());
        System.out.println("peekLast() : " + deque.peekLast()); /*Removing elements from deque */
        System.out.println();
        System.out.println("remove() : " + deque.remove());
        System.out.println("Elements in deque after removal : " + deque);
        System.out.println("removeFirst() : " + deque.removeFirst());
        System.out.println("Elements in deque after removal : " + deque);
        System.out.println("removeLast() : " + deque.removeLast());
        System.out.println("Elements in deque after removal : " + deque);
        System.out.println("poll() : " + deque.poll());
        System.out.println("Elements in deque after removal : " + deque);
        System.out.println("pollFirst() : " + deque.pollFirst());
        System.out.println("Elements in deque after removal : " + deque);
        System.out.println("pollLast() : " + deque.pollLast());
        System.out.println("Elements in deque after removal : " + deque);
        System.out.println("pop() : " + deque.pop());
        System.out.println("Elements in deque after removal : " + deque);
    }
}
