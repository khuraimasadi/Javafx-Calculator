package application;

import java.util.*;

class Expression {
	// infixToPostfix
	public static String[] infixToPostfix(String string) {
		int count=0;
		int counter=0;
		int temp=0;
		StringTokenizer stk = new StringTokenizer(string,"+-/*()^%",true);
		StringTokenizer stk1 = new StringTokenizer(string,"+-/*()^%",true);
		while(stk.hasMoreTokens()) {
		stk.nextToken();
			temp++;
		}
		String s[]=new String[temp];
		while(stk1.hasMoreTokens()) {
			String ms="";
			ms=stk1.nextToken();
			ms=ms.replace('~','-');
			s[counter]=ms;
			counter++;
		}
		Stack st = new Stack();
		String[] s1 = new String[1000];
		for (int i = 0; i < s.length; i++) 
		{
			
			if (s[i].equals("+")|| s[i].equals("-")) 
			{
				if (st.isEmpty()) 
				{
					st.push(s[i]);
				} 
				else if (st.peek().equals("(")) 
				{
					st.push(s[i]);
				} 
				else 
				{
					while (st.peek().equals("+") || st.peek().equals("-") || st.peek().equals("/")|| st.peek().equals("*")|| st.peek().equals("%")|| st.peek().equals("^")) 
					{
						s1[count] = st.pop();
						count++;

					}
					st.push(s[i]);
				}
			} 
			else if (s[i].equals("*")|| s[i].equals("/")|| s[i].equals("%")|| s[i].equals("^")) 
			{
				if (st.isEmpty()) 
				{
					st.push(s[i]);
				} 
				else 
				{
					if (st.peek().equals("+") || st.peek().equals("-")) 
					{
						st.push(s[i]);
					} 
					else if (st.peek().equals("/") || st.peek().equals("*")|| st.peek().equals("%")|| st.peek().equals("^")) 
					{
						while (st.peek().equals("/") || st.peek().equals("*")|| st.peek().equals("%")|| st.peek().equals("^")) 
						{
							s1[count] =  st.pop();
							count++;
						}
						st.push(s[i]);
					} 
					else if (st.peek().equals("(")) 
					{
						st.push(s[i]);
					}
				}
			} 
			else if (s[i].equals("(")) 
			{
				st.push(s[i]);
			} 
			else if (s[i].equals(")")) 
			{
				while (!st.peek().equals("(")) 
				{
					s1[count] =st.pop();
					count++;
				}
				st.pop();
			} 
			else 
			{
				s1[count] = s[i];
				count++;
			}

		}
		while (!st.isEmpty()) {

			s1[count] = st.pop();
			count++;
		}
		String[] ns = new String[count];
		count=0;
		for(String i : s1) {
			if(count==ns.length)
				break;
			ns[count]=s1[count];
			count++;
		}
		return ns;
	}

	// print
	public static double print(String s) 
	{
		String[] s1 = infixToPostfix(s);
		Stack st = new Stack();
		for (int i = 0; i < s1.length; i++) 
		{
			if (s1[i].equals("+")|| s1[i].equals("-")|| s1[i].equals("*") ||s1[i].equals("/")||s1[i].equals("%")||s1[i].equals("^")) {
				String op2 = st.pop();
				String op1 = st.pop();
				st.push(evaluate(s1[i], op1, op2));
			} 
			else 
			{
				st.push(s1[i]);
			}
		}
		return Double.parseDouble(st.pop());
	}

	// evaluate
	public static String evaluate(String ch, String op1, String op2) {
		double opp1 = Double.parseDouble(op1);
		double opp2 = Double.parseDouble(op2);
		switch (ch) {
		case "+":
			return "" + (opp1 + opp2);

		case "-":
			return "" + (opp1 - opp2);

		case "*":
			return "" + (opp1 * opp2);

		case "/":
			return "" + (opp1 / opp2);
		case "%":
			return "" + (opp1 % opp2);
		case "^":
			return "" + (Math.pow(opp1 , opp2));
		default:
			return "0";
		}
	}
}

public class EvaluateExpression {
	public static void main(String args[]) {
		try {
		Expression.print("7*5.5");
		}catch(Exception e) {
			System.out.println("Expression error");
		}
	}
}
