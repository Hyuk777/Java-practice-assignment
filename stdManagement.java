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
	private int num; // 입력 순번
	
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
	// 유효성 검사 해야한다.
	
	Node head, tail;
	int numOfData; // 리스트 내 데이터 개수
	ListStd () {
		tail = new Node (null, null);
		head = new Node (null, tail);
	}
	
	// 해당 객체 리스트 제일 끝에 추가
	void add (Student argObj) {
		add(numOfData, argObj);
	}
	
	// 해당 객체를 index 위치에 추가
	void add (int argIndex, Student argObj) {
		Node temp = head;
		
		// index -1 노드 까지 순회
		for(int i = 0; i < argIndex; i++)
			temp = temp.getNextNode();
		
		Node newNode = new Node (argObj, temp.getNextNode());
		temp.setNextNode(newNode);
		
		numOfData++;
	}
	
	// argId 학생 ID 값을 이용해 해당 객체 	반환, 없을 시 Null 반환
	Student getStudentById (int argId) {
		Node temp = head.getNextNode();
		
		// 순회하며 입력한 id값 찾기
		while(temp != tail) {
			if(temp.getStdInfo().getId() == argId) 
				return temp.getStdInfo();
			
			temp = temp.getNextNode();
		}
		return null;
	}
	
	// argId 학생 ID 값을 이용해 해당 객체 리스트 내 삭제, 없을 시 false 반환
	boolean deleteStudentById(int argId) {
		Node temp = head.getNextNode();
		Node previousNode = head.getNextNode();
		
		// 순회해서 입력받은 id의 학생 찾기
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
	
	// 현 리스트 내 Student 객체의 사이즈 반환
	int size () {
		return numOfData;
	}
	
	void stdList() {
		Node temp = head.getNextNode();
		
		System.out.println("입력순번        학번         국어    영어     수학     총점      평균     순위");
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
			temp = temp.getNextNode(); // tail 까지 순회
		}
	}
}

public class stdManagement {

	public static void main(String[] args) {
		ListStd myList = new ListStd();
		Scanner sc = new Scanner(System.in);
		int select;

		// 메뉴 출력
		do {
			System.out.println("--------------------");
			System.out.println("1. 성적 입력");
			System.out.println("2. 전체 학생 목록 출력 (입력 역순)");
			System.out.println("3. 학생 조회 후 출력");
			System.out.println("4. 학생 삭제");
			System.out.println("5. 종료");
			System.out.println("--------------------");
			System.out.print("메뉴 선택 : ");
			select = sc.nextInt();
			
			switch(select) {
			case 1:
				// 성적 입력
				Student std = new Student();
				
				System.out.println("학번을 입력 하세요:");
				while(true) {
				int inputId = sc.nextInt();
				std.setId(inputId);
				if(myList.getStudentById(inputId) != null) {
					System.out.println("등록된 학번 입니다. 재입력 하세요.");
				} else {
					System.out.println("국어 성적을 입력 하세요");
					std.setKor(sc.nextInt());
					System.out.println("영어 성적을 입력 하세요");
					std.setEng(sc.nextInt());
					System.out.println("수학 성적을 입력 하세요");
					std.setMath(sc.nextInt());
					System.out.println("입력 값 :");
					System.out.printf("학번 : %d 국어 : %d 영어 : %d 수학 : %d 총점 : %d, 평균 : %d \n",
						std.getId(), std.getKor(), std.getEng(), std.getMath(), std.getSum(), std.getAvg());
					myList.add(std);
					std.setNum(myList.size()); // 입력 순번
					break;
					}
				} break;
			case 2:
				// 전체 학생 목록 출력 (입력 역순)
				myList.stdList();
				break;
			case 3:
				// 학생 조회 후 출력
				System.out.println("검색 할 학생의 학번을 입력 하세요");
				
				while(true) {
					int findId = sc.nextInt();
					if(myList.getStudentById(findId) == null) {
						System.out.println("입력하신 학번은 없는 학번입니다. 다시 입력하세요.");
					} else {
						std = myList.getStudentById(findId);
						System.out.println("입력순번        학번         국어    영어     수학     총점      평균     순위");
						System.out.printf("  %d     %d    %d   %d    %d   %d    %d \n",
								std.getNum(), std.getId(), std.getKor(), std.getEng(), std.getMath(), std.getSum(), std.getAvg());
						break;
					} 
				} break;
			case 4:
				// 학생 삭제
				System.out.println("삭제 할 학생의 학번을 입력 하세요");
				
				int rmId = sc.nextInt();
				if(!myList.deleteStudentById(rmId))
					System.out.println("입력하신 학번은 없는 학번입니다. 다시 입력하세요.");
				else {
					myList.deleteStudentById(rmId);
					System.out.println("삭제되었습니다.");
				}
				break;
			case 5:
				// 종료
				System.out.println("5. 프로그램 종료");
				break;
			}
		} while(select != 5);
		
		
	}

}
