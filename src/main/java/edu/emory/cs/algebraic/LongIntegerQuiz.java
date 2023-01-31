package edu.emory.cs.algebraic;
import java.util.Arrays;

public class LongIntegerQuiz extends LongInteger {
    public LongIntegerQuiz(LongInteger n) {
        super(n);
    }

    public LongIntegerQuiz(String n) {
        super(n);
    }

    @Override
    protected void addDifferentSign(LongInteger n) {
        //different sign means there is one positive number and one negative number
        //which number is bigger, use which number's sign
        if(compareAbs(n) < 0) {
            sign = n.sign;
            digits = subtractValue(n.digits, digits);
        } else {
            digits = subtractValue(digits, n.digits);
        }
    }

    private byte[] subtractValue(byte[] greater, byte[] smaller) {
        byte[] result = new byte[greater.length];
        int carry = 0;
        int digit;

        // 1. subtract shorter part
        for(int i = 0; i < smaller.length; i++) {
            digit = greater[i] - smaller[i] - carry;
            if(digit < 0) {
                carry = 1;
                digit += 10;
            } else {
                carry = 0;
            }
            result[i] = (byte)digit;
        }

        // 2. carry rippling

        for(int i = smaller.length; i < greater.length; i++) {
            digit = greater[i] - carry;
            if(digit < 0) {
                carry = 1;
                digit += 10;
            } else {
                carry = 0;
            }
            result[i] = (byte)digit;
        }

        // 3. trim out trailing zeros
        int i;
        for(i = result.length - 1; i > 0; i--) {
            if(result[i] == 0) {
                continue;
            } else {
                break;
            }
        }
        return Arrays.copyOfRange(result, 0,  i + 1);
    }
}
