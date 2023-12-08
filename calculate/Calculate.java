package calculate;

public class Calculate {
    public static void main(String[] args){
        Long[] loops = new Long[] {22411l,18113l,16271l,13201l,24253l,14429l};
        Long tmp= 22411l;
        for(int i = 1; i < loops.length; i++){
            tmp = lcm(tmp, loops[i]);
        }
        System.out.println(tmp);
    }

    public static Long lcm(Long n1, Long n2){
        Long gcd = 1l;
        for(Long i = 1l; i <= n1 && i <= n2; ++i) {
            // Checks if i is factor of both integers
            if(n1 % i == 0 && n2 % i == 0)
            gcd = i;
        }
        return (n1 * n2) / gcd;
    }
}
