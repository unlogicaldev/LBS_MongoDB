package com.lbs.mongo.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
	* 일자들끼리 연산을 수행할때 Return값의 단위를 정해주는 상수로 사용된다.
	* 이 상수는 일단위의 값을 Return하여 준다.
	*/
	public static final char DAY = 'D';

	/**
	* 일자들끼리 연산을 수행할때 Return값의 단위를 정해주는 상수로 사용된다.
	* 이 상수는 시단위의 값을 Return하여 준다.
	*/
	public static final char HOUR = 'H';

	/**
	* 일자들끼리 연산을 수행할때 Return값의 단위를 정해주는 상수로 사용된다.
	* 이 상수는 분단위의 값을 Return하여 준다.
	*/
	public static final char MIN = 'M';
	
	/**
	* 오늘 일자를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
	*
	* 사용예) getToday("yyyy/MM/dd hh:mm a")<BR>
	* 결 과 ) 2001/12/07 10:10 오후<BR><BR>
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pOutformat String
	*/
	public static String getToday( String pOutformat) {
		String rDateString = null;
		Date vDate = new Date();

		try
		{
			rDateString = getDateFormat( pOutformat, vDate);

		} catch( Exception e ) {}

		return rDateString;
	}
	
	/**
	* 전달받은 날짜(Date)를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
	*
	* 사용예) getToday("yyyy/MM/dd hh:mm a")<BR>
	* 결 과 ) 2001/12/07 10:10 오후<BR><BR>
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pOutformat String
	*/
	public static String getDateFormat( String pOutformat, Date vDate) {

		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);

		String rDateString = null;

		try
		{
			rDateString = pOutformatter.format(vDate);

		} catch( Exception e ) {}

		return rDateString;
	}
	
	/**
	* 입력받은 날짜에 일/시/분 단위의 값을 더하여 출력Format에 따라 값을 넘겨준다. <BR><BR>
	* Parameter는 입력일, 입력일 Format, 출력일 Format, 일단위 더하기, 시단위 더하기,
	* 분단위 더하기이다.
	*
 	* 간단한 사용예는 다음과 같다.
	*
	* 사용예) LLog.debug.println( getFormattedDateAdd("200201010605","yyyyMMddhhmm","yyyy/MM/dd HH:mm",-100,10,-11) );
	* 결과) 2001/09/23 15:54
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pIndate String
	* @param pInformat String
	* @param pOutformat String
	* @param pDay int
	* @param pHour int
	* @param pMin int
	*/
	public static String getFormattedDateAdd(String pIndate, String pInformat, String pOutformat, int pDay, int pHour, int pMin ) {

		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);

		String rDateString = "";
		Date vIndate = null;
		long vAddon = ( pDay * 24L*60L*60L*1000L ) + ( pHour * 60L*60L*1000L ) + ( pMin * 60L*1000L );

		try
		{
			vIndate = pInformatter.parse(pIndate);

			Date vAddday = new Date( vIndate.getTime() + vAddon );
			
			
			
			rDateString = pOutformatter.format(vAddday);

		} catch( Exception e ) {
			rDateString = pIndate;
		}

		return rDateString;
	}
	
	/**
	* 일자들의 계산을 수행한다..
	* 제일 마지막의 Parameter pType에 따라서 Return값이 다르다.
	* 둘째 Parameter는 첫째 Parameter의 입력 형식을 지정하고 넷째 Parameter는
	* 셋째 Parameter의 입력형식을 지정한다.
	* Return값의 단위를 정해주는 pType에는 3가지가 올 수 있는데
	* ECOMJDateU.DAY, ECOMJDateU.HOUR, ECOMJDateU.MIN 이다.
	* 각각의 단위는 일단위, 시단위, 분단위 이다.
	* 첫째 Parameter로 입력받은 일자에서 셋째 Parameter로 입력받은 일자를 빼서
	* 나온 결과를 돌려준다.
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	* 간단한 사용예는 다음과 같다.
	*
	* LLog.debug.println(getComputedDate("2002/01/04 00:01","yyyy/MM/dd hh:mm","2002/01/02 23:59","yyyy/MM/dd hh:mm",ECOMJDateU.DAY));
	*
	* 작업 결과로 '1'이 표시된다.
	*
	* @return long
	* @param pIndate1 java.lang.String
	* @param pInformat1 java.lang.String
	* @param pIndate2 java.lang.String
	* @param pInformat2 java.lang.String
	* @param pType char
	*/
	public static long getComputedDate( String pIndate1, String pInformat1, String pIndate2, String pInformat2) {

		SimpleDateFormat pInformatter1 =  new SimpleDateFormat (pInformat1);
		SimpleDateFormat pInformatter2 =  new SimpleDateFormat (pInformat2);
		long vDategap = 0;
		try {
			Date vIndate1 = pInformatter1.parse(pIndate1);
			Date vIndate2 = pInformatter2.parse(pIndate2);
			vDategap = vIndate1.getTime() - vIndate2.getTime();
		} catch ( Exception e ) { 
			e.printStackTrace();
			return 0;
		}

		return vDategap;
	}
}
