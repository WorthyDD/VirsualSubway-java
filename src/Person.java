
public class Person {

	public String name;		//����	
	public Ticket ticket;	//��
	public int direct;		//�˳����� 1 ������  2 ������
	public int enterIndex;		//��ʼվ
	public int currentIndex;    //��ǰվ
	Person(String name, Ticket ticket){
		this.name = name;
		this.ticket = ticket;
		this.direct = 1;
	}
}
