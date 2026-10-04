<div align="center">

# 🔢 Number Logic Programs in Java

**A single menu-driven Java program that checks 55 special numbers – each with its own commented function.**

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=openjdk&logoColor=white)
![Numbers](https://img.shields.io/badge/Special%20Numbers-55-blue)
![Menu Driven](https://img.shields.io/badge/Type-Menu%20Driven-success)
![License](https://img.shields.io/badge/License-MIT-green)
![Level](https://img.shields.io/badge/Level-Beginner%20Friendly-brightgreen)

</div>

---

## 📑 Table of Contents

- [✨ Features](#-features)
- [🧭 How It Works](#-how-it-works)
- [🚀 Getting Started](#-getting-started)
- [📸 Sample Output](#-sample-output)
- [📚 Numbers Covered](#-numbers-covered)
- [🧠 Code Structure](#-code-structure)
- [⚠️ Notes & Limits](#️-notes--limits)
- [🤝 Contributing](#-contributing)
- [📜 License](#-license)
- [👨‍💻 Author](#-author)

---

## ✨ Features

- 🔢 **55 special numbers** in one program – from Armstrong and Happy to Keith, Weird and Goldbach
- 📋 **Menu driven** – pick a number type, enter a number, get an instant answer
- 🧩 **One function per number type** – clean, modular and easy to understand
- 💬 **Fully commented code** – every function explains the rule and gives an example
- 🔁 **Runs in a loop** – check as many numbers as you like, then exit when you want
- 🛡️ **Input validation** – wrong choices and out-of-range numbers are handled politely
- 🎓 **Beginner friendly** – uses only basic Java (loops, functions, `Scanner`)

---

## 🧭 How It Works

1. 📋 The program shows a numbered menu of all 55 number types.
2. ⌨️ You enter your **choice** (1-55).
3. 🔢 You enter a **positive number** (up to 9 digits).
4. ✅ The matching function checks the number and prints whether it **is** or **is not** that type.
5. 🔁 It asks **"Do you want to continue? (y / n)"** – `y` shows the menu again, `n` exits.

---

## 🚀 Getting Started

### 📦 Requirements
- ☕ Java **JDK 11** or later

### ▶️ Run it

```bash
# 1. Clone the repository
git clone https://github.com/<your-username>/<your-repo-name>.git
cd <your-repo-name>

# 2. Compile
javac NumberLogicPrograms.java

# 3. Run
java NumberLogicPrograms
```

---

## 📸 Sample Output

```text
============ NUMBER CHECKER MENU ============
 1. Technical Number
 2. Magic Number
 3. Disarium Number
 ...
55. Perfect Cube Number
=============================================
Enter your choice (1-55): 10
Enter a positive number (up to 9 digits): 153
>> 153 is an Armstrong number.

Do you want to continue? (y = yes / n = exit): y
```

More results:

```text
>> 70 is a Weird number.
>> 155349 is a Bouncy number.
>> 3 is an Evil (OneEven) number.
>> 192 is a Fascinating number.
```

---

## 📚 Numbers Covered

| # | Number | Rule | Example |
|---|--------|------|---------|
| 1 | **Technical** | Even number of digits; square of the sum of its two halves equals the number | `2025 = (20+25)^2` |
| 2 | **Magic** | Keep adding digits until one digit is left; that digit is 1 | `19 → 10 → 1` |
| 3 | **Disarium** | Sum of digits raised to their positions equals the number | `175 = 1^1+7^2+5^3` |
| 4 | **Adam** | Square of the reverse equals reverse of the square | `12: 21^2=441, reverse(144)=441` |
| 5 | **Automorphic** | Square ends with the number itself | `25 → 625` |
| 6 | **Trimorphic** | Cube ends with the number itself | `4 → 64` |
| 7 | **Neon** | Sum of digits of the square equals the number | `9 → 81 → 8+1` |
| 8 | **Unique** | No digit is repeated | `2019` |
| 9 | **Krishnamurthy** | Sum of factorials of digits equals the number | `145 = 1!+4!+5!` |
| 10 | **Armstrong** | Sum of digits each raised to the digit count equals the number | `153 = 1^3+5^3+3^3` |
| 11 | **Special** | Sum of digits + product of digits equals the number | `59 = 14 + 45` |
| 12 | **Happy** | Repeated sum of squares of digits reaches 1 | `19 → 82 → 68 → 100 → 1` |
| 13 | **Fascinating** | 3+ digits; n, 2n, 3n joined use each digit 1-9 exactly once | `192 → 192384576` |
| 14 | **Lynch-Bell** | Distinct non-zero digits; divisible by each digit | `135` |
| 15 | **Palindrome** | Reads the same forwards and backwards | `12321` |
| 16 | **Harshad** | Divisible by the sum of its digits | `18 (18 / 9)` |
| 17 | **Duck** | Contains a zero digit | `3210` |
| 18 | **Buzz** | Divisible by 7 or ends with 7 | `63, 27` |
| 19 | **Spy** | Sum of digits equals product of digits | `1124 (8 = 8)` |
| 20 | **Evil (OneEven)** | Binary form has an EVEN number of 1s | `3 = 11` |
| 21 | **Odious** | Binary form has an ODD number of 1s | `7 = 111` |
| 22 | **Pernicious** | Number of 1s in binary is a prime | `7 = 111 (3 ones)` |
| 23 | **Pandigital** | d-digit number using every digit 1 to d exactly once | `3142` |
| 24 | **Bouncy** | Digits neither all increasing nor all decreasing (3+ digits) | `155349` |
| 25 | **Keith** | Digit-sum series (each term = sum of previous d terms) reaches n | `197: 1,9,7,17,33,57,107,197` |
| 26 | **Kaprekar** | Square split into two parts adds up to the number | `45 → 2025 → 20+25` |
| 27 | **Even** | Divisible by 2 | `8` |
| 28 | **Odd** | Not divisible by 2 | `7` |
| 29 | **Triangular** | Equals 1 + 2 + 3 + ... + k | `10 = 1+2+3+4` |
| 30 | **Prime** | Exactly two factors: 1 and itself | `29` |
| 31 | **Smith** | Composite; digit sum equals digit sum of its prime factors | `22 = 2x11: 4 = 2+1+1` |
| 32 | **Ugly** | Only prime factors are 2, 3 and 5 | `30 = 2x3x5` |
| 33 | **Twin Prime** | Prime p where p-2 or p+2 is also prime | `11 (with 13)` |
| 34 | **Twisted Prime** | Prime whose reverse is also prime | `13 → 31` |
| 35 | **Circular Prime** | All rotations of its digits are prime | `197, 971, 719` |
| 36 | **Lucky** | Survives the lucky-number sieve | `1, 3, 7, 9, 13, 15, 21, 25` |
| 37 | **Leyland (Lead)** | Of the form x^y + y^x, x, y > 1 | `17 = 2^3+3^2` |
| 38 | **Perfect** | Sum of proper divisors equals the number | `28 = 1+2+4+7+14` |
| 39 | **Abundant** | Sum of proper divisors is greater than the number | `12 (16 > 12)` |
| 40 | **Deficient** | Sum of proper divisors is less than the number | `8 (7 < 8)` |
| 41 | **Sunny** | n + 1 is a perfect square | `8 (9 = 3^2)` |
| 42 | **Emirp** | Prime whose reverse is a different prime | `13 → 31` |
| 43 | **Pronic** | Product of two consecutive integers | `20 = 4x5` |
| 44 | **Sphenic** | Product of exactly three different primes | `30 = 2x3x5` |
| 45 | **Fibonacci** | Belongs to 0, 1, 1, 2, 3, 5, 8, 13, 21... | `21` |
| 46 | **Perfect Square** | Square of an integer | `49 = 7^2` |
| 47 | **Composite** | More than two factors (not prime, greater than 1) | `15` |
| 48 | **Goldbach** | Even number greater than 2 that is a sum of two primes | `28 = 5 + 23` |
| 49 | **Weird** | Abundant, but no group of its proper divisors adds to n | `70, 836` |
| 50 | **Pell** | Pell series 0, 1, 2, 5, 12, 29, 70... (2xprev + prev-prev) | `70` |
| 51 | **PalPrime** | A prime that is also a palindrome | `131` |
| 52 | **Mersenne Prime** | Prime of the form 2^p - 1 | `127` |
| 53 | **Sophie Germain Prime** | Prime p where 2p + 1 is also prime | `11 (23)` |
| 54 | **Semiprime** | Product of exactly two primes (may be equal) | `15 = 3x5` |
| 55 | **Perfect Cube** | Cube of an integer | `27 = 3^3` |

---

## 🧠 Code Structure

```text
NumberLogicPrograms.java
├── Helper functions      → countDigits(), digitSum(), reverse(), factorial(), sumProperDivisors() ...
├── 55 number functions   → isTechnical(), isMagic(), isDisarium(), ... isPerfectCube()
├── showMenu()            → prints the numbered menu
├── printResult()         → prints the final message ("is" / "is NOT")
└── main()                → menu loop + switch that calls the right function
```

---

## ⚠️ Notes & Limits

- 📏 Input must be a **positive number of up to 9 digits**.
- 📖 Definitions follow common **school-syllabus (ISC/ICSE style)** versions, e.g. *Technical* = square of the sum of the two halves, *Special* = sum of digits + product of digits equals the number.
- 🔎 *Pandigital* here means a d-digit number that uses each digit from 1 to d exactly once.
- 🔗 *Lead* is treated as the **Leyland** number (`x^y + y^x`).
- 🐢 *Weird* number checks on very large inputs can take longer because they search divisor combinations.

---

## 🤝 Contributing

Contributions are welcome! 🎉

1. 🍴 Fork the repository
2. 🌿 Create a branch: `git checkout -b feature/new-number`
3. 💾 Commit your changes: `git commit -m "Add <name> number"`
4. 📤 Push the branch: `git push origin feature/new-number`
5. 🔃 Open a Pull Request

Ideas: add more special numbers, input validation for non-numeric entries, or a GUI version.

---

## 📜 License

This project is licensed under the **MIT License** – you are free to use, copy, modify and share it, provided the copyright notice is kept.

---

## 👨‍💻 Author

**[Your Name]**

- 🐙 GitHub: https://github.com/Akshara-CodeX  
- 📧 Email: akshara.zylogis.26@gmail.com

---

<div align="center">

⭐ If you found this project helpful, please give it a **star**! ⭐

Made with ❤️ and ☕ in Java

</div>