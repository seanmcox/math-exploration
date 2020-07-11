# Lychrel Numbers
## Problem Description
The Lychrel Number problem relates to an algorithm often referred to as Digit Reversal and Sum.

The basic algorithm consists of taking a number, inverting the order of its digits and summing the number thus created with the original.

For my purposes, I define two processes:  
r(n): Returns the digit reversal of n.
s(n): return n+r(n). This is the Digit Reversal and Sum algorithm.

eg.
s(12340) = 12340 + 4321 = 16661

This example resulted in a number which is a palindrome. However, not all numbers result in palindromes. 

eg.
s(45670) = 45670 + 7654 = 53324

If one takes all resulting numbers that aren't palindromes and repeatedly applies the Digit Reversal and Sum algorithm to them until a palindrome is produced, usually the series of numbers generated is very short.
However, there are some numbers which seem to never terminate, and it is not known if they will ever terminate. In some other bases there are series which have been proven to never terminate.

See:
* ["Lychrel number", Wikipedia](https://en.wikipedia.org/wiki/Lychrel_number)
* ["196-Algorithm", Wolfram Mathworld](https://mathworld.wolfram.com/196-Algorithm.html)

## Definitions
r(x) = the digit reversal of x.<br/>
d(x) = the digital root of x.<br/>
s(x) = x + r(x) (referred to as the next number from x)<br/>
s'(x) = s(s(x)) = s_2(x)<br/>
Lychrel Stop: Palindrome number<br/>
Lychrel Number: Number, x, for which no s_n(x) (where n>0) is a Lychrel stop.<br/>

*For any number, x, s(x) is sometimes simply referred to as the "next" number.*
	
### Interesting patterns in the infinite base
Pa(n,p) = 2^n(, 0)xp, b-2^n, b-1, b-2^n-1(, b-1)xp, 2^n-1 *(Never a palindrome.)*<br/>
s(Pa(n,p)) = Pa(n+1,p)<br/>
Pa(0,1) Arises naturally from N=b-1, b-1, b-1, b-1 in 3 steps, in bases that can get that far (ie. b>=4).<br/>

Pb(n,p) = 2^n, 2^n(, 0)xp, b-2^n, b-2^n-1, b-2^n-1, b-2^n-1(, b-1)xp, 2^n-1, 2^n *(Never a palindrome.)*<br/>
s(Pb(n,p)) = Pb(n+1,p)<br/>
Arises naturally in bases of 2^n starting from N=b-1, b-1, b-1, b-1.<br/>

Pc(n,p) = 2^n, 2^n(, 0)xp, b-2^n, b-2^(n+1)-1, b-2^n-1(, b-1)xp, 2^n-1, 2^n *(Never a palindrome.)*<br/>
s(Pc(n,p)) = Pc(n+1,p)<br/>
Arises naturally in bases of 2^n starting from N=b-1, b-1, b-1, b-1.<br/>

#### Some more general patterns based on these patterns
Pag(n,p,q) = (2^n,)xq 2^n(, 0)xp, b-2^n, b-1, b-2^n-1(, b-1)xp, 2^n-1(, 2^n)xq *(Never a palindrome.)*<br/>
s(Pag(n,p,q)) = Pag(n+1,p,q)<br/>

Pbg(n,p,q) = (2^n,)xq 2^n(, 0)xp, b-2^n, b-2^n-1, b-2^n-1, b-2^n-1(, b-1)xp, 2^n-1(, 2^n)xq *(Never a palindrome.)*<br/>
s(Pbg(n,p,q)) = Pbg(n+1,p,q)<br/>

Pcg(n,p,q) = (2^n,)xq 2^n(, 0)xp, b-2^n, b-2^(n+1)-1, b-2^n-1(, b-1)xp, 2^n-1(, 2^n)xq *(Never a palindrome.)*<br/>
s(Pcg(n,p,q)) = Pcg(n+1,p,q)<br/>

Pcg'(n,p,q,r) = (2^n,)xq 2^n(, 0)xp, b-2^n, b-2^(n+r)-1, b-2^n-1(, b-1)xp, 2^n-1(, 2^n)xq *(Never a palindrome.)*<br/>
s(Pcg'(n,p,q,r)) = Pcg'(n+1,p,q,r)<br/>

## Theorems

### Theorem 1
In base 10, for all numbers, x, that have a digital root of 9, s(x) will also have a digital root of 9.<br/>
*(ie. if d(x) = 9 then d(s(x)) = 9)*

**Status:** Proven
	
**TODO:** Generalize to other bases.
	
#### Proof
This is because all numbers divisible by 9, and only numbers divisible by 9 have a digital root of 9.<br/>
Since the reverse of a number divisible by 9 has the same digits, it also has a digital root of 9, and is hence divisible by 9.<br/>
Adding any two numbers divisible by a common factor will also yield a number divisible by that same common factor (eg. a`*`c + b`*`c = (a+b)`*`c), hence the result will be divisible by 9.<br/> 

### Theorem 2
In base 10, all numbers that have a given digital root, n, will have next numbers that have the root digit sum equal to the digital root of 2*n.<br/>
*(ie. if d(x) = n then d(s(x)) = d(2`*`n))*

**Status:** Proven
	
**TODO:** Generalize to other bases.
 
#### Proof
The digital root, d, of any base 10 number n is known to be equal to n%9.<br/>
Hence, when a number is added to its reverse (which has the same digit sum), the new remainder will be the remainder of the sum of the two previous remainders (both the same), divided by 9,<br/>
which is the same as the digital root of the sum of the two remainders (2*d).<br/>

#### Notes
There are, hence, 3 basic patterns:<br/>
3,6,3,6,3,6,3,6,3,6,3,6<br/>
9,9,9,9,9,9,9,9,9,9,9,9<br/>
1,2,4,8,7,5,1,2,4,8,7,5<br/>
	
Every Lychrel seed has a digital root in one of these three patterns and its' series can never merge with the series of a lychrel seed with a digital root in a different pattern. 

### Theorem 3
In base 10, all numbers that are divisible by 11 will have next numbers that are divisible by 11.<br/>
*(ie. if x%11 = 0 then s(x)%11 = 0)*

**Status:** Proven
	
**TODO:** Generalize to other bases.
	
#### Proof
Since divisibility by 11 can be tested by adding and subtracting each digit alternately in series (ie. 1234 => 1-2+3-4 = -2) and then testing to see if the result is divisible by 11, it follows
that if a given number is divisible by 11, then its reverse will also be divisible by 11 (its reverse will have either the same result for the test or the negative version of the result).
	
Since a`*`11 + b`*`11 = (a+b)`*`11, the next number will be a multiple of 11.

### Theorem 4
In base 10, if x has an even number of digits then the next number is a multiple of 11.

**Status:** Proven
	
**TODO:** Generalize to other bases.
	
**Source for Idea:** https://mathoverflow.net/questions/117104/status-of-the-196-conjecture/117277#117277
	
#### Proof
**No carry:**<br/>
AB...CD + DC...BA = (A+D), (B+C), ..., (B+C), (A+D) = a, b, ..., b, a<br/>
=> a - b + - + b - a = a - a + b - b + c - c ... = 0 => multiple of 11
	
**With carry:**<br/>
If we presume A + D carries, then:<br/>
1, a, b, ..., b+1, a<br/>
=> 1 - a + b - + - (b+1) + a = (1 + b) - (b + 1) + a - a<br/>
Effectively, all of the digits, ignoring the carries will oppose their equal values on the opposite side of the number the same as without carries, but
the carries, since they create equal values both moved one digit to the left, will still oppose each other, because their contributions to the overall
divisibility by 11 test both flip.

### Theorem 5
In base 10,	for x = 11 `*` n, r(x) = 11 `*` r(f(n)) ( => s(x) = 11`*`(n+r(f(n))) )
	
Where f(x) operates on the digits of a number moving from right to left. (Here, x[0] is the digit furthest to the right)

	f(x) = for(i = 0;i<x.length;i++){
	    if(i == x.length - 1){
	        if(x[i]==10){
	        	x[i]--;
	        	push x, 1;
	        	break;
	        }
	    }
	    else if(x[i]+x[i+1]>=10){
	        x[i]--;
	        x[i+1]++;
	    }
	}

**Status:** Proven
	
**TODO:** Generalize to other bases.
	
#### Examples
    1122 = 11*102
    2211 = 11*201
    1353 = 11*123
    3531 = 11*321
    8679 = 11*789
    9768 = 11*888
    2101 = 11*191
    1012 = 11*92  (r(n)=191)
    1991 = 11*181
    1991 = 11*181 (r(n)=181)
    2002 = 11*182
    2002 = 11*182 (r(n)=281)
    2013 = 11*183
    3102 = 11*282 (r(n)=381)
    11913 = 11*1083
    31911 = 11*2901 (r(n)=3801)
    11902 = 11*1082
    20911 = 11*1901 (r(n)=2801)
    13002 = 11*1182
    20031 = 11*1821 (r(n)=2811)
    11209 = 11*1019
    90211 = 11*8201 (r(n)=9101)
    100111 = 11*9101
    111001 = 11*10091 (r(n)=1019)

#### Proof
Without carry:

    ..., L, M, N, ... * 11 = ..., K+L, L+M, M+N, N+O, ...
    => ..., N, M, L, ... * 11 = ..., N+O, M+N, L+M, K+L, ...

With M+N carry, and L+M < 9:

    ..., L, M, N, ... * 11 = ..., K+L, L+M+1, (M+N)%10, N+O, ...
    ..., N-1, M+1, L, ... * 11 = ..., N+O, (M+N)%10, L+M+1, K+L, ...

With M+N carry, and L+M = 9:

    ..., L, M, N, ... * 11 = ..., K+L+1, 0, (M+N)%10, N+O, ...
    ..., N-1, M, L+1, ... * 11 = ..., N+O, (M+N)%10, 0, K+L+1, ...

With M+N carry, and L+M > 9:

    ..., L, M, N, ... * 11 = ..., K+L+1, (L+M+1)%10, (M+N)%10, N+O, ...
    ..., N-1, M, L+1, ... * 11 = ..., N+O, (M+N)%10, (L+M+1)%10, K+L+1, ...Seems
    
This even holds when L is, say, beyond the end of the number. *(ie. boundary condition)*<br/>
With M+N carry, and M < 9:

    M, N, ... * 11 = M+1, (M+N)%10, N+O, ...
    ..., N-1, M+1 * 11 = ..., N+O, (M+N)%10, M+1

With M+N carry, and M = 9:

    9, N, ... * 11 = 1, 0, (9+N)%10, N+O, ...
    ..., N-1, 9, 1 * 11 = ..., N+O, (9+N)%10, 0, 1

With M+N carry, and M > 9, is not a thing.

### Theorem 6
For base b=infinity, the number N = b-1, b-1, b-1, b-1 never creates a palindrome.
	
**Status:** Proven

#### Proof
Define N(n+1) = s(N(n))
	
	Define N(-3) = b-1, b-1, b-1, b-1
	=> N(-2) = 1, b-1, b-1, b-1, b-2
	=> N(-1) = 1, 0, b-1, b-1, b-2, b-1 
	=> N(0) = 1, 0, b-1, b-1, b-2, b-1, 0 = Pa(0,1) = (for n=0) 2^n, 0, b-2^n, b-1, b-2^n-1, b-1, 2^n-1

if N(n) = 2^n, 0, b-2^n, b-1, b-2^n-1, b-1, 2^n-1<br/>
then N(n+1) =  2^(n+1), 0, b-2^(n+1), b-1, b-2^(n+1)-1, b-1, 2^(n+1)-1<br/>
This is the exact same form. Ergo, every subsequent N will have this form.<br/>
2^n != 2^n-1 => Never a palindrome.<br/>
0 != b-1 => Never a palindrome.<br/>
b-2^(n+1) != b-2^(n+1)-1 => Never a palindrome.<br/>

### Theorem 7
For base b=2^(i+1), where n>0, the number N = b-1, b-1, b-1, b-1 never creates a palindrome.
	
**Status:** Proven

#### Proof
For base b = 2^(i+1)

	N'(1) = 2^n, 0, b-2^n, b-1, b-2^n-1, b-1, 2^n-1 (See: N(0) from the previous proof.)
	N'(2) = 1, 0, 0, 0, b-2, b-1, b-1, b-1
	N'(3) = 1, 0, 0, 0, b-2, b-1, b-1, b-1
	N'(4) = 1, 0, b-1, b-1, b-2, b-1, 0, 0, 0
	N'(5) = 1, 1, 0, b-1, b-3, b-2, b-1, 0, 1 = (for n'=0) 2^n', 2^n', 0, b-2^n', b-2^(n'+1)-1, b-2^n'-1,b-1,2^n'-1,2^n' 
	N'(6) = 2, 2, 0, b-2, b-5, b-3, b-1, 1, 2 = 2^(n'+1), 2^(n'+1), 0, b-2^(n'+1), b-2^(n'+2)-1, b-2^(n'+1)-1, b-1, 2^(n'+1)-1, 2^(n'+1) => Since this is the same form, all following numbers will also have this form until carry occurs and none of those numbers will be palindromes.
	N'(7) = 4, 4, 0, b-4, b-9, b-5, b-1, 3, 4
	N'(8) = 8, 8, 0, b-8, b-17, b-9, b-1, 7, 8
	
