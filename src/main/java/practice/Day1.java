package practice;

public class Day1 {
    public static boolean CheckEvenOddNumbers(int number){
        if(number<0){
            System.out.println("Nhap so lon hon 0! ");
            return  false;
        }else if(number%2==0){
            System.out.println(number+ " la so chan !");
            return  true;
        }else{
            System.out.println(number+ " la so le !");
            return false;
        }
    }
}
