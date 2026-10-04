import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * NumberLogicPrograms
 * All special number logic programs in one file
 * Menu driven program that checks 55 special numbers:
 * Technical, Magic, Disarium, Adam, Automorphic, Trimorphic, Neon, Unique, Krishnamurthy, Armstrong, Special, Happy, Fascinating, Lynch-Bell, Palindrome, Harshad, Duck, Buzz, Spy, Evil (OneEven), Odious, Pernicious, Pandigital, Bouncy, Keith, Kaprekar, Even, Odd, Triangular, Prime, Smith, Ugly, Twin Prime, Twisted Prime, Circular Prime, Lucky, Leyland (Lead), Perfect, Abundant, Deficient, Sunny, Emirp, Pronic, Sphenic, Fibonacci, Perfect Square, Composite, Goldbach, Weird, Pell, PalPrime, Mersenne Prime, Sophie Germain Prime, Semiprime, Perfect Cube.
 * Each number has its own function; main() shows the menu, reads the
 * choice and the number, calls the matching function and prints the result.
 */
public class numb {

    // ---------------------------------------------------------------
    // Common helper functions
    // ---------------------------------------------------------------
    /** Returns the number of digits in n. */
    static int countDigits(long n) {
        return String.valueOf(n).length();
    }

    /** Returns the sum of the digits of n. */
    static long digitSum(long n) {
        long sum = 0;
        while (n > 0) {
            sum += n % 10;   // extract last digit and add it
            n /= 10;         // remove last digit
        }
        return sum;
    }

    /** Returns the reverse of n (e.g. 123 -> 321). */
    static long reverse(long n) {
        long rev = 0;
        while (n > 0) {
            rev = rev * 10 + n % 10;
            n /= 10;
        }
        return rev;
    }

    /** Returns the factorial of a single digit (0! to 9!). */
    static long factorial(int d) {
        long f = 1;
        for (int i = 2; i <= d; i++) f *= i;
        return f;
    }

