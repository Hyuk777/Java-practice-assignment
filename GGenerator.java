package test9;

import java.util.Scanner;

class RawData {
	String m_stdId[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
						"11", "12", "13", "14", "15", "16", "17", "18"};
	
	String m_stdName[] = { "�ڳ���",	"������",	"���켺",	"�豹��", "������", "��ȣ��",
			               "�̼���",	"�ǿ�",	"����",	"������", "������", "����",	
			               "������", "ä��", "��û", "�����", "������", "��â��"};
	
	double m_stdGrade[] = {15, 12, 11, 10, 9, 8, 7, 7, 7, 6, 6, 6, 6, 6, 5, 5, 4, 3};
	
	String m_stdGender[] = {"��", "��", "��", "��", "��", "��", "��", "��", "��",
							"��", "��", "��", "��", "��", "��", "��", "��", "��"};									
}

// �л� ���� ���� Ŭ���� (StdInfo ��ü �� ���� �� �� �л� ��Ÿ��)
class StdInfo {
	// �� �� �л��� �й�, �̸�, ����, ���� ����
	private String id;
	private String name;
	private double grade;
	private String gender;
	
	String getId() 		{ return id; }
	String getName() 	{ return name; }
	double getGrade() 	{ return grade; }
	String getGender() 	{ return gender; }
	
	void setId(String argId) 		 { id = argId; }
	void setName(String argName) 	 { name = argName; }
	void setGrade(double argGrade)   { grade = argGrade; }
	void setGender(String argGender) { gender = argGender; }
}

class Node {
	private StdInfo stdValue;
	private Node nextNode;
	
	// Node ���� �� �л���ü, ���� ��� �ʱ�ȭ
	Node(StdInfo argValue, Node argNextNode) {
		stdValue = argValue;
		nextNode = argNextNode;
	}
	
	StdInfo getStdInfo() 		{ return stdValue; }
	Node	getNextNode() 		{ return nextNode; }
	void setStdInfo(StdInfo argValue)  { stdValue = argValue; }
	void setNextNode(Node argNextNode) { nextNode = argNextNode; }
}

// Linked-List Version ����Ʈ 
class MyList { 
	Node head, tail;
	MyList() {
		tail = new Node(null, null);
		head = new Node (null, tail);
	}
	int numOfData; // ����Ʈ �� ������ ����
	
	// index�� ��ȿ�� �˻�
	boolean isValidIndex(int argIndex) {
		// index�� �� ������ �������� ũ�ų� ������ ��� true
		if(argIndex > numOfData || argIndex < 0)
			return true;
		
		return false;
	}
	
	// StdInfo getStdById(String argId) : List �� argID �Է� ���� ��Ī�Ǵ� �л� ��ü(StdInfo) ��ȯ
	//                                    ��Ī�Ǵ� �л��� ���� ��� Null ��ȯ
	StdInfo getStdById(String argId) {
		Node temp = head.getNextNode();
		
		// tail ������ ��ȸ�ϸ鼭 �Է¹��� id�� �л� ��ü�� id�� ��ġ��  ��� �ش� �л���ü ����
		while(temp != tail) {
			if(temp.getStdInfo().getId() == argId)
				return temp.getStdInfo();
		}
		return null;
	}

	// StdInfo get(int argIndex) : List �� argIndex ������ �ش��ϴ� StdInfo ��ü ��ȯ
	//                             index �� ��ȿ�� �˻� �ǽ� [�Է� ���� -1 ���� �̰ų�, List �� ���� ������ ������ Ŭ���]
	StdInfo get(int argIndex) {
		// index ��ȿ�� �˻� -> ��ȿ���� ���� ��� null ��ȯ
		if(isValidIndex(argIndex))
			return null;
		
		Node temp = head;
		// �Է¹��� index ���� ��ȸ�ϸ鼭 �ش� �ε����� ��ġ�ϴ� �л� ��ü ��ȯ
		for (int i = 0; i <= argIndex; i++) 
			temp = temp.getNextNode();
		
		return temp.getStdInfo();
	}

