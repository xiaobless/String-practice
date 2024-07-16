public class ExtractNumber {
  
    /**
     * 从混合字符串中提取数字，并格式化为小数点后两位的字符串。
     * 如果原数字没有小数点，则在末尾添加".00"。
     * 如果小数点后超过两位，则截取前两位小数。
     *
     * @param mixedStr 字母和数字混合的字符串
     * @return 格式化后的数字字符串，小数点后保留两位
     */
    public static String extractNumberWithFixedDecimals(String mixedStr) {
        // 使用正则表达式找到字符串中的所有数字部分
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(mixedStr);
        
        if (matcher.find()) {
            String numberStr = matcher.group();
            // 检查是否有小数点
            if (numberStr.contains(".")) {
                // 有小数点，且小数点后有多于两位的情况，截取到小数点后两位
                int dotIndex = numberStr.indexOf('.');
                if (numberStr.length() - dotIndex > 3) {
                    numberStr = numberStr.substring(0, dotIndex + 3);
                }
            } else {
                // 无小数点，添加".00"
                numberStr += ".00";
            }
            return numberStr;
        } else {
            // 如果没有找到数字，返回空字符串或特定提示
            return "";
        }
    }

  /**
     * 提取字符串中的数字部分，并格式化为小数点后两位，不足补0。
     * @param input 原始字符串，前部为字母，后部为数字。
     * @return 格式化后的数字字符串，小数点后保留两位。
     */
    public static String extractAndFormat(String input) {
        // 找到第一个数字的位置
        int firstDigitIndex = -1;
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                firstDigitIndex = i;
                break;
            }
        }
        
        if (firstDigitIndex == -1) {
            // 如果没有找到数字，返回空字符串或处理错误，根据需求调整
            return "";
        }
        
        // 从第一个数字开始截取到字符串末尾
        String numberPart = input.substring(firstDigitIndex);
        
        // 检查是否有小数点
        int decimalPointIndex = numberPart.indexOf('.');
        if (decimalPointIndex == -1) {
            // 如果没有小数点，添加小数点和两个0
            return numberPart + ".00";
        } else {
            // 有小数点，检查小数点后是否有超过两位的数字
            int decimalLength = numberPart.length() - decimalPointIndex - 1;
            if (decimalLength > 2) {
                // 截取到小数点后两位
                return numberPart.substring(0, decimalPointIndex + 3);
            } else {
                // 小数不足两位，在末尾补0
                while (decimalLength < 2) {
                    numberPart += "0";
                    decimalLength++;
                }
                return numberPart;
            }
        }
    }
}
