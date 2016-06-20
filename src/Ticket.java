import java.util.Date;

public class Ticket {

	public String num;		//卡编号
	public float balance;	//  余额
	public long seconds;		// 已进站的秒数
	public Date date;		//进站的时间 
	public int state;			//状态  0 站外   1 站内
}
