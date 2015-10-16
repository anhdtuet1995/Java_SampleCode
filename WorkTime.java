/*
* Java Ngu Hoc
*/

import java.util.*;

public class WorkTime{

	// public void increase(ClockTime a, int m){
	// 	a = new ClockTime(a.getHour(), a.getMinute() + m, a.getAmPm());
	// 	if ( m >= 60 * 24 && m < 60 * 24 * 7){
	// 		if( m / (60 * 24) == 1) System.out.println(a.toString() + "( 1 day later )");
	// 		else if( m / (60 * 24) > 1 ){
	// 			System.out.println(a.toString() + "( " + m / (60 * 24) + " days later )" );
	// 		}
	// 		else{
	// 			System.out.println(a.toString());
	// 		}
	// 	}
	// 	else if ( m >= 60 * 24 * 7 ){
	// 		if ( m == 60 * 24 * 7 ) {
	// 			System.out.println(a.toString() + "( 1 week later )");
	// 		}
	// 		else{
	// 			System.out.println(a.toString() + "( " + m / (60 * 24 * 7) + " weeks later )");
	// 		}
	// 	}
	// }

	public String increase(ClockTime a, int m){
		a = new ClockTime(a.getHour(), a.getMinute() + m, a.getAmPm());
		String time = a.toString();
		if(m >= 60 * 24){
			time = time.concat("(");
			if ( m >= 60 * 24 * 7 ){
				if ( m / ( 60 * 24 * 7) == 1 ){			
					time = time.concat(" 1 week");	
					m %= 60 * 24 * 7;
				} 
				if ( m / ( 60 * 24 * 7) >= 1 ){
					time = time.concat(" " + m / ( 60 * 24 * 7) + " weeks");
					m %= 60 * 24 * 7;	
				}
			}

			if ( m < 60 *24 * 7 && m >= 60 * 24){
				if ( m / ( 60 * 24 ) == 1 ){			
					time= time.concat(" 1 day ");	
				} 
				if ( m / ( 60 * 24 ) > 1 ){
					time = time.concat(" " + m / ( 60 * 24 ) + " days ");	
				}	
			}

			time = time.concat("later )");
			return time;
		}
		return time;
	}

	public boolean isWorkTime(ClockTime a){

		if ( a.getAmPm().equals("AM") ){
			if ( a.getHour() >= 9 && a.getHour() <= 12){
				return true;
			}
		}
		if (a.getAmPm().equals("PM")){
			if ( a.getHour() >= 1 && a.getHour() < 5 ){
				return true;
			}
			else if ( a.getHour() == 5 && a.getMinute() == 0){
				return true;
			} 
		}
		return false;
		
	}

	public static void main(String[] args) {
		ClockTime a = new ClockTime(13, 27, "AM");
	 	System.out.println(a.toString());
	 	WorkTime time = new WorkTime();
	 	System.out.println(time.increase(a, 34 ));
	 	System.out.println(time.isWorkTime(a));
	}

}

class ClockTime{
	
	public int hour;
	public int minute;
	public String amPm;

	ClockTime(int h, int m, String ap){
		
		if ( m >= 60 ){
			h = h + m / 60;
			m = m % 60;
		}

		if ( h > 12 && ap.equals("AM") ){
			if( (h / 12) % 2 != 0 ){
				h = h % 12;
				ap = "PM";
			}
			else{
				h %= 12;
				ap = "AM";
			}
		}
		
		if (h > 12 && ap.equals("PM")  ){
			if( (h / 12) % 2 != 0 ){
				h = h % 12;
				ap = "AM";
			}
			else{
				h %= 12;
				ap = "PM";
			}
		}
		this.hour = h;
		this.minute = m;
		this.amPm = ap;

	}

	public int getHour(){
		return hour;
	}

	public int getMinute(){

		return minute;
	}

	public String getAmPm(){
		return amPm;
	}

	public String toString(){
		if ( this.minute >= 0 && this.minute <=9  ){
			return hour + ":0" + minute + " " + amPm;	
		}
		return hour + ":" + minute + " " + amPm;
	}
}



