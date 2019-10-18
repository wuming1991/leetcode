
import com.Test1;
import com.google.common.annotations.VisibleForTesting;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @ProjectName: study
 * @Package: PACKAGE_NAME
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/10/24 17:43
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) throws ParseException {
		Test1 test1 = null;
		try {
			  test1 = new Test1(new String[]{"1"});
			test1.getA(2);
		}catch (Exception e){
			System.out.println(e.toString());
		}
		try {
			Class<?> test = Class.forName("com.Test1");
			Method main = test.getDeclaredMethod("getA", int.class);
			Object invoke = main.invoke(test1,0);
			System.out.println((String) invoke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Date date = new Date();
		
		date.setDate(1);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = format.format(date);
		
		System.out.println(s);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = format1.parse("2017-1-1");
		Calendar calendar=Calendar.getInstance();
		//calendar.set(2017,1,1);
		calendar.add(Calendar.MONTH, -2);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxDay, 23, 59, 59);
		Date time1 = calendar.getTime();
		System.out.println(format.format(time1)+"1111");
		System.out.println(calendar.get(Calendar.MONTH));
		calendar.add(Calendar.MONTH,-10);
		Date time = calendar.getTime();
		System.out.println(format.format(time));*/
		String a="1542354197971";
		Date date = new Date(Long.parseLong(a));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		System.out.println(sdf.format(date));
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), maxDay, 23, 59, 59);
		//上月结束时间
		String end = sdf.format(calendar.getTime());
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01  00:00:00");
		//上月开始时间
		String start = sdf2.format(calendar.getTime());
		
		System.out.println(start + ":" + end);
		
		String s= "_93_108_118_";
		String[] split = s.split("_");
		System.out.println(split[1] + "=" + split[2]+split.length);
		System.out.println(UUID.randomUUID().toString().substring(0,8));
	}
}
