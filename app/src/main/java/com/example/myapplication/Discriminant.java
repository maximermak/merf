package com.example.myapplication;

public class Discriminant {
    public static String discText(int a, int b, int c) {
        double x1,x2,D;
        D = b*b - 4 * a * c;
        x1 = (-b + Math.sqrt(D))/2 * a;
        x2 = (-b - Math.sqrt(D))/2 * a;
        String  tmp = "x1 = " + x1 + " x2 = " + x2;
        if(D < 0)
        {
            tmp = "Кореней нет.";
        }

        if(D == 0)
        {
            int tmp2 = -b/2*a;
            tmp = "x = " + tmp2;
        }
    return "a = " + a + " b = " + b + " c = " + c +
            "\n D = " + D + "\n" + tmp;
    }
}
