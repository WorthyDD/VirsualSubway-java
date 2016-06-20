
public class Person {

	public String name;		//姓名	
	public Ticket ticket;	//卡
	public int direct;		//乘车方向 1 正方向  2 反方向
	public int enterIndex;		//起始站
	public int currentIndex;    //当前站
	Person(String name, Ticket ticket){
		this.name = name;
		this.ticket = ticket;
		this.direct = 1;
	}
}
