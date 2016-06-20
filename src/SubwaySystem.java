import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


/**
 * ;��վ�㣺����㣩��������·��̫ƽ��е������������վ����ֱ·����ͨѧԺ��̫ƽ�ţ�����3���ߣ�
 * �����̴�ѧ���̳�������4���ߣ��������֣�����5���ߣ�������ݣ�����2���ߣ�����·�֡��̻��㳡
 * �������š������԰������3���ߣ������������ѧ����������ѧ����ҽ���Ժ������4���ߣ�
 * ��ũ��Ժ����������վ��ѧ��·���Ļ������Ļ��ϡ�ƽ˳�̳ǡ��½���֣��յ㣩
 * @author Administrator
 *
 */
public class SubwaySystem {

	// ������
	Ticket ticket;
	String[] stations = new String[]{"��������·","̫ƽ��е��","��������վ","��ֱ·","��ͨѧԺ","̫ƽ��",
														"���̴�ѧ","�̳�","������","�����","��·��","�̻��㳡","������","�����԰",
														"���","����ѧ","��������ѧ","��ҽ���Ժ","ũ��Ժ","��������վ","ѧ��·",
														"�Ļ���","�Ļ���","ƽ˳�̳�","�½����"};
	List<Person> people = new ArrayList<Person>();		//�ֿ���
	Scanner scanner;
//	Timer timer;		//��ʱ��
	public SubwaySystem() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
	}
	
	public static void main(String[] args){
		
		new SubwaySystem().service();
	}
	
	//��ʼ����
	public void initPeople(){
		while(true){
			System.out.println("������ֿ��˵�����:");
			String name = scanner.next();
			System.out.println("�����뿨��:");
			String number = scanner.next();
			Ticket ticket = new Ticket();
			ticket.num = number;
			Person person = new Person(name,ticket);
			buyTicket(person);
			people.add(person);
			System.out.println("����Ҫ������ӳ˿�ô?(Y��/N��)");
			String n = scanner.next();
			if(n.equals("Y") || n.equals("y")){
				continue;
			}
			else{
				break;
			}
		}
	}
	
	//�������������Ǯ��
	public void buyTicket(Person person){

		System.out.println("����������Ҫ��ֵ�Ľ�����λ��Ԫ��");
		float balance = scanner.nextFloat();
		person.ticket.balance = balance;
	}
	
	//��վ
	public void enter(){
		
		for(Person person : people){
			if(person.ticket == null || person.ticket.balance<2){
				System.out.println(person.name+"  "+person.ticket.num+"  ����û��Ʊ�����������㣬 ���ȳ�ֵ��");
				buyTicket(person);
			}
			int index = -1;
			while(index<0 || index>= stations.length){
				System.out.println(person.name+"  "+person.ticket.num+"��ѡ����ʾվ��λ�ã�������ţ�0-"+stations.length+")");
				index = scanner.nextInt();
			}
			System.out.println(person.name+"  "+person.ticket.num+"��ѡ����������߷�������ʹ(������:1, ������:2)");
			int direct = scanner.nextInt();
			person.direct = direct;
			person.currentIndex = index;
			person.enterIndex = index;
			String name = stations[index];
			System.out.println("��վ�ɹ���"+"����:"+person.name+"����:"+person.ticket.num +"��ǰ��"+person.ticket.balance+"Ԫ");
			person.ticket.date = new Date();
			person.ticket.state = 1;
		}
	}
	
	public void enter(Person person){
		
		if(person.ticket == null || person.ticket.balance<2){
			System.out.println(person.name+"  "+person.ticket.num+"  ����û��Ʊ�����������㣬 ���ȳ�ֵ��");
			buyTicket(person);
		}
		int index = -1;
		while(index<0 || index>= stations.length){
			System.out.println(person.name+"  "+person.ticket.num+"��ѡ����ʾվ��λ�ã�������ţ�0-"+stations.length+")");
			index = scanner.nextInt();
		}
		System.out.println(person.name+"  "+person.ticket.num+"��ѡ����������߷�������ʹ(������:1, ������:2)");
		int direct = scanner.nextInt();
		person.direct = direct;
		person.currentIndex = index;
		person.enterIndex = index;
		String name = stations[index];
		System.out.println("��վ�ɹ���"+"����:"+person.name+"����:"+person.ticket.num +"��ǰ��"+person.ticket.balance+"Ԫ");
		person.ticket.date = new Date();
		person.ticket.state = 1;
	}
	
	//�н�
	public void runSubway(){
		while(true){
			
			boolean flag = false;
			for(Person person : people){
				if(person.ticket.state == 0){
					continue;
				}
				flag = true;
				if(person.currentIndex>=stations.length || person.currentIndex<=0){
					System.out.println(person.name+"  "+person.ticket.num+"���ѵ����յ㣬 ���վ:");
					outSubway(person);
					continue;
				}
				String name = stations[person.currentIndex];
				System.out.println(person.name+"  "+person.ticket.num+"���ѵ���"+name+"���Ƿ���Ҫ��վ����Y��/N��");
				String s = scanner.next();
				if(s.equals("Y") || s.equals("y")){
					//��վ
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
				System.out.println("��ѡ�����½�վ�����˳���ϵͳ(���½�վY, �˳�ϵͳN)");
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
	
	//��վ
	public void outSubway(Person person){
		
		int n = person.currentIndex - person.enterIndex;		//վ��
		System.out.println(person.name+"  "+person.ticket.num+"  ������"+n+"վ");
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
		//����Ƿ�ʱ
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
			System.out.println(person.name+"  "+person.ticket.num+"�������㣡�뵽���ڳ�ֵ");
			buyTicket(person);
		}
		person.ticket.state = 0;
		System.out.println(person.name+"  "+person.ticket.num+"��վ�ɹ��� ��"+person.ticket.balance+"Ԫ,"+"��ʼվ:"+stations[person.enterIndex] + "����վ:"+stations[person.currentIndex]);
		System.out.println(person.name+"  "+person.ticket.num+"лл���Ĺ���");
	}
	
	//ϵͳ����
	public void service(){
		
		//while(true){
			initPeople();
			enter();
			runSubway();
		//}
	}

}