    // ---------------------------------------------------------------
    // Perfect number
    // Sum of its proper divisors (all divisors except itself) equals n.
    // Example: 28 -> 1+2+4+7+14 = 28
    // ---------------------------------------------------------------
    static long sumProperDivisors(long n) {
        if (n < 2) return 0;
        long sum = 1;                              // 1 divides every number
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) sum += n / i;      // add the paired divisor
            }
        }
        return sum;
    }

    /** Helper for Weird numbers: can some of the divisors (from index i) add up to target? */
    static boolean subsetSum(long[] divs, long[] suffix, int i, long target) {
        if (target == 0) return true;
        if (i == divs.length || suffix[i] < target) return false;   // not enough left
        if (divs[i] <= target && subsetSum(divs, suffix, i + 1, target - divs[i])) return true;
        return subsetSum(divs, suffix, i + 1, target);
    }

    // ---------------------------------------------------------------
    // Technical number
    // A number with an EVEN number of digits which is equal to the
    // square of the sum of its two halves.  Example: 2025 -> (20+25)^2 = 2025
    // ---------------------------------------------------------------
    static boolean isTechnical(long n) {
        int d = countDigits(n);
        if (d % 2 != 0) return false;          // must have even digits
        long divisor = (long) Math.pow(10, d / 2);
        long left = n / divisor;               // first half
        long right = n % divisor;              // second half
        long sum = left + right;
        return sum * sum == n;
    }
    // ---------------------------------------------------------------
    // Magic number
    // Keep adding the digits until a single digit remains.
    // If that single digit is 1, the number is a magic number.
    // Example: 19 -> 1+9 = 10 -> 1+0 = 1  (Magic)
    // ---------------------------------------------------------------
    static boolean isMagic(long n) {
        while (n > 9) {
            n = digitSum(n);
        }
        return n == 1;
    }
    // ---------------------------------------------------------------
    // Disarium number
    // Sum of digits raised to the power of their positions equals n.
    // Example: 175 -> 1^1 + 7^2 + 5^3 = 175
    // ---------------------------------------------------------------
    static boolean isDisarium(long n) {
        long temp = n;
        int position = countDigits(n);         // last digit has highest position
        long sum = 0;
        while (temp > 0) {
            long digit = temp % 10;
            sum += (long) Math.pow(digit, position);
            position--;
            temp /= 10;
        }
        return sum == n;
    }
    // ---------------------------------------------------------------
    // Adam number
    // Square of the reverse of n equals the reverse of the square of n.
    // Example: 12 -> 144, reverse(12)=21, 21^2=441, reverse(144)=441
    // ---------------------------------------------------------------
    static boolean isAdam(long n) {
        long square = n * n;
        long revSquare = reverse(square);      // reverse of the square
        long revN = reverse(n);
        long squareOfRev = revN * revN;        // square of the reverse
        return revSquare == squareOfRev;
    }
    // ---------------------------------------------------------------
    // Automorphic number
    // The square of the number ends with the number itself.
    // Example: 25 -> 625 (ends with 25)
    // ---------------------------------------------------------------
    static boolean isAutomorphic(long n) {
        long square = n * n;
        long divisor = (long) Math.pow(10, countDigits(n));
        return square % divisor == n;          // last d digits of the square
    }
    // ---------------------------------------------------------------
    // Trimorphic number
    // The cube of the number ends with the number itself.
    // Example: 4 -> 64 (ends with 4), 24 -> 13824
    // BigInteger is used because cubes can be very large.
    // ---------------------------------------------------------------
    static boolean isTrimorphic(long n) {
        BigInteger cube = BigInteger.valueOf(n).pow(3);
        return cube.toString().endsWith(String.valueOf(n));
    }
    // ---------------------------------------------------------------
    // Neon number
    // Sum of the digits of the square equals the number.
    // Example: 9 -> 81 -> 8+1 = 9
    // ---------------------------------------------------------------
    static boolean isNeon(long n) {
        return digitSum(n * n) == n;
    }
    // ---------------------------------------------------------------
    // Unique number
    // No digit is repeated in the number.  Example: 2019 (yes), 2020 (no)
    // ---------------------------------------------------------------
    static boolean isUnique(long n) {
        boolean[] seen = new boolean[10];        // seen[d] = digit d already found?
        while (n > 0) {
            int digit = (int) (n % 10);
            if (seen[digit]) return false;       // repeated digit
            seen[digit] = true;
            n /= 10;
        }
        return true;
    }
    // ---------------------------------------------------------------
    // Krishnamurthy (Strong) number
    // Sum of the factorials of the digits equals the number.
    // Example: 145 -> 1! + 4! + 5! = 1 + 24 + 120 = 145
    // ---------------------------------------------------------------
    static boolean isKrishnamurthy(long n) {
        long temp = n, sum = 0;
        while (temp > 0) {
            sum += factorial((int) (temp % 10));
            temp /= 10;
        }
        return sum == n;
    }
    // ---------------------------------------------------------------
    // Armstrong number
    // Sum of each digit raised to the power of the digit count equals n.
    // Example: 153 -> 1^3 + 5^3 + 3^3 = 153
    // ---------------------------------------------------------------
    static boolean isArmstrong(long n) {
        int d = countDigits(n);
        long temp = n, sum = 0;
        while (temp > 0) {
            long digit = temp % 10;
            long power = 1;
            for (int i = 0; i < d; i++) power *= digit;   // digit^d
            sum += power;
            temp /= 10;
        }
        return sum == n;
    }
    // ---------------------------------------------------------------
    // Special number
    // Sum of the digits + product of the digits equals the number.
    // Example: 59 -> 5+9 = 14, 5*9 = 45, 14 + 45 = 59
    // ---------------------------------------------------------------
    static boolean isSpecial(long n) {
        long temp = n, sum = 0, product = 1;
        while (temp > 0) {
            long digit = temp % 10;
            sum += digit;
            product *= digit;
            temp /= 10;
        }
        return sum + product == n;
    }
    // ---------------------------------------------------------------
    // Happy number
    // Repeatedly replace the number by the sum of squares of its digits.
    // If it reaches 1 the number is happy; if it loops forever, it is not.
    // Example: 19 -> 82 -> 68 -> 100 -> 1
    // ---------------------------------------------------------------
    static boolean isHappy(long n) {
        while (n != 1 && n != 4) {         // every unhappy number ends in the loop containing 4
            long sum = 0;
            while (n > 0) {
                long digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            n = sum;
        }
        return n == 1;
    }
    // ---------------------------------------------------------------
    // Fascinating number
    // A number of 3 or more digits such that n, 2n and 3n joined together
    // contain every digit from 1 to 9 exactly once (and no zero).
    // Example: 192 -> 192 384 576 -> "192384576"
    // ---------------------------------------------------------------
    static boolean isFascinating(long n) {
        if (n < 100) return false;
        String joined = "" + n + (2 * n) + (3 * n);   // join n, 2n, 3n
        if (joined.length() != 9) return false;       // must be exactly 9 digits
        for (char d = '1'; d <= '9'; d++) {
            // each digit 1-9 must appear exactly once
            if (joined.indexOf(d) == -1 || joined.indexOf(d) != joined.lastIndexOf(d))
                return false;
        }
        return true;
    }
    // ---------------------------------------------------------------
    // Lynch-Bell number
    // All digits are different, none is zero, and the number is exactly
    // divisible by each of its digits.  Example: 135 (135/1, 135/3, 135/5)
    // ---------------------------------------------------------------
    static boolean isLynchBell(long n) {
        boolean[] seen = new boolean[10];
        long temp = n;
        while (temp > 0) {
            int digit = (int) (temp % 10);
            if (digit == 0 || seen[digit]) return false;   // zero or repeated digit
            if (n % digit != 0) return false;              // not divisible by digit
            seen[digit] = true;
            temp /= 10;
        }
        return true;
    }
    // ---------------------------------------------------------------
    // Palindrome number - reads the same forwards and backwards.
    // Example: 12321
    // ---------------------------------------------------------------
    static boolean isPalindrome(long n) {
        return reverse(n) == n;
    }
    // ---------------------------------------------------------------
    // Harshad (Niven) number - divisible by the sum of its digits.
    // Example: 18 -> 1+8 = 9 and 18 % 9 == 0
    // ---------------------------------------------------------------
    static boolean isHarshad(long n) {
        return n % digitSum(n) == 0;
    }
    // ---------------------------------------------------------------
    // Duck number - contains at least one zero digit
    // (a leading zero does not count).  Example: 3210, 707
    // ---------------------------------------------------------------
    static boolean isDuck(long n) {
        return String.valueOf(n).contains("0");
    }
    // ---------------------------------------------------------------
    // Buzz number - divisible by 7 OR ends with the digit 7.
    // Example: 63 (divisible by 7), 27 (ends with 7)
    // ---------------------------------------------------------------
    static boolean isBuzz(long n) {
        return n % 7 == 0 || n % 10 == 7;
    }
    // ---------------------------------------------------------------
    // Spy number - sum of digits equals product of digits.
    // Example: 1124 -> 1+1+2+4 = 8 and 1*1*2*4 = 8
    // ---------------------------------------------------------------
    static boolean isSpy(long n) {
        long sum = 0, product = 1;
        while (n > 0) {
            long digit = n % 10;
            sum += digit;
            product *= digit;
            n /= 10;
        }
        return sum == product;
    }
    // ---------------------------------------------------------------
    // Evil number
    // Its binary form contains an EVEN number of 1s.
    // Example: 3 -> 11 (two 1s) so it is evil
    // ---------------------------------------------------------------
    static boolean isEvil(long n) {
        int ones = Long.bitCount(n);             // counts 1s in binary
        return ones % 2 == 0;
    }
    // ---------------------------------------------------------------
    // Odious number
    // The count of 1s in its binary form is ODD (opposite of Evil).
    // Example: 7 = 111 (three 1s)
    // ---------------------------------------------------------------
    static boolean isOdious(long n) {
        return Long.bitCount(n) % 2 != 0;
    }
    // ---------------------------------------------------------------
    // Pernicious number
    // The count of 1s in its binary form is a prime number.
    // Example: 7 = 111 (three 1s, 3 is prime)
    // ---------------------------------------------------------------
    static boolean isPernicious(long n) {
        return isPrime(Long.bitCount(n));
    }
    // ---------------------------------------------------------------
    // Pandigital number
    // A d-digit number that uses every digit from 1 to d exactly once.
    // Example: 123, 3142, 312645
    // ---------------------------------------------------------------
    static boolean isPandigital(long n) {
        int d = countDigits(n);
        boolean[] seen = new boolean[d + 1];
        while (n > 0) {
            int digit = (int) (n % 10);
            if (digit == 0 || digit > d || seen[digit]) return false;
            seen[digit] = true;
            n /= 10;
        }
        return true;                                  // d digits, no repeats -> all of 1..d used
    }
    // ---------------------------------------------------------------
    // Bouncy number
    // Digits are neither all increasing nor all decreasing (needs 3+ digits).
    // Example: 155349 (bouncy), 1234 and 4321 are NOT bouncy.
    // ---------------------------------------------------------------
    static boolean isBouncy(long n) {
        boolean increasing = false, decreasing = false;
        long prev = n % 10;
        n /= 10;
        while (n > 0) {
            long cur = n % 10;                       // digit to the left
            if (cur < prev) increasing = true;       // reading left-to-right it goes up
            if (cur > prev) decreasing = true;       // reading left-to-right it goes down
            prev = cur;
            n /= 10;
        }
        return increasing && decreasing;
    }
    // ---------------------------------------------------------------
    // Keith number
    // Start a series with the digits of n; every next term is the sum of the
    // previous d terms (d = number of digits).  If n appears, it is a Keith number.
    // Example: 197 -> 1, 9, 7, 17, 33, 57, 107, 197  (Keith). Needs 2+ digits.
    // ---------------------------------------------------------------
    static boolean isKeith(long n) {
        int d = countDigits(n);
        if (d < 2) return false;
        long[] terms = new long[d];
        long temp = n;
        for (int i = d - 1; i >= 0; i--) {           // store digits in order
            terms[i] = temp % 10;
            temp /= 10;
        }
        long next = 0;
        while (next < n) {
            next = 0;
            for (int i = 0; i < d; i++) next += terms[i];   // sum of last d terms
            for (int i = 0; i < d - 1; i++) terms[i] = terms[i + 1];  // slide window
            terms[d - 1] = next;
            if (next == n) return true;
        }
        return false;
    }
    // ---------------------------------------------------------------
    // Kaprekar number
    // Square the number, split the square into a right part having the
    // same number of digits as n and a left part (the rest).
    // If left + right == n (and right > 0) it is a Kaprekar number.
    // Example: 45 -> 2025 -> 20 + 25 = 45
    // ---------------------------------------------------------------
    static boolean isKaprekar(long n) {
        long square = n * n;
        long divisor = (long) Math.pow(10, countDigits(n));
        long right = square % divisor;     // last d digits
        long left = square / divisor;      // remaining digits
        return right > 0 && (left + right == n);
    }
    // ---------------------------------------------------------------
    // Even number
    // Exactly divisible by 2.
    // ---------------------------------------------------------------
    static boolean isEven(long n) {
        return n % 2 == 0;
    }
    // ---------------------------------------------------------------
    // Odd number
    // Not divisible by 2 (the opposite of an even number).
    // ---------------------------------------------------------------
    static boolean isOdd(long n) {
        return n % 2 != 0;
    }
    // ---------------------------------------------------------------
    // Triangular number
    // n = 1 + 2 + 3 + ... + k.   Examples: 1, 3, 6, 10, 15
    // ---------------------------------------------------------------
    static boolean isTriangular(long n) {
        long sum = 0;
        for (long k = 1; sum < n; k++) {
            sum += k;                          // keep adding 1,2,3...
        }
        return sum == n;
    }
    /** Returns true if n is a prime number. */
    static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
    // ---------------------------------------------------------------
    // Smith number
    // A COMPOSITE number whose digit sum equals the sum of the digit
    // sums of all its prime factors (counted with repetition).
    // Example: 22 = 2 x 11 -> 2+2 = 4 and 2 + (1+1) = 4
    // ---------------------------------------------------------------
    static boolean isSmith(long n) {
        if (n < 2 || isPrime(n)) return false;   // primes are not Smith numbers
        long temp = n;
        long factorDigitSum = 0;
        for (long i = 2; i * i <= temp; i++) {
            while (temp % i == 0) {              // i is a prime factor
                factorDigitSum += digitSum(i);
                temp /= i;
            }
        }
        if (temp > 1) {                          // one prime factor left over
            factorDigitSum += digitSum(temp);
        }
        return factorDigitSum == digitSum(n);
    }
    // ---------------------------------------------------------------
    // Ugly number
    // A number whose only prime factors are 2, 3 and 5.
    // Example: 30 = 2 x 3 x 5 (ugly),  14 = 2 x 7 (not ugly)
    // By convention 1 is also an ugly number.
    // ---------------------------------------------------------------
    static boolean isUgly(long n) {
        if (n <= 0) return false;
        while (n % 2 == 0) n /= 2;    // remove all factors of 2
        while (n % 3 == 0) n /= 3;    // remove all factors of 3
        while (n % 5 == 0) n /= 5;    // remove all factors of 5
        return n == 1;                // nothing else left -> ugly
    }
    // ---------------------------------------------------------------
    // Twin prime
    // A prime p is a twin prime if p-2 or p+2 is also prime.
    // Example: 11 (11 and 13), 17 (17 and 19)
    // ---------------------------------------------------------------
    static boolean isTwinPrime(long n) {
        if (!isPrime(n)) return false;
        return isPrime(n - 2) || isPrime(n + 2);
    }
    // ---------------------------------------------------------------
    // Twisted prime
    // A prime number whose reverse is also a prime number.
    // Example: 13 -> 31 (both prime)
    // ---------------------------------------------------------------
    static boolean isTwistedPrime(long n) {
        return isPrime(n) && isPrime(reverse(n));
    }
    // ---------------------------------------------------------------
    // Circular prime
    // Every rotation of the digits is a prime number.
    // Example: 197 -> 971 -> 719 (all prime)
    // ---------------------------------------------------------------
    static boolean isCircularPrime(long n) {
        if (!isPrime(n)) return false;
        int d = countDigits(n);
        long divisor = (long) Math.pow(10, d - 1);
        long rotated = n;
        for (int i = 1; i < d; i++) {
            // move the first digit to the end
            long firstDigit = rotated / divisor;
            rotated = (rotated % divisor) * 10 + firstDigit;
            if (!isPrime(rotated)) return false;
        }
        return true;
    }
    // ---------------------------------------------------------------
    // Lucky number
    // Start with 1,2,3,...  Remove every 2nd number, then take the next
    // survivor (3) and remove every 3rd number, then the next survivor
    // (7) and remove every 7th number... Numbers that survive are lucky.
    // Lucky numbers: 1, 3, 7, 9, 13, 15, 21, 25, 31, ...
    // ---------------------------------------------------------------
    static boolean isLucky(long n) {
        int limit = (int) n;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= limit; i += 2) {   // step 1: keep only odd numbers
            list.add(i);
        }
        int index = 1;                          // list.get(1) is 3 initially
        while (index < list.size()) {
            int step = list.get(index);         // remove every 'step'-th number
            if (step > list.size()) break;      // nothing more to remove
            // remove from the back so indices stay correct
            for (int pos = (list.size() / step) * step; pos >= step; pos -= step) {
                list.remove(pos - 1);
            }
            index++;
        }
        return list.contains(limit);
    }
    // ---------------------------------------------------------------
    // Leyland number
    // A number of the form x^y + y^x where x > 1 and y > 1.
    // Example: 17 = 2^3 + 3^2, 32 = 2^4 + 4^2
    // ---------------------------------------------------------------
    static boolean isLeyland(long n) {
        for (int y = 2; Math.pow(y, 2) + Math.pow(2, y) <= n; y++) {
            for (int x = y; ; x++) {
                double value = Math.pow(x, y) + Math.pow(y, x);
                if (value > n) break;                 // too big, try next y
                if ((long) value == n) return true;
            }
        }
        return false;
    }
    static boolean isPerfect(long n) {
        return n > 1 && sumProperDivisors(n) == n;
    }
    // ---------------------------------------------------------------
    // Abundant number - sum of proper divisors is GREATER than n.
    // Example: 12 -> 1+2+3+4+6 = 16 > 12
    // ---------------------------------------------------------------
    static boolean isAbundant(long n) {
        return sumProperDivisors(n) > n;
    }
    // ---------------------------------------------------------------
    // Deficient number - sum of proper divisors is LESS than n.
    // Example: 8 -> 1+2+4 = 7 < 8
    // ---------------------------------------------------------------
    static boolean isDeficient(long n) {
        return sumProperDivisors(n) < n;
    }
    static boolean isSunny(long n) {
        return isPerfectSquare(n + 1);
    }
    // ---------------------------------------------------------------
    // Emirp number - a prime whose reverse is a DIFFERENT prime.
    // Example: 13 -> 31 (prime "spelled backwards" is emirp)
    // ---------------------------------------------------------------
    static boolean isEmirp(long n) {
        long rev = reverse(n);
        return isPrime(n) && isPrime(rev) && rev != n;
    }
    // ---------------------------------------------------------------
    // Pronic number - product of two consecutive integers k(k+1).
    // Example: 12 = 3 x 4, 20 = 4 x 5
    // ---------------------------------------------------------------
    static boolean isPronic(long n) {
        for (long k = 0; k * (k + 1) <= n; k++) {
            if (k * (k + 1) == n) return true;
        }
        return false;
    }
    // ---------------------------------------------------------------
    // Sphenic number - product of exactly three DIFFERENT primes.
    // Example: 30 = 2 x 3 x 5
    // ---------------------------------------------------------------
    static boolean isSphenic(long n) {
        int distinctPrimes = 0;
        long temp = n;
        for (long i = 2; i * i <= temp; i++) {
            if (temp % i == 0) {
                int power = 0;
                while (temp % i == 0) {          // count how often i divides
                    temp /= i;
                    power++;
                }
                if (power > 1) return false;     // a repeated prime -> not sphenic
                distinctPrimes++;
            }
        }
        if (temp > 1) distinctPrimes++;          // leftover prime factor
        return distinctPrimes == 3;
    }
    // ---------------------------------------------------------------
    // Fibonacci number - appears in 0, 1, 1, 2, 3, 5, 8, 13, 21...
    // ---------------------------------------------------------------
    static boolean isFibonacci(long n) {
        long a = 0, b = 1;
        while (a < n) {
            long next = a + b;
            a = b;
            b = next;
        }
        return a == n;
    }
    // ---------------------------------------------------------------
    // Sunny number - (n + 1) is a perfect square.
    // Example: 8 -> 9 = 3^2
    // ---------------------------------------------------------------
    static boolean isPerfectSquare(long n) {
        long root = (long) Math.sqrt(n);
        return root * root == n;
    }
    // ---------------------------------------------------------------
    // Composite number - has more than two factors (not prime, > 1).
    // Example: 9, 15, 20
    // ---------------------------------------------------------------
    static boolean isComposite(long n) {
        return n > 1 && !isPrime(n);
    }
    // ---------------------------------------------------------------
    // Goldbach number
    // An even number greater than 2 that can be written as the sum of two primes.
    // Example: 28 = 5 + 23,  10 = 3 + 7
    // ---------------------------------------------------------------
    static boolean isGoldbach(long n) {
        if (n < 4 || n % 2 != 0) return false;       // must be even and > 2
        for (long i = 2; i <= n / 2; i++) {
            if (isPrime(i) && isPrime(n - i)) return true;   // found a pair
        }
        return false;
    }
    // ---------------------------------------------------------------
    // Weird number
    // Abundant (sum of proper divisors > n) but NO group of its proper divisors
    // adds up to exactly n.  Example: 70, 836, 4030
    // ---------------------------------------------------------------
    static boolean isWeird(long n) {
        if (sumProperDivisors(n) <= n) return false;          // must be abundant
        ArrayList<Long> list = new ArrayList<>();
        for (long i = n / 2; i >= 1; i--) {                   // proper divisors, largest first
            if (n % i == 0) list.add(i);
        }
        long[] divs = new long[list.size()];
        long[] suffix = new long[list.size() + 1];
        for (int i = 0; i < divs.length; i++) divs[i] = list.get(i);
        for (int i = divs.length - 1; i >= 0; i--) suffix[i] = suffix[i + 1] + divs[i];
        return !subsetSum(divs, suffix, 0, n);                // weird if no subset works
    }
    // ---------------------------------------------------------------
    // Pell number
    // Belongs to the Pell series 0, 1, 2, 5, 12, 29, 70, 169, 408 ...
    // Each term = 2 x (previous term) + (term before that).
    // ---------------------------------------------------------------
    static boolean isPell(long n) {
        long a = 0, b = 1;
        while (a < n) {
            long next = 2 * b + a;
            a = b;
            b = next;
        }
        return a == n;
    }
    // ---------------------------------------------------------------
    // PalPrime (palindromic prime)
    // A prime number that is also a palindrome.  Example: 11, 101, 131, 313
    // ---------------------------------------------------------------
    static boolean isPalPrime(long n) {
        return isPrime(n) && reverse(n) == n;
    }
    // ---------------------------------------------------------------
    // Mersenne prime
    // A prime number of the form 2^p - 1.  Example: 3, 7, 31, 127
    // ---------------------------------------------------------------
    static boolean isMersennePrime(long n) {
        boolean oneLessThanPowerOf2 = ((n + 1) & n) == 0;   // n looks like 111...1 in binary
        return oneLessThanPowerOf2 && isPrime(n);
    }
    // ---------------------------------------------------------------
    // Sophie Germain prime
    // A prime p such that 2p + 1 is also prime.  Example: 11 (2x11+1 = 23)
    // ---------------------------------------------------------------
    static boolean isSophieGermain(long n) {
        return isPrime(n) && isPrime(2 * n + 1);
    }
    // ---------------------------------------------------------------
    // Semiprime
    // Product of exactly two primes (the primes may be equal).
    // Example: 15 = 3 x 5,  49 = 7 x 7
    // ---------------------------------------------------------------
    static boolean isSemiprime(long n) {
        int count = 0;
        long temp = n;
        for (long i = 2; i * i <= temp; i++) {
            while (temp % i == 0) {                  // count prime factors with repetition
                temp /= i;
                count++;
            }
        }
        if (temp > 1) count++;
        return count == 2;
    }
    // ---------------------------------------------------------------
    // Perfect Cube number
    // Cube of an integer.  Example: 27 = 3^3, 64 = 4^3
    // ---------------------------------------------------------------
    static boolean isPerfectCube(long n) {
        long root = Math.round(Math.cbrt(n));
        return root * root * root == n;
    }

    // ---------------------------------------------------------------
    // Menu display
    // ---------------------------------------------------------------
    static void showMenu() {
        System.out.println("\n============ NUMBER CHECKER MENU ============");
        System.out.println(" 1. Technical Number");
        System.out.println(" 2. Magic Number");
        System.out.println(" 3. Disarium Number");
        System.out.println(" 4. Adam Number");
        System.out.println(" 5. Automorphic Number");
        System.out.println(" 6. Trimorphic Number");
        System.out.println(" 7. Neon Number");
        System.out.println(" 8. Unique Number");
        System.out.println(" 9. Krishnamurthy Number");
        System.out.println("10. Armstrong Number");
        System.out.println("11. Special Number");
        System.out.println("12. Happy Number");
        System.out.println("13. Fascinating Number");
        System.out.println("14. Lynch-Bell Number");
        System.out.println("15. Palindrome Number");
        System.out.println("16. Harshad Number");
        System.out.println("17. Duck Number");
        System.out.println("18. Buzz Number");
        System.out.println("19. Spy Number");
        System.out.println("20. Evil (OneEven) Number");
        System.out.println("21. Odious Number");
        System.out.println("22. Pernicious Number");
        System.out.println("23. Pandigital Number");
        System.out.println("24. Bouncy Number");
        System.out.println("25. Keith Number");
        System.out.println("26. Kaprekar Number");
        System.out.println("27. Even Number");
        System.out.println("28. Odd Number");
        System.out.println("29. Triangular Number");
        System.out.println("30. Prime Number");
        System.out.println("31. Smith Number");
        System.out.println("32. Ugly Number");
        System.out.println("33. Twin Prime Number");
        System.out.println("34. Twisted Prime Number");
        System.out.println("35. Circular Prime Number");
        System.out.println("36. Lucky Number");
        System.out.println("37. Leyland (Lead) Number");
        System.out.println("38. Perfect Number");
        System.out.println("39. Abundant Number");
        System.out.println("40. Deficient Number");
        System.out.println("41. Sunny Number");
        System.out.println("42. Emirp Number");
        System.out.println("43. Pronic Number");
        System.out.println("44. Sphenic Number");
        System.out.println("45. Fibonacci Number");
        System.out.println("46. Perfect Square Number");
        System.out.println("47. Composite Number");
        System.out.println("48. Goldbach Number");
        System.out.println("49. Weird Number");
        System.out.println("50. Pell Number");
        System.out.println("51. PalPrime Number");
        System.out.println("52. Mersenne Prime Number");
        System.out.println("53. Sophie Germain Prime Number");
        System.out.println("54. Semiprime Number");
        System.out.println("55. Perfect Cube Number");
        System.out.println("=============================================");
    }

    /** Prints the final message for the user. */
    static void printResult(long n, String name, boolean result) {
        // "an" before vowel sounds (an Adam, an Even, an Ugly number) else "a"
        String article = ("AEIO".indexOf(name.charAt(0)) >= 0 || name.startsWith("Ugly")) ? "an" : "a";
        if (result)
            System.out.println(">> " + n + " is " + article + " " + name + " number.");
        else
            System.out.println(">> " + n + " is NOT " + article + " " + name + " number.");
    }

    // ---------------------------------------------------------------
    // main - drives the whole menu loop
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char again;

        do {
            showMenu();
            System.out.print("Enter your choice (1-55): ");
            int choice = sc.nextInt();

            if (choice < 1 || choice > 55) {
                System.out.println("Invalid choice! Please select from 1 to 55.");
            } else {
                System.out.print("Enter a positive number (up to 9 digits): ");
                long n = sc.nextLong();

                if (n <= 0 || n > 999999999L) {
                    System.out.println("Please enter a positive number up to 9 digits.");
                } else {
                    // call the function that matches the user's choice
                    switch (choice) {
                        case 1 -> printResult(n, "Technical", isTechnical(n));
                        case 2 -> printResult(n, "Magic", isMagic(n));
                        case 3 -> printResult(n, "Disarium", isDisarium(n));
                        case 4 -> printResult(n, "Adam", isAdam(n));
                        case 5 -> printResult(n, "Automorphic", isAutomorphic(n));
                        case 6 -> printResult(n, "Trimorphic", isTrimorphic(n));
                        case 7 -> printResult(n, "Neon", isNeon(n));
                        case 8 -> printResult(n, "Unique", isUnique(n));
                        case 9 -> printResult(n, "Krishnamurthy", isKrishnamurthy(n));
                        case 10 -> printResult(n, "Armstrong", isArmstrong(n));
                        case 11 -> printResult(n, "Special", isSpecial(n));
                        case 12 -> printResult(n, "Happy", isHappy(n));
                        case 13 -> printResult(n, "Fascinating", isFascinating(n));
                        case 14 -> printResult(n, "Lynch-Bell", isLynchBell(n));
                        case 15 -> printResult(n, "Palindrome", isPalindrome(n));
                        case 16 -> printResult(n, "Harshad", isHarshad(n));
                        case 17 -> printResult(n, "Duck", isDuck(n));
                        case 18 -> printResult(n, "Buzz", isBuzz(n));
                        case 19 -> printResult(n, "Spy", isSpy(n));
                        case 20 -> printResult(n, "Evil (OneEven)", isEvil(n));
                        case 21 -> printResult(n, "Odious", isOdious(n));
                        case 22 -> printResult(n, "Pernicious", isPernicious(n));
                        case 23 -> printResult(n, "Pandigital", isPandigital(n));
                        case 24 -> printResult(n, "Bouncy", isBouncy(n));
                        case 25 -> printResult(n, "Keith", isKeith(n));
                        case 26 -> printResult(n, "Kaprekar", isKaprekar(n));
                        case 27 -> printResult(n, "Even", isEven(n));
                        case 28 -> printResult(n, "Odd", isOdd(n));
                        case 29 -> printResult(n, "Triangular", isTriangular(n));
                        case 30 -> printResult(n, "Prime", isPrime(n));
                        case 31 -> printResult(n, "Smith", isSmith(n));
                        case 32 -> printResult(n, "Ugly", isUgly(n));
                        case 33 -> printResult(n, "Twin Prime", isTwinPrime(n));
                        case 34 -> printResult(n, "Twisted Prime", isTwistedPrime(n));
                        case 35 -> printResult(n, "Circular Prime", isCircularPrime(n));
                        case 36 -> printResult(n, "Lucky", isLucky(n));
                        case 37 -> printResult(n, "Leyland (Lead)", isLeyland(n));
                        case 38 -> printResult(n, "Perfect", isPerfect(n));
                        case 39 -> printResult(n, "Abundant", isAbundant(n));
                        case 40 -> printResult(n, "Deficient", isDeficient(n));
                        case 41 -> printResult(n, "Sunny", isSunny(n));
                        case 42 -> printResult(n, "Emirp", isEmirp(n));
                        case 43 -> printResult(n, "Pronic", isPronic(n));
                        case 44 -> printResult(n, "Sphenic", isSphenic(n));
                        case 45 -> printResult(n, "Fibonacci", isFibonacci(n));
                        case 46 -> printResult(n, "Perfect Square", isPerfectSquare(n));
                        case 47 -> printResult(n, "Composite", isComposite(n));
                        case 48 -> printResult(n, "Goldbach", isGoldbach(n));
                        case 49 -> printResult(n, "Weird", isWeird(n));
                        case 50 -> printResult(n, "Pell", isPell(n));
                        case 51 -> printResult(n, "PalPrime", isPalPrime(n));
                        case 52 -> printResult(n, "Mersenne Prime", isMersennePrime(n));
                        case 53 -> printResult(n, "Sophie Germain Prime", isSophieGermain(n));
                        case 54 -> printResult(n, "Semiprime", isSemiprime(n));
                        case 55 -> printResult(n, "Perfect Cube", isPerfectCube(n));
                    }
                }
            }

            // ask whether the user wants to continue or exit
            System.out.print("\nDo you want to continue? (y = yes / n = exit): ");
            again = sc.next().charAt(0);

        } while (again == 'y' || again == 'Y');

        System.out.println("Thank you! Program ended.");
      
    }
}