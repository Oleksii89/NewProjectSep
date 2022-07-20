public class Main {
public static void main(String[] args)  {

 /*Cat Zombik = new Cat();
 Dog Betty = new Dog();
 Zombik.voice();
 Betty.voice();
 System.out.println(Zombik.voice());
 System.out.println(Betty.voice());*/
 Integer a = 3;
 Integer b = 0;
   //System.out.println(a/b);

   try {
    System.out.println("Message before exception.");
    System.out.println(a/b);
   }
   catch (ArithmeticException exception) {
    System.out.println("can't divide to 0 ");
   }
   }
}