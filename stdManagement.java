package test6;

import java.util.Scanner;

class Node {
	private Node 	nextNode;
	private Student stdInfo;
	
	Node (Student argObj, Node argNextNode)
	{
		nextNode = argNextNode;
		stdInfo	 = argObj;
	}
	
	Node 	getNextNode() 				  	  { return nextNode; }
	Student getStdInfo() 					  { return stdInfo; }
	void 	setNextNode(Node argNextNode) 	  { nextNode = argNextNode; }
	void 	setStdInfo(Student argObj) 	  	  { stdInfo = argObj; }
}

class Student {
	private int id;
	private int kor, eng, math;
	private int sum;
	private int avg;
	private int rank;
	private int num; // �Է� ����
	
	public int 	getId() 		   	 { return id; }
	public void setId(int argId)   	 { id = argId; } 
	public int 	getKor() 		   	 { return kor; }
	public void setKor(int argKor) 	 { kor = argKor; }
	public int 	getEng() 		   	 { return eng; }
	public void setEng(int argEng) 	 { eng = argEng; }
	public int 	getMath() 		     { return math; }
	public void setMath(int argMath) { math = argMath; }
	public int 	getSum() { 
		sum = kor+eng+math;
		return sum;
	}
	public int  getAvg() { 
		avg = sum / 3; 
		return avg; 
	}
	public int  getNum() 			 { return num; }
	public void setNum(int argNum) 	 { num = argNum; }
}

class ListStd {
	// ��ȿ�� �˻� �ؾ��Ѵ�.
	
	Node head, tail;
	int numOfData; // ����Ʈ �� ������ ����
	ListStd () {
		tail = new Node (null, null);
		head = new Node (null, tail);
	}
	
	// �ش� ��ü ����Ʈ ���� ���� �߰�
	void add (Student argObj) {
		add(numOfData, argObj);
	}
	
	// �ش� ��ü�� index ��ġ�� �߰�
	void add (int argIndex, Student argObj) {
		Node temp = head;
		
		// index -1 ��� ���� ��ȸ
		for(int i = 0; i < argIndex; i++)
			temp = temp.getNextNode();
		
		Node newNode = new Node (argObj, temp.getNextNode());
		temp.setNextNode(newNode);
		
		numOfData++;
	}
	
	// argId �л� ID ���� �̿��� �ش� ��ü 	��ȯ, ���� �� Null ��ȯ
	Student getStudentById (int argId) {
		Node temp = head.getNextNode();
		
		// ��ȸ�ϸ� �Է��� id�� ã��
		while(temp != tail) {
			if(temp.getStdInfo().getId() == argId) 
				return temp.getStdInfo();
			
			temp = temp.getNextNode();
		}
		return null;
	}
	
	// argId �л� ID ���� �̿��� �ش� ��ü ����Ʈ �� ����, ���� �� false ��ȯ
	boolean deleteStudentById(int argId) {
		Node temp = head.getNextNode();
		Node previousNode = head.getNextNode();
		
		// ��ȸ�ؼ� �Է¹��� id�� �л� ã��
		while(temp != tail) {
			if(temp.getStdInfo().getId() == argId) {
				previousNode.setNextNode(temp);   
				temp = null;					  
				
				return true;
			}
			previousNode = temp;
			temp = temp.getNextNode();
		}
		return false;
	}
	
	// �� ����Ʈ �� Student ��ü�� ������ ��ȯ
	int size () {
		return numOfData;
	}
	
	void stdList() {
		Node temp = head.getNextNode();
		
		System.out.println("�Է¼���        �й�         ����    ����     ����     ����      ���     ����");
		while(temp != tail) {
			System.out.printf("  %d     %d    %d   %d    %d   %d    %d \n",
					temp.getStdInfo().getNum(), temp.getStdInfo().getId(), temp.getStdInfo().getKor(), temp.getStdInfo().getEng(), temp.getStdInfo().getMath(), temp.getStdInfo().getSum(), temp.getStdInfo().getAvg());
			
			temp = temp.getNextNode();
		}
	}
	
	void stdRank() {
		Node temp = head.getNextNode();
		
		while(temp != tail) {
				temp.getStdInfo().getAvg();
			temp = temp.getNextNode(); // tail ���� ��ȸ
		}
	}
}

public class stdManagement {

	public static void main(String[] args) {
		ListStd myList = new ListStd();
		Scanner sc = new Scanner(System.in);
		int select;

		// �޴� ���
		do {
			System.out.println("--------------------");
			System.out.println("1. ���� �Է�");
			System.out.println("2. ��ü �л� ��� ��� (�Է� ����)");
			System.out.println("3. �л� ��ȸ �� ���");
			System.out.println("4. �л� ����");
			System.out.println("5. ����");
			System.out.println("--------------------");
			System.out.print("�޴� ���� : ");
			select = sc.nextInt();
			
			switch(select) {
			case 1:
				// ���� �Է�
				Student std = new Student();
				
				System.out.println("�й��� �Է� �ϼ���:");
				while(true) {
				int inputId = sc.nextInt();
				std.setId(inputId);
				if(myList.getStudentById(inputId) != null) {
					System.out.println("��ϵ� �й� �Դϴ�. ���Է� �ϼ���.");
				} else {
					System.out.println("���� ������ �Է� �ϼ���");
					std.setKor(sc.nextInt());
					System.out.println("���� ������ �Է� �ϼ���");
					std.setEng(sc.nextInt());
					System.out.println("���� ������ �Է� �ϼ���");
					std.setMath(sc.nextInt());
					System.out.println("�Է� �� :");
					System.out.printf("�й� : %d ���� : %d ���� : %d ���� : %d ���� : %d, ��� : %d \n",
						std.getId(), std.getKor(), std.getEng(), std.getMath(), std.getSum(), std.getAvg());
					myList.add(std);
					std.setNum(myList.size()); // �Է� ����
					break;
					}
				} break;
			case 2:
				// ��ü �л� ��� ��� (�Է� ����)
				myList.stdList();
				break;
			case 3:
				// �л� ��ȸ �� ���
				System.out.println("�˻� �� �л��� �й��� �Է� �ϼ���");
				
				while(true) {
					int findId = sc.nextInt();
					if(myList.getStudentById(findId) == null) {
						System.out.println("�Է��Ͻ� �й��� ���� �й��Դϴ�. �ٽ� �Է��ϼ���.");
					} else {
						std = myList.getStudentById(findId);
						System.out.println("�Է¼���        �й�         ����    ����     ����     ����      ���     ����");
						System.out.printf("  %d     %d    %d   %d    %d   %d    %d \n",
								std.getNum(), std.getId(), std.getKor(), std.getEng(), std.getMath(), std.getSum(), std.getAvg());
						break;
					} 
				} break;
			case 4:
				// �л� ����
				System.out.println("���� �� �л��� �й��� �Է� �ϼ���");
				
				int rmId = sc.nextInt();
				if(!myList.deleteStudentById(rmId))
					System.out.println("�Է��Ͻ� �й��� ���� �й��Դϴ�. �ٽ� �Է��ϼ���.");
				else {
					myList.deleteStudentById(rmId);
					System.out.println("�����Ǿ����ϴ�.");
				}
				break;
			case 5:
				// ����
				System.out.println("5. ���α׷� ����");
				break;
			}
		} while(select != 5);
		
		
	}

}
