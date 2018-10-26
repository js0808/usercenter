package cn.org.bjca.footstone.usercenter.util;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>身份证号码验证
 * 1、号码的结构
 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * 2、地址码(前六位数）
 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。
 * 3、出生日期码（第七位至十四位）
 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
 * 4、顺序码（第十五位至十七位）
 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
 * 顺序码的奇数分配给男性，偶数分配给女性。
 * 5、校验码（第十八位数）
 * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0,  , 16 ，先对前17位数字的权求和
 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
 * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0
 * X 9 8 7 6 5 4 3 2
 * </pre>
 */

public class IdcardUtil {

  final static Map<Integer, String> ZONE_NUM = new HashMap<Integer, String>();

  static {
    ZONE_NUM.put(11, "北京");
    ZONE_NUM.put(12, "天津");
    ZONE_NUM.put(13, "河北");
    ZONE_NUM.put(14, "山西");
    ZONE_NUM.put(15, "内蒙古");
    ZONE_NUM.put(21, "辽宁");
    ZONE_NUM.put(22, "吉林");
    ZONE_NUM.put(23, "黑龙江");
    ZONE_NUM.put(31, "上海");
    ZONE_NUM.put(32, "江苏");
    ZONE_NUM.put(33, "浙江");
    ZONE_NUM.put(34, "安徽");
    ZONE_NUM.put(35, "福建");
    ZONE_NUM.put(36, "江西");
    ZONE_NUM.put(37, "山东");
    ZONE_NUM.put(41, "河南");
    ZONE_NUM.put(42, "湖北");
    ZONE_NUM.put(43, "湖南");
    ZONE_NUM.put(44, "广东");
    ZONE_NUM.put(45, "广西");
    ZONE_NUM.put(46, "海南");
    ZONE_NUM.put(50, "重庆");
    ZONE_NUM.put(51, "四川");
    ZONE_NUM.put(52, "贵州");
    ZONE_NUM.put(53, "云南");
    ZONE_NUM.put(54, "西藏");
    ZONE_NUM.put(61, "陕西");
    ZONE_NUM.put(62, "甘肃");
    ZONE_NUM.put(63, "青海");
    ZONE_NUM.put(64, "宁夏");
    ZONE_NUM.put(65, "新疆");
    ZONE_NUM.put(71, "台湾");
    ZONE_NUM.put(81, "香港");
    ZONE_NUM.put(82, "澳门");
    ZONE_NUM.put(91, "国外");
  }

  final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
  final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

  /**
   * 身份证号是否基本有效
   *
   * @param s 号码内容
   * @return 是否有效，null和""都是false
   */
  public static boolean isIdcard(String s) {
    if (s == null || (s.length() != 15 && s.length() != 18)) {
      return false;
    }
    final char[] cs = s.toUpperCase().toCharArray();
    // （1）校验位数
    int power = 0;
    for (int i = 0; i < cs.length; i++) {// 循环比正则表达式更快
      if (i == cs.length - 1 && cs[i] == 'X') {
        break;// 最后一位可以是X或者x
      }
      if (cs[i] < '0' || cs[i] > '9') {
        return false;
      }
      if (i < cs.length - 1) {
        power += (cs[i] - '0') * POWER_LIST[i];
      }
    }
    // （2）校验区位码
    if (!ZONE_NUM.containsKey(Integer.valueOf(s.substring(0, 2)))) {
      return false;
    }
    // （3）校验年份
    String year = s.length() == 15 ? "19" + s.substring(6, 8) : s.substring(6, 10);
    final int iyear = Integer.parseInt(year);
    if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
      return false;// 1900年的PASS，超过今年的PASS
    }
    // （4）校验月份
    String month = s.length() == 15 ? s.substring(8, 10) : s.substring(10, 12);
    final int imonth = Integer.parseInt(month);
    if (imonth < 1 || imonth > 12) {
      return false;
    }
    // （5）校验天数
    String day = s.length() == 15 ? s.substring(10, 12) : s.substring(12, 14);
    final int iday = Integer.parseInt(day);
    if (iday < 1 || iday > 31) {
      return false;
    }
    // （6）校验一个合法的年月日
    if (!validate(iyear, imonth, iday)) {
      return false;
    }
    // （7）校验“校验码”
    return s.length() == 15 || cs[cs.length - 1] == PARITYBIT[power % 11];
  }

  static boolean validate(int year, int month, int day) {
    // 比如考虑闰月，大小月等
    return true;
  }
}