For m'=i-1

	N''(1) = 2^(i-1), 2^(i-1), 0, b-2^(i-1), b-2^i-1, b-2^(i-1)-1,b-1,2^(i-1)-1,2^(i-1) = N'(m')
	N''(2) = 2^i, 2^i, 0, b-2^i-1, b-1, b-2^i-1,b-1,2^i-1,2^i = s(N'(m'))
	N''(3) = 1, 0, b-1, b-1, b-1, b-2, b-1, 0, 0, 0 = s''(N'(m'))
	N''(4) = 1, 1, 0, b-1, b-2, b-2, b-2, b-1, 0, 1 = (for n''=0) 2^n'', 2^n'', 0, b-2^n'', b-2^n''-1, b-2^n''-1, b-2^n''-1, b-1, 2^n''-1, 2^n''
	N''(5) = 2, 2, 0, b-2, b-3, b-3, b-3, b-1, 1, 2 = 2^(n''+1), 2^(n''+1), 0, b-2^(n''+1), b-2^(n''+1)-1, b-2^(n''+1)-1, b-2^(n''+1)-1, b-1, 2^(n''+1)-1, 2^(n''+1)
	N''(6) = 4, 4, 0, b-4, b-5, b-5, b-5, b-1, 3, 4
	
For m''=i

	N'''(1) = 2^i, 2^i, 0, b-2^i, b-2^i-1, b-2^i-1, b-2^i-1, b-1, 2^i-1, 2^i = N''(m'')
	N'''(2) = 1, 0, b-1, b-1, b-1, b-2, b-1, 0, 0, 0, 0 = s(N''(m''))
	N'''(3) = 1, 1, 0, 0, b-1, b-3, b-2, b-1, b-1, 0, 1 = (for n'''=0) 2^n''', 2^n''', 0, 0, b-2^n''', b-2^(n'''+1)-1, b-2^n'''-1, b-1, b-1, 2^n'''-1, 2^n'''
	N'''(4) = 2, 2, 0, 0, b-2, b-5, b-3, b-1, b-1, 1, 2 = 2^(n'''+1), 2^(n'''+1), 0, 0, b-2^(n'''+1), b-2^(n'''+2)-1, b-2^(n'''+1)-1, b-1, b-1, 2^(n'''+1)-1, 2^(n'''+1)
	N'''(5) = 4, 4, 0, 0, b-4, b-9, b-5, b-1, b-1, 3, 4
	
For m''' = i-1

	N''''(1) = 2^(i-1), 2^(i-1), 0, 0, b-2^(i-1), b-2^i-1, b-2^(i-1)-1, b-1, b-1, 2^(i-1)-1, 2^(i-1) = N'''(m''')
	N''''(2) = 2^i, 2^i, 0, 0, b-2^i-1, b-1, b-2^i-1, b-1, b-1, 2^i-1, 2^i = s(N'''(m'''))
	N''''(3) = 1, 0, b-1, b-1, b-1, b-1, b-2, b-1, 0, 0, 0, 0 = s'(N'''(m'''))
	N''''(4) = 1, 1, 0, 0, b-1, b-2, b-2, b-2, b-1, b-1, 0, 1 = (for n''''=0) 2^n'''', 2^n'''', 0, 0, b-2^n'''', b-2^n''''-1, b-2^n''''-1, b-2^n''''-1, b-1, b-1, 2^n''''-1, 2^n''''
	N''''(5) = 2, 2, 0, 0, b-2, b-3, b-3, b-3, b-1, b-1, 1, 2
	
Define P(m,p) = 2^m, 2^m, (0,)xp b-2^m, b-2^m-1, b-2^m-1, b-2^m-1, (b-1,)xp 2^m-1, 2^m *(Never a palindrome.)*<br/>
Define P'(m,p') = 2^m, 2^m, (0,)xp' b-2^m, b-2^(m+1)-1, b-2^m-1, (b-1,)xp' 2^m-1, 2^m *(Never a palindrome.)*<br/>
	
For m<i

	P(m,p) = 2^m, 2^m, (0,)xp b-2^m, b-2^m-1, b-2^m-1, b-2^m-1, (b-1,)xp 2^m-1, 2^m
	=> s(P(m,p)) = 2^(m+1), 2^(m+1), (0,)xp b-2^(m+1), b-2^(m+1)-1, b-2^(m+1)-1, b-2^(m+1)-1, (b-1,)xp 2^(m+1)-1, 2^(m+1) = P(m+1,p)
	
For m<i-1

	P'(m,p') = 2^m, 2^m, (0,)xp' b-2^m, b-2^(m+1)-1, b-2^m-1, (b-1,)xp' 2^m-1, 2^m
	=> s(P'(m,p')) = 2^(m+1), 2^(m+1), (0,)xp' b-2^(m+1), b-2^(m+2)-1, b-2^(m+1)-1, (b-1,)xp' 2^(m+1)-1, 2^(m+1) = P'(m+1,p')
	
With P(m,p), as soon as m=i, we have:

	P(m,p) = P(i,p) = 2^i, 2^i, (0,)xp b-2^i, b-2^i-1, b-2^i-1, b-2^i-1, (b-1,)xp 2^i-1, 2^i
	s(P(i,p)) = 1, 0, b-1, (b-1,)xp b-1, b-2, b-1, 0, (0,)xp 0, 0 (Never a palindrome.)
	s'(P(i,p)) = 1, 1, 0, (0,)xp b-1, b-3, b-2, b-1, (b-1,)xp 0, 1 = P'(0,p+1)

With P'(m,p'), as soon as m=i-1, we have:

	P'(m,p') = P'(i-1,p') = 2^(i-1), 2^(i-1), (0,)xp' b-2^(i-1), b-2^i-1, b-2^(i-1)-1, (b-1,)xp' 2^(i-1)-1, 2^(i-1)
	s(P'(i-1,p')) = 2^i, 2^i, (0,)xp' b-2^i-1, b-1, b-2^i-1, (b-1,)xp' 2^i-1, 2^i (Never a palindrome.)
	s'(P'(i-1,p')) = 1, 0, b-1, (b-1,)xp' b-1, b-2, b-1, (0,)xp' 0, 0 (Never a palindrome.)
	s''(P'(i-1,p')) = 1, 1, (0,)xp', b-1 b-2, b-2, b-2, (b-1,)xp' 0, 1 = P(0,p')

=> We have the following pattern for all i>0:<br/>
P'(0,y) ... P'(i-1,y) ... P(0,y) ... P(i,y) ... P'(0,y+1) ...
	
N'(5) = P'(0,1):<br/>
=> for i>0 the s_x(N) will always fall into the following: P'(0,1) ... P'(i-1,1) ... P(0,1) ... P(i,1) ... P'(0,2) ...
	
Therefore for all bases b = 2^(i+1) where i>0, N = b-1,b-1,b-1,b-1 is a Lychrel number. (eg. not base 2, but bases 4, 8, 16, 32, etc.)

### Theorem 8
Every base b=2^n-1 where n>=7 will reach a palindrome, starting from N = b-1, b-1, b-1, b-1 in m steps where m=n*5+3.

**Status:** Proven

#### Proof
Pa(0,1) is reached in 3 steps. (See: Theorem 6 proof)
	
for b = 2^n-1

	Pa(n-1,1) = 2^(n-1), 0, b-2^(n-1), b-1, b-2^(n-1)-1, b-1, 2^(n-1)-1
	s(Pa(n-1,1)) = 1, 0, b-1, b-1, b-2, b-1, 0, 0
	s'(Pa(n-1,1)) = 1, 1, b-1, b-2, b-2, b-2, 0, 1 = Pb(0,0) (reached in n+1 more steps. total n+4)
	s''(Pa(n-1,1)) = 2, 2, b-2, b-3, b-3, b-3, 1, 2
	s'''(Pa(n-1,1)) = 4, 4, b-4, b-5, b-5, b-5, 3, 4
	
	Pb(n-1,0) = 2^(n-1), 2^(n-1), b-2^(n-1), b-2^(n-1)-1, b-2^(n-1)-1, b-2^(n-1)-1, 2^(n-1)-1, 2^(n-1)
	s(Pb(n-1,0)) = 1, 2, 0, b-2, b-3, b-3, b-1, 1, 1 = N'(n'=0) 2^n', 2^(n'+1), 0, b-2^(n'+1), b-2^(n'+1)-1, b-2^(n'+1)-1, b-1, 2^(n'+1)-1, 2^n' (reached in n more steps. total 2n+4) 
	s'(Pb(n-1,0)) = 2, 4, 0, b-4, b-5, b-5, b-1, 3, 2 = 2^(n'+1), 2^(n'+2), 0, b-2^(n'+2), b-2^(n'+2)-1, b-2^(n'+2)-1, b-1, 2^(n'+2)-1, 2^(n'+1)
	
    N'(n-2) = 2^(n-2), 2^(n-1), 0, b-2^(n-1), b-2^(n-1)-1, b-2^(n-1)-1, b-1, 2^(n-1)-1, 2^(n-2) (reached in n-2 more steps. total 3n+2)
    s(N'(n-2)) = 2^(n-1)+1, 0, b-1, b-2, b-3, b-1, 0, 0, 2^(n-1)
    s'(N'(n-2)) = 1, 2, 1, 0, b-2, b-5, b-3, b-1, 1, 2
    s''(N'(n-2)) = 3, 4, 0, b-2, b-6, b-7, b-2, 0, 3, 3
    s'''(N'(n-2)) = 6, 7, 1, b-3, b-12, b-12, b-4, 0, 7, 6 = N''(n''=0) = 6*2^n'', 7*2^n'', 2^n'', b-3*2^n'', b-11*2^n''-1, b-11*2^n''-1, b-3*2^n''-1, 2^n''-1, 7*2^n'', 6*2^n'' (reached in 4 more steps. total 3n+6)
    s''''(N'(n-2)) = 12, 14, 2, b-6, b-23, b-23, b-7, 1, 14, 12
	
    N''(n-4) = (2+4)*2^(n-4), (1+2+4)*2^(n-4), 2^(n-4), b-(1+2)*2^(n-4), b-(1+2+8)*2^(n-4)-1, b-(1+2+8)*2^(n-4)-1, b-(1+2)*2^(n-4)-1, 2^(n-4)-1, (1+2+4)*2^(n-4), (2+4)*2^(n-4) (reached in n-4 more steps. total 4n+2)
    s(N''(n-4)) = (2+4)*2^(n-3), (1+2+4)*2^(n-3), 2^(n-3), b-(1+2)*2^(n-3)-1, b-(1+2)*2^(n-3)-3, b-(1+2)*2^(n-3)-2, b-(1+2)*2^(n-3)-1, 2^(n-3)-1, (1+2+4)*2^(n-3), (2+4)*2^(n-3)
    s'(N''(n-4)) = 1, (2)*2^(n-2)+2, (1+2)*2^(n-2)+1, 2^(n-2), b-(1+2)*2^(n-2)-1, b-(1+2)*2^(n-2)-4, b-(1+2)*2^(n-2)-4, b-(1+2)*2^(n-2)-2, 2^(n-2), (1+2)*2^(n-2)+2, (2)*2^(n-2)+1
    s''(N''(n-4)) = 2*2^(n-2)+3, (1)*2^(n-2)+6, 2, b-2*2^(n-2)-2, b-(2)*2^(n-2)-6, b-(2)*2^(n-2)-9, b-(2)*2^(n-2)-6, b-2*2^(n-2)-1, 3, (1)*2^(n-2)+5, 2*2^(n-2)+2
    s'''(N''(n-4)) = 1, 6, 2^(n-1)+11, 5, b-4, b-13, b-19, b-13, b-4, 5, 2^(n-1)+12, 6
    s''''(N''(n-4)) = 7, 2^(n-1)+18, 2^(n-1)+17, 2, b-16, b-31, b-31, b-16, 1, 2^(n-1)+16, 2^(n-1)+18, 7
    s'''''(N''(n-4)) = 15, 38, 34, 4, b-31, b-61, b-61, b-32, 4, 35, 37, 14    
    s''''''(N''(n-4)) = 29, 75, 69, 9, b-62, b-121, b-121, b-63, 8, 69, 75, 29 = N'''(n'''=0) = 29*2^n''', 75*2^n''', 69*2^n''', 9*2^n''', b-62*2^n''', b-120*2^n'''-1, b-120*2^n'''-1, b-62*2^n'''-1 , 9*2^n'''+1, 69*2^n''', 75*2^n''', 29*2^n'''  (reached in 7 more steps. total 4n+9) (verified)
    s'''''''(N''(n-4)) = 58, 150, 138, 18, b-124, b-241, b-241, b-125, 17, 138, 150, 58
	
    N'''(n-7) = (1+4+8+16)*2^(n-7), (1+2+8+64)*2^(n-7), (1+4+64)*2^(n-7), (1+8)*2^(n-7), b-(2+4+8+16+32)*2^(n-7), b-(8+16+32+64)*2^(n-7)-1, b-(8+16+32+64)*2^(n-7)-1, b-(2+4+8+16+32)*2^(n-7)-1, (1+8)*2^(n-7)+1, (1+4+64)*2^(n-7), (1+2+8+64)*2^(n-7), (1+4+8+16)*2^(n-7) (reached in n-7 more steps. total 5n+2)
    s(N'''(n-7)) = (1+4+8+16)*2^(n-6)+1, (1+2+8)*2^(n-6)+2, (1+4)*2^(n-6)+1, (1+8)*2^(n-6)+2, b-(2+4+8+16+32)*2^(n-6)-1, b-(8+16+32)*2^(n-6)-3, b-(8+16+32)*2^(n-6)-2, b-(2+4+8+16+32)*2^(n-6)-1, (1+8)*2^(n-6)+2, (1+4)*2^(n-6)+2, (1+2+8)*2^(n-6)+1, (1+4+8+16)*2^(n-6)
    s'(N'''(n-7)) = (1+4+8+16)*2^(n-5)+1, (1+2+8)*2^(n-5)+3, (1+4)*2^(n-5)+3, (1+8)*2^(n-5)+4, b-(2+4+8+16)*2^(n-5)-3, b-(8+16)*2^(n-5)-6, b-(8+16)*2^(n-5)-6, b-(2+4+8+16)*2^(n-5)-3, (1+8)*2^(n-5)+4, (1+4)*2^(n-5)+3, (1+2+8)*2^(n-5)+3, (1+4+8+16)*2^(n-5)+1 (reached in 2 more steps. total 5n+4) Palindrome will be reached here for all bases s^n-1 where n>=7

### Theorem 9
In base 10, the number preceding a lychrel stop (ie. added to its mirror number, it becomes a lychrel stop) has one of two forms:
	
1. All digits added to their mirror digit are less than 10. (No Carry)
   1. The sum of the digits must be less than or equal to 9*floor(n/2)+(n%2)*4 where n is the number of digits in the number.
1. All digits added to their mirror digit are either zero or 11. (if there is any carry.)
   1. For odd numbers of digits, this means that the middle digit must be 0.
   1. Since the lead digit must be at least 1, then the lead digit cannot add with its mirror to make zero,
       it must make 11, so the new number will have a lead digit of 1, and have one more digit in length.
       The old number must have a lead digit of at least 2.
   1. The sum of the digits must be a multiple of 11 greater than zero and less than or equal to floor(n/2) where n is the number of digits in the number.

**Status:** Proven

**TODO:** Extend this to other bases.
	
#### Proof
Take n1 to be a series of digits a, b, c, ... c', b', a'<br/>
n2 is a Lychrel stop by definition, and<br/>
n2 = s(n1)

Our job then is to show what forms n1 can have.

The Lychrel stop, n2, could either get one more digit, or it could have the same number of digits.

##### Case 1: n2 has more digits than n1
With expanding digits, the outer digit of n2 is 1:

	n2 = 1, B, ... , B, 1
	=> a+a' % 10 = 1
	=> a+a' is 11 or 1.

Since the we added a digit a+a' = 1 doesn't work, as it can't lead to a carry.<br/>
=> a+a' = 11

=> B = (b+b'+1)%10 = 1+floor((b+b')/10)<br/>
*(We might say B = 1+floor((b+b')/10) and B' = (b+b'+1)%10, but B = B')*
	
Hence, B is 1 or 2

###### Case 1.1: B = 2
	=> (b+b'+1) % 10 = 2
	=> b+b' = 11 or 1

	=> 2 = 1+floor((b+b')/10)
	=> 1 = floor((b+b')/10) = floor(11/10) or floor(1/10)
	=> b+b' != 1
	=> b+b' = 11
	
	=> n2 = 1, 2, C, ... , C, 2, 1
	
	=> C = (c+c'+1)%10 = 1+floor((c+c')/10)
	
	This has the same form as the original B, so when B = 2, then C = 1 or 2 by the same standard.
	
###### Case 1.2: B = 1
	=> (b+b'+1) % 10 = 1
	=> b+b' = 10 or 0
	
	=> 1 = 1+floor((b+b')/10)
	=> 0 = floor((b+b')/10) = floor(10/10) or floor(0/10)
	=> b+b' != 10
	=> b+b' = 0
	
	n2 = 1, 1, C, ... , C, 1, 1
	
	=> C = (c+c')%10 = floor((c+c')/10)

	Hence, C is 1 or 0
	
###### Case 1.2.1: C = 1
	=> (c+c') % 10 = 1
	=> c+c' = 11 or 1
	
	=> 1 = floor((c+c')/10) = floor(11/10) or floor(1/10)
	
	=> c+c' != 1
	=> c+c' = 11
	
	n2 = 1, 1, 1, D, ... , D, 1, 1, 1

	=> D = (d+d'+1)%10 = 1+floor((d+d')/10)

	This has the same form as the original B, so this case can be treated like B.

###### Case 1.2.2: C = 0
	=> (c+c') % 10 = 0
	=> c+c' = 10 or 0
	
	=> 0 = floor((c+c')/10) = floor(10/10) or floor(0/10)
	
	=> c+c' != 10
	=> c+c' = 0

	n2 = 1, 1, 0, D, ... , D, 0, 1, 1

	=> D = (d+d')%10 = floor((d+d')/10)

	This has the same form as the original C, so this case can be treated like C.

###### Case 1 Semi-Conclusion
Hence, when n2 has more digits than n1 and n2 is a palindrome:
* All digits in n2 must be 0, 1, or 2.
* All digits in n1 must be either zero or make 11 when added to their mirror.
* The outer digits in n2 must be 1.
* The outer digits in n1 must make 11 when added together.
	
###### Case 1 middle digit
Now to address the middle digit/s:<br/>
If n2 has an even number of digits, then one eventually gets to a case like this:<br/>
..., Y, Z, Z, Y, ...<br/>
Y must have one of these forms:<br/>
Y = (y+y'+1)%10 = 1+floor((y+y')/10)<br/>
or<br/>
Y = (y+y')%10 = floor((y+y')/10)<br/>
	
Hence, y+y' will be either 0 or 11.
	
Note: Since z is the middle digit for n1, which is odd in this case, then z = z'.

If y+y' = 0, then Z has the form<br/>
Z = (2*z)%10 = floor(z/5)
	
Hence, Z must be 1, or 0.<br/>
1 = (2*z)%10 is not possible.<br/>
0 = (2*z)%10 => z = 0, or 5<br/>
0 = floor(z/5) => z = 0,1,2,3, or 4<br/>
Combining the previous two results, the only condition that can satisfy both criteria is z=0.<br/>
=> z = 0 and Z = 0 when y+y' = 0 and n1 has an odd number of digits while n2 has an even number of digits

If y+y' = 11, then Z has the form<br/>
Z = (2*z+1)%10 = 1+floor(z/5)
	
Hence, Z must be 1, or 2.<br/>
2 = (2*z+1)%10 is not possible.<br/>
1 = (2*z+1)%10 => z = 0, or 5<br/>
1 = 1+floor(z/5) => z = 0,1,2,3, or 4<br/>
Combining the previous two results, the only condition that can satisfy both criteria is z=0.<br/>
=> z = 0 and Z = 1 when y+y' = 11 and n1 has an odd number of digits while n2 has an even number of digits

=> z = 0 and Z = 0 or 1 when n1 has an odd number of digits while n2 has an even number of digits

If n2 has an even number of digits, then one eventually gets to a case like this:<br/>
..., Y, Z, Y, ...<br/>
As before, Y must have one of these forms:<br/>
Y = (y+y'+1)%10 = 1+floor((y+y')/10)<br/>
or<br/>
Y = (y+y')%10 = floor((y+y')/10)
	
y+y' will be either 0 or 11.
	
However, unlike with the even n2 case, there is no z or z'.<br/>
This middle digit is novel and is the result of adding floor((y+y')/10) to (y+y')%10, so:<br/>
Z = floor((y+y')/10) + (y+y')%10<br/>
=> Z = 0 or 2 when n1 has an even number of digits while n2 has an odd number of digits<br/>

###### Case 1 Conclusion
Hence, when n2 has more digits than n1 and n2 is a palindrome:<br/>
All digits in n2 must be 0, 1, or 2.<br/>
All digits in n1 must be either zero or make 11 when added to their mirror.<br/>
The outer digits in n2 must be 1.<br/>
The outer digits in n1 must make 11 when added together.<br/>
The middle digit of n1 must be zero if n1 has an odd number of digits.<br/>
The middle digit of n2 must be zero or 2 if n2 has an odd number of digits.<br/>
The middle two digits of n2 must be zero or 1 if n2 has an even number of digits.

##### Case 2: n2 has the same number of digits as n1
When n2 has the same number of digits as n1:<br/>
n2 = A, B, ... , B, A<br/>
=> a + a' is less than 10<br/>
Since a is at least one, then a' must be less than 9.
	
If a+a' = 9, then b+b' must follow the same rules as a+a' to prevent a carry from cascading. (Except that b CAN be 0, so b' CAN be 9.)<br/>
If a+a' < 9, then b+b' must still follow the same rules to prevent a carry from making A != A'. (Except, as before, that b CAN be 0, so b' CAN be 9.)<br/>
If there are an odd number of digits, then z = z', so we have:<br/>
2*z<10 => z<5.
	
So, with n1 and n2 having the same number of digits:<br/> 
Every digit added to its mirror digit must make a number less than 10.<br/>
If there are an odd number of digits, then the middle digit of n1 must be less than 5.

### Theorem 10
The following two patterns are stable in the infinite base (these are the most generic patterns I have been able to construct):

E(n,a) = A*2^n,...,Y*2^n,Z*2^n, (0,)xa, b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1, (b-1)xa, Z*2^n-1,Y*2^n,...,A*2^n
O(n,a) =	 A*2^n,...,Y*2^n,Z*2^n, (0,)xa, b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,(b-1,)xa,Z*2^n-1,Y*2^n,...,A*2^n

By stable, I mean that for any number with a form described by either of these functions, the next number has a form that can also be described by the same function.

**Status:** Proven
	
#### Proof
s(E(n,a)) = A*2^(n+1),...,Y*2^(n+1),Z*2^(n+1), (0,)xa, b-c*2^(n+1),b-d*2^(n+1)-1,...,b-z*2^(n+1)-1,b-z*2^(n+1)-1,...,b-d*2^(n+1)-1,b-c*2^(n+1)-1, (b-1)xa, Z*2^(n+1)-1,...,Y*2^(n+1),A*2^(n+1) = E(n+1,a)
s(O(n,a)) = A*2^(n+1),...,Y*2^(n+1),Z*2^n, (0,)xa, b-c*2^(n+1),b-d*2^(n+1)-1,...,b-z*2^(n+1)-1,...,b-d*2^(n+1)-1,b-c*2^(n+1)-1,(b-1,)xa,Z*2^(n+1)-1,...,Y*2^(n+1),A*2^(n+1) = O(n+1,a)

### Theorem 11
For base 10, a number of the form E(n,a) or O(n,a), there are only two forms where the next number can be a palindrome:

1) For either E(n,a) or O(n,a), none of the digits carry. Hence c*2^(n+1) > b, and the same is true for d*2^(n+1)+1 through z*2^(n+1)+1. Hence all other digits that would normally not carry with the infinite base also don't carry.
2) For only E(n,a), the only number that can produce a palindrome is, 6, 6, 5, 5. (Hence, a=0, and n must be 0 or 1 to make the E(n,a) pattern produce this result.)

**Status:** Proven

**TODO:** Extend this to other bases. Probably it will be necessary to extend theorem 9 to other bases first.

#### Proof
Per theorem 9, there are only two number forms for which the next number is a palindrome, one where all digits added to their opposite produce a value less than 10, and one where all digits added to their opposite produce either an 11 or a zero.

##### No Carry
The middle parts of the pattern have the form b-x. (Note: This does not include (b-1)xa, which normally doesn't carry anyway)
If all of the b-x (except (b-1)xa, which usually doesn't carry anyway) digits fail to carry because 2*x >= b and the rest of the pattern progresses as normal, (ie. doesn't carry) then the pattern will create a palindrome.

##### With Carry
A*2^n + A*2^n can never equal 11. It must make an even number. The same with hypothetical B through Y terms. These optional terms must be absent from the patttern.
Hence we are left with this form:

	E(n,a) = Z*2^n,(0,)xa,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,(b-1)xa,Z*2^n-1
	O(n,a) =	Z*2^n,(0,)xa,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,(b-1,)xa,Z*2^n-1

Z*2^n = 6 (Z=3 and n=1 or Z=6 and n=0) will lead to it creating 11 when added with its opposite giving us this:

	E(n=1 or 0,a) = 6,(0,)xa,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,(b-1)xa,5
	O(n=1 or 0,a) = 6,(0,)xa,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,(b-1,)xa,5


Since b-1 will not be 11, a must be 0 giving us these possibilities:

	E(n=1 or 0,0) = 6,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,5
	O(n=1 or 0,0) = 6,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,5

b-c*2^n = 6 will lead to it creating 11 when added with its opposite, (c=2 and n=1 or c=4 and n=0) giving us this:					

	E(n=1 or 0,0) = 6,6,b-d*2^n-1,...,b-z*2^n-1,b-z*2^n-1,...,b-d*2^n-1,5,5
	O(n=1 or 0,0) = 6,6,b-d*2^n-1,...,b-z*2^n-1,...,b-d*2^n-1,5,5

b-d*2^n + b-d*2^n can never equal 11. It must make an even number. The same with hypothetical e through z terms. These optional terms must be absent from the pattern.
Since this eliminates the odd z that distinguishes O(n,a), O(n,a) is not a possible form.
Hence, the form is:

	E(n=1 or 0,0) = 6,6,5,5

Hence, this is the only number following the pattern which can lead to a palindrome with carry.					

### Theorem 12
In base 10, E(n,a) is always a multiple of 11. (Not true of O(n,a), but due to Theorem 3, all O(n,a) proceeding from a carrying E(n,a) must be multiples of 11, which places an interesting constraint on O(n,a).)

**Status:** Proven

**TODO:** Extend this to other bases.

#### Proof
E(n,a) = A*2^n,...,Y*2^n,Z*2^n,(0,)xa,b-c*2^n,b-d*2^n-1,...,b-z*2^n-1,b-z*2^n-1,...,b-d*2^n-1,b-c*2^n-1,(b-1,)xa,Z*2^n-1,Y*2^n,...,A*2^n

	A*2^n-A*2^n = 0, which is also true for any terms B through Y.
	deltaZ =  Z*2^n-(Z*2^n-1) = 1
	deltab = 0-(b-1) = 1-b = -9 (in base 10, ie. b=10)
	deltac = b-c*2^n-(b-c*2^n-1) = 1

The deltas on the d through z terms are all 0.

With any even a, there are an even number of deltab terms in the divisibility by 11 check, and the alternating positive and negative terms all cancel out and we end up with:

	deltaZ - deltac = 0 => divisible by 11.

With an odd a, all deltab terms cancel out except one, and we end up with: 

	deltaZ - deltab + deltac = 1+9+1 = 11 => divisible by 11.

This analysis does not work with O(n,a), and it is simple to construct a counterexample to the proposition that O(n,a) is always a multiple of 11.

eg.
3,6,0,4,3,3,9,5,3 is an example that comes up in the series starting with 9,9,9,9 and is a form matching O(0,1). Proceeding, as it does, from a number which is divisible by 11, then it too must be divisible by 11.

	3-6+0-4+3-3+9-5+3 = 0 => divisible by 11
	
If we take this same number but redo it with O(0,0), then we get 3,6,4,3,3,5,3, which is a number that I already know terminates in a small number of steps. Doing the divisibility by 11 check, we get:

	3-6+4-3+3-5+3 = -1 => NOT divisible by 11
	
In fact, it would seem that most O(n,a) are not divisible 11, meaning that far fewer of these numbers appear to be appropriate candidates for chains for non-lychrel numbers than for E(n,a). Is E(n,A) more likely to result in a palindrome shortly after carrying, before it can settle down into an O(n,a) pattern, than O(n,a) is (before settling down into an E(n,a) pattern)?

### Theorem 13
...

	0:4) Define N(-3) = b-1, b-1, b-1, b-1
	1:5) => N(-2) = 1, b-1, b-1, b-1, b-2
	2:6) => N(-1) = 1, 0, b-1, b-1, b-2, b-1 
	3:7) => N(0) = 1, 0, b-1, b-1, b-2, b-1, 0 = Pa(0,1) = (for n=0) 2^n, 0, b-2^n, b-1, b-2^n-1, b-1, 2^n-1
	---) Pa(3,1) = 8,0,b-8,b-1,b-9,b-1,7 (would've carried for b=8, avoids an expected carry for b=9)
	6:8) s(Pa(3,1)) = 1,5,b-1,b-6,b-2,b-6,0,5 (for b=10)
	7:8) s'(Pa(3,1))= 6, 6, b-6, b-7, b-7, b-7, 5, 6 = N'(0) where N'(n') = 6*2^n',6*2^n',b-6*2^n',b-6*2^n'-1,b-6*2^n'-1,b-6*2^n'-1,6*2^n'-1,6*2^n' = E(n',0)
    
    ---) s(N'(n')) = N'(n'+1)
    8:9) s(N'(0)) = 1,3,1,b-3,b-4,b-4,b-2,2,2 (for b=10)
    9:9) s'(N'(0)) = 3,6,0,b-6,b-7,b-7,b-1,5,3 = N''(0) where N''(n'') = 3*2^n'',6*2^n'',0,b-6*2^n'',b-6*2^n''-1,b-6*2^n''-1,b-1,6*2^n''-1,3*2^n'' = O(n'',1)
    
    ----) s(N''(n'')) = N''(n''+1)
    10:9) s(N''(0)) = 7,1,b-1,b-3,b-4,b-2,0,1,6 (for b=10)
    ----) s'(N''(0)) = 13, 3, 0, b-4, b-7, b-5, b-1, 2, 13 = N'''(0) where N'''(n''') = 13*2^n''',3*2^n''',0,b-4*2^n''',b-6*2^n'''-1,b-4*2^n'''-1,b-1,3*2^n'''-1,13*2^n'''
    10:10) s'(N''(0)) = 1,3,3,0,b-4,b-7,b-5,b-1,3,3 (for b=10)
    -----) s''(N''(0)) = 4,7,2,b-4,b-10,b-11,b-4,2,6,4
    -----) s'''(N''(0)) = 8,13,5,b-7,b-20,b-20,b-8,4,13,8 = N''''(0) where N''''(n'''') = 8*2^n'''',13*2^n'''',5*2^n'''',b-7*2^n'''',b-19*2^n''''-1,b-19*2^n''''-1,b-7*2^n''''-1,5*2^n''''-1,13*2^n'''',8*2^n''''
    11:10) s''(N''(0)) = 4,7,2,b-5,b-1,b-1,b-4,2,6,4 (for b=10)
    -----) s'''(N''(0)) = 8,13,5,b-8,b-1,b-1,b-9,4,13,8 = N'''''(0) where N'''''(n''''') = 8*2^n''''',13*2^n''''',5*2^n''''',b-8*2^n''''',b-1,b-1,b-8*2^n'''''-1,5*2^n'''''-1,13*2^n''''',8*2^n'''''
    12:10) s'''(N''(0)) = 9,3,5,b-8,b-1,b-1,b-9,5,3,8 (for b=10)



