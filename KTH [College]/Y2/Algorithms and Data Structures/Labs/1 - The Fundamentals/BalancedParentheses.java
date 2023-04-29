import java.util.Scanner;

class BalancedParentheses {
    
    class CharacterStack {
        Node last;

        private class Node {
            Node previous;
            char item;

            Node() {}

            Node(char item) {
                this.item = item;
            }

            Node(Node previous, char item) {
                this.previous = previous;
                this.item = item;
            }
        }

        void push(char c) {
            Node node = new Node(c);
            node.previous = last;
            last = node;
        }

        Character pop() {
            if(isEmpty()) throw new IllegalArgumentException("List is empty");

            char returnChar = last.item;

            last = last.previous;

            return returnChar;
        }
        
        boolean isEmpty() {
            return last == null;
        }
    }
    
    boolean areParenthesesBalanced(String input) {
        CharacterStack stack = new CharacterStack();
        
        Character compareChar;
        for(int i = 0; i < input.length(); i++) {
            Character currentChar = input.charAt(i);
            
            if(currentChar.equals('}')) compareChar = '{';
            else if(currentChar.equals(')')) compareChar = '(';
            else if(currentChar.equals(']')) compareChar = '[';
            else compareChar = null;
            
            if(currentChar.equals('(') || currentChar.equals('{') || currentChar.equals('['))
                stack.push(currentChar);
            else if(compareChar == null) 
                throw new IllegalArgumentException();
            else {
                Character poppedChar = null;
                try {
                    poppedChar = stack.pop();
                }
                catch(IllegalArgumentException e) {
                    return false;
                }
                if(poppedChar == null || !poppedChar.equals(compareChar))
                    return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BalancedParentheses bp = new BalancedParentheses();
        
        String input = "";
        
        boolean isValid = false;
        while(!isValid) {
            System.out.println("Input your String of parentheses:");
            input = sc.nextLine();
            for(int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if(c == '{' || c == '[' || c == '(' || c == ')' || c == ']' || c == '}')
                    isValid = true;
            }
        }

        if(bp.areParenthesesBalanced(input))
            System.out.println("The parentheses are balanced.");
        else
            System.out.println("The parentheses are not balanced.");
    }
}