	// StdInfo remove(int argIndex) : List �� argIndex ������ �ش��ϴ� StdInfo ��ü�� List �� ���� �� �ش� ��ü ��ȯ
	//                                index �� ��ȿ�� �˻� �ǽ� [�Է� ���� -1 ���� �̰ų�, List �� ���� ������ ������ Ŭ���]
	StdInfo remove(int argIndex) {
		// index ��ȿ�� �˻� �ǽ�
		if(isValidIndex(argIndex))
			return null;
		
		Node temp = head.getNextNode();
		Node previousNode = head;	// ������带 ������ ����
		StdInfo value;
		
		// �Է� ���� index ���� ��ȸ
		for (int i = 0; i < argIndex; i++) 	
			temp = temp.getNextNode(); // �Է¹��� index�� node��ü ȹ��
		
			previousNode.setNextNode(temp.getNextNode()); // ������尡 �����ϴ� ���� ��带 �� ����� ���� ��带 ������ => �������   ������    �������  �� ���     �������, ������ -> �������
			value = temp.getStdInfo(); 					  // ���� ����� �л� ��ü ȹ�� 
			temp = null; 			   					  // ���� ��尡 �����ϴ� ���� ��带 ����
			numOfData--; 							      // ����Ʈ �� ������ ���� -1
			return value; 								  // ���� index��ġ�� �л� ��ü ��ȯ
	}
	
	
	// void add(StdInfo argStdInfo) : List ���� �������� argStdInfo ��ü ����
	void add(StdInfo argStdInfo) {
		Node temp = head;
		
		// tail ������ ��ȸ�ϸ鼭 ���� ������ ��尴ü ȹ��
		while (temp.getNextNode() != tail) 
			temp = temp.getNextNode();
		
		Node newNode = new Node (argStdInfo, tail); // �� ��尡 tail�� ����Ŵ���� ���� ������ ��ġ�� �л� ��ü ����
		temp.setNextNode(newNode);					// ���� �������� �־��� ��尴ü�� ���� ���� newNode�� ����Ŵ
		numOfData++;								// ����Ʈ �� ������ ���� +1
	}
	
	// int size() : �� ����Ʈ �� ������ ���� ��ȯ
	int size() {
		return numOfData;	// ����Ʈ �� ������ ���� ��ȯ
	}
	
	// int sizeOfFemaleStd() : �� ����Ʈ �� ���л� ��� ��ȯ
	int sizeOfFemaleStd() {
		Node temp = head.getNextNode();
		int femaleCount = 0;
		
		// ����Ʈ�� ��ȸ�ϸ鼭 �ش� ����� �л���ü�� �ͺ��� ������ ��� ī��Ʈ
		while(temp != tail) {
			if(temp.getStdInfo().getGender() == "��")
				femaleCount++;
			
			temp = temp.getNextNode();
		}
		return femaleCount;
	}
	
	// int sizeOfMaleStd() : �� ����Ʈ �� ���л� ��� ��ȯ
	int sizeOfMaleStd() {
		Node temp = head.getNextNode();
		int maleCount = 0;
		
		// ����Ʈ�� ��ȸ�ϸ鼭 �ش� ����� �л���ü�� �ͺ��� ������ ��� ī��Ʈ
		while(temp != tail) {
			if(temp.getStdInfo().getGender() == "��")
				maleCount++;
			
			temp = temp.getNextNode();
		}
		return maleCount;
	}
}

// �� �� Ŭ����
class GroupManager {
	int		m_numOfGroup;   // ������ �׷� �� : �����(CLI)�κ��� �Է�
	MyList	rawDataList; 	// �л� ���� ��ü(StdInfo)���� �����ϴ� ����Ʈ
	
	Scanner sc = new Scanner(System.in);
	RawData rawdata; 	 	// �л� ���� rawData 
	StdInfo std; 	 		// �л� ��ü 

	MyList femaleList; 		// ���л� ��ü ���� MyList
	MyList maleList;		// ���л� ��ü ���� MyList
	
	// ������
	GroupManager() {
		// rawDataList ����Ʈ�� �л�(StdInfo)��ü �Է�
		LoadData();
	}
	
	// �л� ���� ��üȭ �� ����Ʈ�� ����
	void LoadData() {
		// RawData Ŭ���� ��� ������ �ڷ����
		// �� StdInfo ��ü�� ���� �� rawDataList ����Ʈ�� �߰�
		rawDataList = new MyList();
		rawdata 	= new RawData();
		
		// rawdata�� �л� ����ŭ RawData Ŭ���� ��� ������ �ڷ���� �� StdInfo ��ü�� ���� �� rawDataList�� �߰�
		for(int i=0; i < rawdata.m_stdId.length; i++) {
			std = new StdInfo();
			rawdata = new RawData();
			std.setId(rawdata.m_stdId[i]);
			std.setName(rawdata.m_stdName[i]);
			std.setGrade(rawdata.m_stdGrade[i]);
			std.setGender(rawdata.m_stdGender[i]);
			
			rawDataList.add(std); 
		}
		
		// ����Ʈ rawDataList�� �Է� �� �л� ���� ȭ�鿡 ���
		PrtStdList();
	}
	
