import acm.program.ConsoleProgram;

//detects prime numbers
public class Problem31 extends ConsoleProgram {
    public void run(){
        int n = readInt();
        println(isPrime(n) ? n + " is prime": n + " is not prime");
    }
    private boolean isPrime(int n){
        if(n == 2)return true;
        if(n <= 1 || n % 2 == 0)return false;
        for (int i = 3; i * i <= n; i += 2){
            if(n % i == 0)return false;
        }
        return true;
    }

    //first n prime list
    public static class Problem32 extends ConsoleProgram {
        public void run(){
            Problem31 primeTector = new Problem31();
            int n = 1000;

            for(int i = 0; i < n; i++){
                if(primeTector.isPrime(i))println(i + " is prime");
            }
        }
    }
}
