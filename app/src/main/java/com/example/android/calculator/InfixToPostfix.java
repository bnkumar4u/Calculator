package com.example.android.calculator;

import java.util.Stack;

public class InfixToPostfix
{

    private int priority(char x)
    {
        if(x=='=')
            return 0;
        if(x=='+'||x=='-')
            return 1;
        if(x=='*'||x=='/')
            return 2;
        if(x=='^')
            return 3;
        return -1;
    }


    public  String[] toPostfix(String s)
    {
        s=s+" ";
        Stack<Character> stack=new Stack<Character>();
        char[] c=s.toCharArray();
        char t;
        int count=0;
        int j=0;
        String temp;

        for (char ci:c)
            if (ci == '+' || ci == '-'|| ci == '*' || ci == '/' || ci == '^'||ci=='%')
                count++;
        count=2*count+1;

        String[] pf=new String[count];

        for(int i=0;i<s.length();i++)
        {

            if( (c[i]>='0'&&c[i]<='9')||c[i]=='.')
            {
                temp=""+c[i];
                i++;
                if (i>=c.length)
                    break;
                while ( (c[i]>='0'&&c[i]<='9')||c[i]=='.')
                {
                    temp=temp+c[i];
                    i++;
                }
                pf[j]=temp;
                j++;
                i--;
            }
            else if(c[i]=='(')
                stack.push(c[i]);
            else if(c[i]==')')
            {
                t=stack.pop();
                while (t!='(')
                {
                    pf[j]=""+t;
                    j++;
                    t=stack.pop();
                }

            }
            else if(c[i]==' ');
            else
            {
                if (!stack.empty())
                {
                    t=stack.pop();
                    stack.push(t);
                    while (!stack.empty()&&t!='('&&priority(c[i])<=priority(t))
                    {
                        t=stack.pop();
                        pf[j]=""+t;

                        j++;
                        if(!stack.empty())
                        {
                            t=stack.pop();
                            stack.push(t);
                        }
                    }
                }
                stack.push(c[i]);
            }


        }
        while(!stack.empty())
        {
            t=stack.pop();
            pf[j]=""+t;

            j++;
        }
        return pf;
    }
     double evalute(String si)
    {
        String[] pf=toPostfix(si);
        double ans;
        Stack<Double> stack=new Stack<>();

        for (String s:pf)
        {
            s.replaceAll("\\s","");
            if(s.equals("+")|| s.equals("-")|| s.equals("*")|| s.equals("/")|| s.equals("^")||s.equals("%"))
            {
                double a,b,r;
                r=0;
                b=stack.pop();
                a=stack.pop();
                System.out.println(a+""+s+""+b);

                switch (s.charAt(0))
                {
                    case '+':
                        r=a+b;
                        break;

                    case '-':
                        r=a-b;
                        break;
                    case '*':
                        r=a*b;
                        break;
                    case '/':
                        r=a/b;
                        break;
                    case '^':
                        r=Math.pow(a,b);
                        break;

                    case '%':
                        r=a%b;
                        break;

                }
                System.out.println(r);
                stack.push(r);
            }
            else if(s==" ");
            else
            {
                double a=Double.parseDouble(s);
                stack.push(a);
                System.out.println(a);
            }
        }
        ans=stack.pop();
        return ans;
    }
}