	// rawDataList �� �л� ����(StdInfo ��ü)�� ȭ�鿡 ���
	void PrtStdList() {
		// ȭ�� ��� ���� : �̸�, �й�, ����, ���� ������ ��� (�Ʒ� ����)
		// 1) �ڳ���, 1, "��", 10
		// 2) ������, 2, "��", 10
		for(int i=0; i < rawDataList.size(); i++) {
			StdInfo std = rawDataList.get(i);
			System.out.printf("%d) %s, %s, %s, %.0f\n",
					i+1, std.getName(), std.getId(), std.getGender(), std.getGrade());
		}
	}
	// �������� �׷��� �����Ͽ� ȭ�鿡 ���
	void GenerateGroup() {
		// ���� �׷� ����  ����ڷκ��� �Է�
		System.out.println("������ �׷� ������ �Է� ���ּ���.");
		m_numOfGroup = sc.nextInt(); 

		// ���л� ��ü�� ���� ����Ʈ
		femaleList = new MyList(); // ���л� ��ü�� ������ ����Ʈ
		
		// ��� �л� ��ü�� ��� rawDataList���� ������ ������ ��� femaleList�� �ش� ��ü �߰�
		for(int i=0; i < rawDataList.size(); i++) {
			if (rawDataList.get(i).getGender() == "��") {
				femaleList.add(rawDataList.get(i));
			}
		}
		
		// ���л� ��ü�� ���� ����Ʈ
		maleList = new MyList(); // ���л� ��ü�� ������ ����Ʈ
		// ��� �л� ��ü�� ��� rawDataList���� ������ ������ ��� maleList�� �ش� ��ü �߰�
		for(int i=0; i < rawDataList.size(); i++) {
			if (rawDataList.get(i).getGender() == "��") {
				maleList.add(rawDataList.get(i));
			}
		}
		
		// �Ʒ� ��Ģ�� �����Ͽ� �׷� ����
		// 1) ���л� �� ���� ������ �� �׷쿡 �����ϰ� ���� :    
		//    ��) ���� �׷� : 3, ���������� 1~3���� �л����� �����ϰ� 1, 2, 3���� ��
		//    Math.Random() �޼��� �̿� : 0~2 ���� �� ���� : (int)(Math.Random() * 3)
		// 2) ��� ���л��� ���� �ɶ����� 1) �ݺ�
		
		// ����ڷ� ���� �Է¹��� ����ŭ �׷� �迭 ����
		MyList group[] = new MyList[m_numOfGroup];
		for(int i=0; i < group.length; i++) {
			group[i] = new MyList();
		}
		
		// 1. �׷�� �޾Ƽ� ���� �迭 ����
		// 2. ���� �迭�� ������ ���� �ʱ�ȭ �� 
		// 3. ������ ���� �Լ��� �Ἥ ���� ���� -> ������ �����ϴ��� ���ϴ��� �ߺ�����
		// 4. �ߺ������� ������ �迭�� ����
		// 5. ���� ����Ʈ�� �ε��� ��ȣ�� �� 
		// 6. �Է��� �׷����ŭ ���� ��ü �׷� �迭�� �߰�
		// 7. �׷� ����ŭ remove
		// 8. while ������ ��� ���л� ���� �� ���� �ݺ�
		while(maleList.size() > maleList.size() % m_numOfGroup) {
			int ranList[] = new int[m_numOfGroup]; 
			for(int i = 0; i < ranList.length; i++) { // �����Լ� -1�� �ʱ�ȭ -> �ߺ��� ���ϱ� ����
				ranList[i] = -1; 
			}
			
			Loop:
			for(int i=0; i < ranList.length; i++) {
				int value = (int)(Math.random() * m_numOfGroup); // ������ �׷� �� ������ ���� �� ����
				for(int j=0; j < ranList.length; j++) {
					if(ranList[j] == value) { // ���� ���� ���� �迭�� �ߺ��� ���� ��� continue Loop label�� ���� �ٽ� Loop������ ���ư�;
						i--;
						continue Loop;
					}
				}
				ranList[i] = value; // �ߺ��� ���� ���� ���� �迭�� ����
			}
			
			// ���л��� ������ �׷쿡 �������� ����
			for(int i=0; i < m_numOfGroup; i++) { // �Է¹��� �׷����ŭ �ݺ�
				if(maleList.size() > 0) { // ���л� ����Ʈ ���� �����Ͱ� ������ �׷쿡 �������� ���л� ��ü ����
					group[i].add(maleList.get(ranList[i]));  
				}
			}
			// �׷쿡 �߰��� 0���� �ε����� ���л� ��ü ���� �ϸ鼭 ���� ���л� ��ü �ҷ���
			for(int j = 0; j < ranList.length; j++) {
				maleList.remove(0);
			}
		}
		
		// 3) ���л� ���ڰ� ���� �׷� ���� �¾� �������� ���� ���, ���� �л����� �� ���� ������ ���� ������ ����
		//    ��) �� �л� �� : 9��, ���� �׷� : 2, 8����� ���� �� ���� ���� ���� -> 1�� ���� : 80��, 2�� ���� : 90��
		//       -> 1�� �л����� �� ������ �������� ������ 1����  1���� ����
		while(maleList.size() > 0) {
			int g_num = 0; // �׷� ��ȣ
			int g_low = 0; // �׷캰 ���� ������ ���� ��
			int g_sum[] = new int[m_numOfGroup]; // �׷캰 ���� ���
			
			for(int i=0; i < group.length; i++)
				for(int j=0; j < group[i].size(); j++)
					g_sum[i] += group[i].get(j).getGrade(); // �׷캰 ���� ���
			
			g_low = g_sum[0]; // ������ ���� ���� �� 
			
			for(int i=0; i < g_sum.length; i++) {
				if(g_low > g_sum[i]) { // ���� ���� ���� ���պ��� ũ�� �ش� ������ ���� ������ ����
					g_low = g_sum[i];
					g_num = i;
				}
			}
			group[g_num].add(maleList.get(0)); // ������ ���� �׷쿡 ���� ���л� ����
			maleList.remove(0);
		}
		
		// 4) ���л��� ��� 1������ ���ʴ�� Round Robin ������� ����
        //    ��) ���� �׷� 3, ���л� �� 5�� : 1, 2, 3, 1, 2�� ������ ����
		while(femaleList.size() > 0) { 					// ���л��� ���� �׷쿡 �����ɋ����� �ݺ�
			for(int i=0; i < m_numOfGroup; i++) { 		// �Է¹��� �׷� ����ŭ �ݺ�
				if(femaleList.size() > 0) {  			// ���л� ��ü�� 0�� �ƴϸ� 
					group[i].add(femaleList.get(0));  	// ������ �׷�迭�� ���л� �߰�
					femaleList.remove(0);				// �߰��� ���л� ��ü�� ���������ν� ���� ����� ���л� ��ü�� �׷쿡 �߰�
				}
			}
		}
		
		// ���� ��� ȭ�� ���
		System.out.printf("���� �׷� ���� : %d \n", m_numOfGroup);
		System.out.println("���л� �� : " + rawDataList.sizeOfMaleStd());
		System.out.println("���л� �� : " + rawDataList.sizeOfFemaleStd());
		System.out.println("�� �л� �� : " + rawDataList.size());
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println("                   �� �� ���");
		System.out.println("-------------------------------");
		// ��� ������ ������ ����
		for(int i = 0; i < m_numOfGroup; i++) {
			System.out.print("\t");
	         System.out.print(i+1 + " �� ��� ���\n");
	         for(int j = 0; j < group[i].size(); j++) {
	        	 System.out.print("\t\t");
	            System.out.print(j+1 + ") �й� : " + group[i].get(j).getId() + "\t �̸� : " + group[i].get(j).getName() + "\t ���� : " + group[i].get(j).getGender()+"\n");
	         }
	         System.out.println();
	      }
		
	}
}

public class GGenerator {
	public static void main(String args[]) {
		GroupManager myGrpMgr = new GroupManager();
		// �� �� ���α׷� ����
		myGrpMgr.GenerateGroup();	
	}
}
