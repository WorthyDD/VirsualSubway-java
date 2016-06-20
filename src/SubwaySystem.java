import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 途经站点：（起点）东化工厂路—太平机械厂—哈尔滨东站—南直路—交通学院—太平桥（换乘3号线）
 * —工程大学—烟厂（换乘4号线）—龙江街（换乘5号线）—博物馆（换乘2号线）—铁路局—教化广场
 * —西大桥—清滨公园（换乘3号线）—电表厂—理工大学—黑龙江大学—哈医大二院（换乘4号线）
 * —农科院—哈尔滨南站—学府路—四环北—四环南—平顺商城—新疆大街（终点）
 * @author Administrator
 *
 */
public class SubwaySystem {

	// 地铁卡
	Ticket ticket;
	String[] stations = new String[]{"东化工厂路","太平机械厂","哈尔滨东站","南直路","交通学院","太平桥",
														"工程大学","烟厂","龙江街","博物馆","铁路局","教化广场","西大桥","清滨公园",
														"电表厂","理工大学","黑龙江大学","哈医大二院","农科院","哈尔滨南站","学府路",
														"四环北","四环南","平顺商城","新疆大街"};
	List<Person> people = new ArrayList<Person>();		//持卡人
	Scanner scanner;
//	Timer timer;		//计时器
	public SubwaySystem() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args){
		
		new SubwaySystem().service();
	}
	
	//初始化人
	public void initPeople(){
		while(true){
			System.out.println("请输入持卡人的姓名:");
			String name = scanner.next();
			System.out.println("请输入卡号:");
			String number = scanner.next();
			Ticket ticket = new Ticket();
			ticket.num = number;
			Person person = new Person(name,ticket);
			buyTicket(person);
			people.add(person);
			System.out.println("还需要继续添加乘客么?(Y是/N否)");
			String n = scanner.next();
			if(n.equals("Y") || n.equals("y")){
				continue;
			}
			else{
				break;
			}
		}
	}
	
	//购买地铁卡（充钱）
	public void buyTicket(Person person){

		System.out.println("请输入你想要充值的金额：（单位：元）");
		float balance = scanner.nextFloat();
		person.ticket.balance = balance;
	}
	
	//进站
	public void enter(){
		
		for(Person person : people){
			if(person.ticket == null || person.ticket.balance<2){
				System.out.println(person.name+"  "+person.ticket.num+"  您还没买票或者您的余额不足， 请先充值！");
				buyTicket(person);
			}
			int index = -1;
			while(index<0 || index>= stations.length){
				System.out.println(person.name+"  "+person.ticket.num+"请选择启示站点位置：输入序号（0-"+stations.length+")");
				index = scanner.nextInt();
			}
			System.out.println(person.name+"  "+person.ticket.num+"请选择正方向或者反方向行使(正方向:1, 反方向:2)");
			int direct = scanner.nextInt();
			person.direct = direct;
			person.currentIndex = index;
			person.enterIndex = index;
			String name = stations[index];
			System.out.println("进站成功："+"姓名:"+person.name+"卡号:"+person.ticket.num +"当前余额："+person.ticket.balance+"元");
			person.ticket.date = new Date();
			person.ticket.state = 1;
		}
	}
	
	public void enter(Person person){
		
		if(person.ticket == null || person.ticket.balance<2){
			System.out.println(person.name+"  "+person.ticket.num+"  您还没买票或者您的余额不足， 请先充值！");
			buyTicket(person);
		}
		int index = -1;
		while(index<0 || index>= stations.length){
			System.out.println(person.name+"  "+person.ticket.num+"请选择启示站点位置：输入序号（0-"+stations.length+")");
			index = scanner.nextInt();
		}
		System.out.println(person.name+"  "+person.ticket.num+"请选择正方向或者反方向行使(正方向:1, 反方向:2)");
		int direct = scanner.nextInt();
		person.direct = direct;
		person.currentIndex = index;
		person.enterIndex = index;
		String name = stations[index];
		System.out.println("进站成功："+"姓名:"+person.name+"卡号:"+person.ticket.num +"当前余额："+person.ticket.balance+"元");
		person.ticket.date = new Date();
		person.ticket.state = 1;
	}
	
	//行进
	public void runSubway(){
		while(true){
			
			boolean flag = false;
			for(Person person : people){
				if(person.ticket.state == 0){
					continue;
				}
				flag = true;
				if(person.currentIndex>=stations.length || person.currentIndex<=0){
					System.out.println(person.name+"  "+person.ticket.num+"您已到达终点， 请出站:");
					outSubway(person);
					continue;
				}
				String name = stations[person.currentIndex];
				System.out.println(person.name+"  "+person.ticket.num+"您已到达"+name+"，是否需要出站：（Y是/N否）");
				String s = scanner.next();
				if(s.equals("Y") || s.equals("y")){
					//出站
					outSubway(person);
					continue;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(person.direct == 1){
					person.currentIndex++;
				}
				else if(person.direct == 2){
					person.currentIndex--;
				}
			}
			if(!flag){
				System.out.println("请选择重新进站或者退出本系统(重新进站Y, 退出系统N)");
				String n = scanner.next();
				if(n.equals("Y") || n.equals("y")){
					enter();
					runSubway();
					return;
				}
				else{
					System.exit(0);
				}
			}
			
		}
	}
	
	//出站
	public void outSubway(Person person){
		
		int n = person.currentIndex - person.enterIndex;		//站数
		System.out.println(person.name+"  "+person.ticket.num+"  共经过"+n+"站");
		float cost = 0;
		if(n<=4){
			cost = 2;
		}
		else if(n <= 8){
			cost = 3;
		}
		else{
			cost = 4;
		}
		
		person.ticket.balance -= cost;
		//检测是否超时
		person.ticket.seconds = new Date().getTime()-person.ticket.date.getTime();
		if(person.currentIndex == person.enterIndex){
			if(person.ticket.seconds<3600){
				person.ticket.balance -= 2;
			}
			else if(person.ticket.seconds <= 3600*2){
				person.ticket.balance -= 3;
			}
			else{
				person.ticket.balance -= 4;
			}
		}
		
		if(person.ticket.balance < 0){
			System.out.println(person.name+"  "+person.ticket.num+"您的余额不足！请到窗口充值");
			buyTicket(person);
		}
		person.ticket.state = 0;
		System.out.println(person.name+"  "+person.ticket.num+"出站成功， 余额："+person.ticket.balance+"元,"+"起始站:"+stations[person.enterIndex] + "到达站:"+stations[person.currentIndex]);
		System.out.println(person.name+"  "+person.ticket.num+"谢谢您的光临");
	}
	
	//系统服务
	public void service(){
		
		//while(true){
			initPeople();
			enter();
			runSubway();
		//}
	}

}
