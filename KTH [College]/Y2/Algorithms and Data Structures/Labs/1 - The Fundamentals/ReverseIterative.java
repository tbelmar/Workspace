import java.io.*;
import java.util.Scanner;

public class ReverseIterative {
    private static class CharacterStack {
        private class CharacterNode {
            char character;
            CharacterNode previous;
            
            CharacterNode(char character, CharacterNode previous) {
                this.character = character;
                this.previous = previous;
            }
        }
        
        CharacterNode last;
        
        CharacterStack() {
            last = null;
        }
        
        CharacterStack(String str) {
            for(int i = 0; i < str.length(); i++)
                this.push(str.charAt(i));
        }
        
        void push(char c) {
            CharacterNode newNode = new CharacterNode(c, last);
            last = newNode;
        }
        
        char pop() {
            if(isEmpty()) throw new IllegalArgumentException("List is empty");
            
            CharacterNode returnNode = last;
            last = last.previous;
            
            return returnNode.character;
        }
        
        boolean isEmpty() {
            return last == null;
        }
        
        int size() {
            int count = 0;
            CharacterNode counterNode = last;
            
            while(counterNode != null) {
                count++;
                counterNode = counterNode.previous;
            }
            
            return count;
        }
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your String:");
        String str = sc.nextLine();
        
        CharacterStack charStack = new CharacterStack(str);
        
        while(!charStack.isEmpty())
            System.out.print(charStack.pop());
    }
}