package NumberInReversedOrder;

public class DecimalNumber {
    private String number;

    public DecimalNumber(String number) {
        this.number = number;
    }

    public String getReversedNumber(){
        String result = "";
        for (int i = this.number.length() - 1; i >= 0; i--) {
            result += this.number.charAt(i);
        }
        return result;
    }
}
