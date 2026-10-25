
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class SinglyLinkedList {
        Node head;

        void insertBeginning(int value) {
            Node n = new Node(value);
            n.next = head;
            head = n;
            System.out.println("Inserted successfully.");
        }

        void insertEnd(int value) {
            Node n = new Node(value);
            if (head == null) {
                head = n;
            } else {
                Node cur = head;
                while (cur.next != null) cur = cur.next;
                cur.next = n;
            }
            System.out.println("Inserted successfully.");
        }

        int count() {
            int c = 0;
            for (Node cur = head; cur != null; cur = cur.next) c++;
            return c;
        }

        void insertPosition(int value, int pos) {
            if (pos < 1 || pos > count() + 1) {
                System.out.println("Invalid position.");
                return;
            }
            if (pos == 1) {
                insertBeginning(value);
                return;
            }
            Node cur = head;
            for (int i = 1; i < pos - 1; i++) cur = cur.next;
            Node n = new Node(value);
            n.next = cur.next;
            cur.next = n;
            System.out.println("Inserted successfully.");
        }

        void insertRelative(int value, int target, boolean after) {
            Node cur = head;
            while (cur != null && cur.data != target) cur = cur.next;
            if (cur == null) {
                System.out.println("Value not found.");
                return;
            }
            Node n = new Node(value);
            if (after) {
                n.next = cur.next;
                cur.next = n;
            } else if (cur == head) {
                n.next = head;
                head = n;
            } else {
                Node prev = head;
                while (prev.next != cur) prev = prev.next;
                prev.next = n;
                n.next = cur;
            }
            System.out.println("Inserted successfully.");
        }

        void deleteBeginning() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            head = head.next;
            System.out.println("Deleted successfully.");
        }

        void deleteEnd() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            if (head.next == null) {
                head = null;
            } else {
                Node cur = head;
                while (cur.next.next != null) cur = cur.next;
                cur.next = null;
            }
            System.out.println("Deleted successfully.");
        }

        void deletePosition(int pos) {
            if (pos < 1 || pos > count()) {
                System.out.println("Invalid position.");
                return;
            }
            if (pos == 1) {
                deleteBeginning();
                return;
            }
            Node cur = head;
            for (int i = 1; i < pos - 1; i++) cur = cur.next;
            cur.next = cur.next.next;
            System.out.println("Deleted successfully.");
        }

        void deleteValue(int value) {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            if (head.data == value) {
                head = head.next;
                System.out.println("Deleted successfully.");
                return;
            }
            Node cur = head;
            while (cur.next != null && cur.next.data != value)
                cur = cur.next;
            if (cur.next == null) {
                System.out.println("Value not found.");
            } else {
                cur.next = cur.next.next;
                System.out.println("Deleted successfully.");
            }
        }

        void search(int value) {
            int pos = 1;
            for (Node cur = head; cur != null; cur = cur.next, pos++) {
                if (cur.data == value) {
                    System.out.println("Found at position " + pos);
                    return;
                }
            }
            System.out.println("Value not found.");
        }

        void update(int oldValue, int newValue) {
            for (Node cur = head; cur != null; cur = cur.next) {
                if (cur.data == oldValue) {
                    cur.data = newValue;
                    System.out.println("Updated successfully.");
                    return;
                }
            }
            System.out.println("Value not found.");
        }

        void display() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            for (Node cur = head; cur != null; cur = cur.next)
                System.out.print(cur.data + " -> ");
            System.out.println("NULL");
        }

        void extremes(boolean maximum) {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            int result = head.data;
            for (Node cur = head.next; cur != null; cur = cur.next)
                result = maximum ? Math.max(result, cur.data)
                                 : Math.min(result, cur.data);
            System.out.println(maximum ? "Maximum: " + result
                                       : "Minimum: " + result);
        }

        void middle() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            Node slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            System.out.println("Middle element: " + slow.data);
        }

        void reverse() {
            Node prev = null, cur = head;
            while (cur != null) {
                Node next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            head = prev;
            System.out.println("List reversed.");
        }

        void sort() {
            for (Node i = head; i != null; i = i.next)
                for (Node j = i.next; j != null; j = j.next)
                    if (i.data > j.data) {
                        int t = i.data;
                        i.data = j.data;
                        j.data = t;
                    }
            System.out.println("List sorted.");
        }

        void removeDuplicates() {
            for (Node cur = head; cur != null; cur = cur.next) {
                Node prev = cur;
                Node next = cur.next;
                while (next != null) {
                    if (next.data == cur.data) {
                        prev.next = next.next;
                        next = prev.next;
                    } else {
                        prev = next;
                        next = next.next;
                    }
                }
            }
            System.out.println("Duplicates removed.");
        }

        void menu() {
            while (true) {
                System.out.println("\n--- SINGLY LINKED LIST ---");
                System.out.println("1.Insert Beginning  2.Insert End");
                System.out.println("3.Insert Position  4.Insert After");
                System.out.println("5.Insert Before  6.Delete Beginning");
                System.out.println("7.Delete End  8.Delete Position");
                System.out.println("9.Delete Value  10.Search");
                System.out.println("11.Update  12.Display  13.Count");
                System.out.println("14.Maximum  15.Minimum  16.Middle");
                System.out.println("17.Reverse  18.Sort  19.Remove Duplicates");
                System.out.println("20.Back");
                System.out.print("Choice: ");

                int ch = sc.nextInt();
                int value, target, pos;

                switch (ch) {
                    case 1:
                        System.out.print("Value: ");
                        insertBeginning(sc.nextInt());
                        break;
                    case 2:
                        System.out.print("Value: ");
                        insertEnd(sc.nextInt());
                        break;
                    case 3:
                        System.out.print("Value and position: ");
                        value = sc.nextInt(); pos = sc.nextInt();
                        insertPosition(value, pos);
                        break;
                    case 4:
                    case 5:
                        System.out.print("New value and target value: ");
                        value = sc.nextInt(); target = sc.nextInt();
                        insertRelative(value, target, ch == 4);
                        break;
                    case 6: deleteBeginning(); break;
                    case 7: deleteEnd(); break;
                    case 8:
                        System.out.print("Position: ");
                        deletePosition(sc.nextInt());
                        break;
                    case 9:
                        System.out.print("Value: ");
                        deleteValue(sc.nextInt());
                        break;
                    case 10:
                        System.out.print("Search value: ");
                        search(sc.nextInt());
                        break;
                    case 11:
                        System.out.print("Old value and new value: ");
                        value = sc.nextInt(); target = sc.nextInt();
                        update(value, target);
                        break;
                    case 12: display(); break;
                    case 13: System.out.println("Nodes: " + count()); break;
                    case 14: extremes(true); break;
                    case 15: extremes(false); break;
                    case 16: middle(); break;
                    case 17: reverse(); break;
                    case 18: sort(); break;
                    case 19: removeDuplicates(); break;
                    case 20: return;
                    default: System.out.println("Invalid choice.");
                }
            }
        }
    }

    static class DoublyLinkedList {
        static class DNode {
            int data;
            DNode prev, next;

            DNode(int data) {
                this.data = data;
            }
        }

        DNode head, tail;

        int count() {
            int n = 0;
            for (DNode p = head; p != null; p = p.next) n++;
            return n;
        }

        DNode find(int value) {
            for (DNode p = head; p != null; p = p.next)
                if (p.data == value) return p;
            return null;
        }

        void insertBeginning(int value) {
            DNode n = new DNode(value);
            n.next = head;
            if (head != null) head.prev = n;
            else tail = n;
            head = n;
            System.out.println("Inserted.");
        }

        void insertEnd(int value) {
            DNode n = new DNode(value);
            n.prev = tail;
            if (tail != null) tail.next = n;
            else head = n;
            tail = n;
            System.out.println("Inserted.");
        }

        void insertPosition(int value, int pos) {
            if (pos < 1 || pos > count() + 1) {
                System.out.println("Invalid position.");
                return;
            }
            if (pos == 1) {
                insertBeginning(value);
                return;
            }
            if (pos == count() + 1) {
                insertEnd(value);
                return;
            }
            DNode p = head;
            for (int i = 1; i < pos; i++) p = p.next;
            DNode n = new DNode(value);
            n.prev = p.prev;
            n.next = p;
            p.prev.next = n;
            p.prev = n;
            System.out.println("Inserted.");
        }

        void insertRelative(int value, int target, boolean after) {
            DNode p = find(target);
            if (p == null) {
                System.out.println("Value not found.");
                return;
            }
            if (after && p == tail) {
                insertEnd(value);
                return;
            }
            if (!after && p == head) {
                insertBeginning(value);
                return;
            }

            DNode n = new DNode(value);
            if (after) {
                n.prev = p;
                n.next = p.next;
                p.next.prev = n;
                p.next = n;
            } else {
                n.next = p;
                n.prev = p.prev;
                p.prev.next = n;
                p.prev = n;
            }
            System.out.println("Inserted.");
        }

        void deleteNode(DNode p) {
            if (p == null) {
                System.out.println("Value not found.");
                return;
            }
            if (p.prev != null) p.prev.next = p.next;
            else head = p.next;

            if (p.next != null) p.next.prev = p.prev;
            else tail = p.prev;

            System.out.println("Deleted.");
        }

        void deleteBeginning() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            deleteNode(head);
        }

        void deleteEnd() {
            if (tail == null) {
                System.out.println("List is empty.");
                return;
            }
            deleteNode(tail);
        }

        void deletePosition(int pos) {
            if (pos < 1 || pos > count()) {
                System.out.println("Invalid position.");
                return;
            }
            DNode p = head;
            for (int i = 1; i < pos; i++) p = p.next;
            deleteNode(p);
        }

        void deleteValue(int value) {
            deleteNode(find(value));
        }

        void search(int value) {
            int pos = 1;
            for (DNode p = head; p != null; p = p.next, pos++) {
                if (p.data == value) {
                    System.out.println("Found at position " + pos);
                    return;
                }
            }
            System.out.println("Value not found.");
        }

        void update(int oldValue, int newValue) {
            DNode p = find(oldValue);
            if (p == null) System.out.println("Value not found.");
            else {
                p.data = newValue;
                System.out.println("Updated.");
            }
        }

        void displayForward() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            System.out.print("NULL <- ");
            for (DNode p = head; p != null; p = p.next)
                System.out.print(p.data + (p.next == null ? " -> " : " <-> "));
            System.out.println("NULL");
        }

        void displayBackward() {
            if (tail == null) {
                System.out.println("List is empty.");
                return;
            }
            System.out.print("NULL <- ");
            for (DNode p = tail; p != null; p = p.prev)
                System.out.print(p.data + (p.prev == null ? " -> " : " <-> "));
            System.out.println("NULL");
        }

        void extremes(boolean max) {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            int result = head.data;
            for (DNode p = head.next; p != null; p = p.next)
                result = max ? Math.max(result, p.data)
                             : Math.min(result, p.data);
            System.out.println((max ? "Maximum: " : "Minimum: ") + result);
        }

        void middle() {
            if (head == null) {
                System.out.println("List is empty.");
                return;
            }
            DNode slow = head, fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            System.out.println("Middle element: " + slow.data);
        }

        void reverse() {
            DNode p = head;
            while (p != null) {
                DNode temp = p.prev;
                p.prev = p.next;
                p.next = temp;
                p = p.prev;
            }
            DNode temp = head;
            head = tail;
            tail = temp;
            System.out.println("List reversed.");
        }

        void sort() {
            for (DNode i = head; i != null; i = i.next)
                for (DNode j = i.next; j != null; j = j.next)
                    if (i.data > j.data) {
                        int t = i.data;
                        i.data = j.data;
                        j.data = t;
                    }
            System.out.println("List sorted.");
        }

        void removeDuplicates() {
            for (DNode p = head; p != null; p = p.next) {
                DNode q = p.next;
                while (q != null) {
                    DNode next = q.next;
                    if (q.data == p.data) {
                        if (q.prev != null) q.prev.next = q.next;
                        if (q.next != null) q.next.prev = q.prev;
                        if (q == tail) tail = q.prev;
                    }
                    q = next;
                }
            }
            System.out.println("Duplicates removed.");
        }

        void menu() {
            while (true) {
                System.out.println("\n--- DOUBLY LINKED LIST ---");
                System.out.println("1.Insert Beginning  2.Insert End");
                System.out.println("3.Insert Position  4.Insert After");
                System.out.println("5.Insert Before  6.Delete Beginning");
                System.out.println("7.Delete End  8.Delete Position");
                System.out.println("9.Delete Value  10.Search");
                System.out.println("11.Update  12.Display Forward");
                System.out.println("13.Display Backward  14.Count");
                System.out.println("15.Maximum  16.Minimum  17.Middle");
                System.out.println("18.Reverse  19.Sort  20.Remove Duplicates");
                System.out.println("21.Back");
                System.out.print("Choice: ");

                int ch = sc.nextInt();
                int value, target;

                switch (ch) {
                    case 1:
                        System.out.print("Value: ");
                        insertBeginning(sc.nextInt());
                        break;
                    case 2:
                        System.out.print("Value: ");
                        insertEnd(sc.nextInt());
                        break;
                    case 3:
                        System.out.print("Value and position: ");
                        value = sc.nextInt();
                        insertPosition(value, sc.nextInt());
                        break;
                    case 4:
                    case 5:
                        System.out.print("New value and target value: ");
                        value = sc.nextInt();
                        target = sc.nextInt();
                        insertRelative(value, target, ch == 4);
                        break;
                    case 6: deleteBeginning(); break;
                    case 7: deleteEnd(); break;
                    case 8:
                        System.out.print("Position: ");
                        deletePosition(sc.nextInt());
                        break;
                    case 9:
                        System.out.print("Value: ");
                        deleteValue(sc.nextInt());
                        break;
                    case 10:
                        System.out.print("Search value: ");
                        search(sc.nextInt());
                        break;
                    case 11:
                        System.out.print("Old value and new value: ");
                        value = sc.nextInt();
                        update(value, sc.nextInt());
                        break;
                    case 12: displayForward(); break;
                    case 13: displayBackward(); break;
                    case 14: System.out.println("Nodes: " + count()); break;
                    case 15: extremes(true); break;
                    case 16: extremes(false); break;
                    case 17: middle(); break;
                    case 18: reverse(); break;
                    case 19: sort(); break;
                    case 20: removeDuplicates(); break;
                    case 21: return;
                    default: System.out.println("Invalid choice.");
                }
            }
        }
    }


    public static void main(String[] args) {
        SinglyLinkedList singly = new SinglyLinkedList();
        DoublyLinkedList doubly = new DoublyLinkedList();

        while (true) {
            System.out.println("\n=== LINKED LIST MANAGEMENT SYSTEM ===");
            System.out.println("1. Singly Linked List");
            System.out.println("2. Doubly Linked List (next step)");
            System.out.println("3. Compare Singly vs Doubly");
            System.out.println("4. Exit");
            System.out.print("Choice: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: singly.menu(); break;
                case 2:
                    doubly.menu();
                    break;
                case 3:
                    System.out.println("Singly: one next reference per node.");
                    System.out.println("Doubly: previous and next references per node.");
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
