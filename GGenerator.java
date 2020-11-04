package test9;

import java.util.Scanner;

class RawData {
	String m_stdId[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
						"11", "12", "13", "14", "15", "16", "17", "18"};
	
	String m_stdName[] = { "박나래",	"이정재",	"정우성",	"김국진", "하춘하", "강호동",
			               "이수근",	"피오",	"공유",	"곽도원", "고은아", "규현",	
			               "전지현", "채연", "김청", "김수지", "전영록", "구창모"};
	
	double m_stdGrade[] = {15, 12, 11, 10, 9, 8, 7, 7, 7, 6, 6, 6, 6, 6, 5, 5, 4, 3};
	
	String m_stdGender[] = {"여", "남", "남", "남", "여", "남", "남", "남", "남",
							"남", "여", "남", "여", "여", "여", "여", "남", "남"};									
}

// 학생 정보 저장 클래스 (StdInfo 객체 한 개가 한 명 학생 나타냄)
class StdInfo {
	// 한 명 학생의 학번, 이름, 점수, 성별 저장
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
	
	// Node 생성 시 학생객체, 다음 노드 초기화
	Node(StdInfo argValue, Node argNextNode) {
		stdValue = argValue;
		nextNode = argNextNode;
	}
	
	StdInfo getStdInfo() 		{ return stdValue; }
	Node	getNextNode() 		{ return nextNode; }
	void setStdInfo(StdInfo argValue)  { stdValue = argValue; }
	void setNextNode(Node argNextNode) { nextNode = argNextNode; }
}

// Linked-List Version 리스트 
class MyList { 
	Node head, tail;
	MyList() {
		tail = new Node(null, null);
		head = new Node (null, tail);
	}
	int numOfData; // 리스트 내 데이터 개수
	
	// index값 유효성 검사
	boolean isValidIndex(int argIndex) {
		// index가 총 데이터 개수보다 크거나 음수일 경우 true
		if(argIndex > numOfData || argIndex < 0)
			return true;
		
		return false;
	}
	
	// StdInfo getStdById(String argId) : List 내 argID 입력 값과 매칭되는 학생 객체(StdInfo) 반환
	//                                    매칭되는 학생이 없을 경우 Null 반환
	StdInfo getStdById(String argId) {
		Node temp = head.getNextNode();
		
		// tail 전까지 순회하면서 입력받은 id와 학생 객체의 id와 일치할  경우 해당 학생객체 리턴
		while(temp != tail) {
			if(temp.getStdInfo().getId() == argId)
				return temp.getStdInfo();
		}
		return null;
	}

	// StdInfo get(int argIndex) : List 내 argIndex 순서에 해당하는 StdInfo 객체 반환
	//                             index 값 유효성 검사 실시 [입력 값이 -1 이하 이거나, List 내 저장 데이터 값보다 클경우]
	StdInfo get(int argIndex) {
		// index 유효성 검사 -> 유효하지 않을 경우 null 반환
		if(isValidIndex(argIndex))
			return null;
		
		Node temp = head;
		// 입력받은 index 까지 순회하면서 해당 인덱스에 위치하는 학생 객체 반환
		for (int i = 0; i <= argIndex; i++) 
			temp = temp.getNextNode();
		
		return temp.getStdInfo();
	}

	// StdInfo remove(int argIndex) : List 내 argIndex 순서에 해당하는 StdInfo 객체를 List 내 삭제 후 해당 객체 반환
	//                                index 값 유효성 검사 실시 [입력 값이 -1 이하 이거나, List 내 저장 데이터 값보다 클경우]
	StdInfo remove(int argIndex) {
		// index 유효성 검사 실시
		if(isValidIndex(argIndex))
			return null;
		
		Node temp = head.getNextNode();
		Node previousNode = head;	// 이전노드를 저장할 변수
		StdInfo value;
		
		// 입력 받은 index 까지 순회
		for (int i = 0; i < argIndex; i++) 	
			temp = temp.getNextNode(); // 입력받은 index의 node객체 획득
		
			previousNode.setNextNode(temp.getNextNode()); // 이전노드가 참조하는 다음 노드를 현 노드의 다음 노드를 참조함 => 이전노드   현재노드    다음노드  일 경우     이전노드, 현재노드 -> 다음노드
			value = temp.getStdInfo(); 					  // 현재 노드의 학생 객체 획득 
			temp = null; 			   					  // 현재 노드가 참조하는 다음 노드를 끊음
			numOfData--; 							      // 리스트 내 데이터 개수 -1
			return value; 								  // 현재 index위치의 학생 객체 반환
	}
	
	
	// void add(StdInfo argStdInfo) : List 제일 마지막에 argStdInfo 객체 삽입
	void add(StdInfo argStdInfo) {
		Node temp = head;
		
		// tail 전까지 순회하면서 가장 마지막 노드객체 획득
		while (temp.getNextNode() != tail) 
			temp = temp.getNextNode();
		
		Node newNode = new Node (argStdInfo, tail); // 새 노드가 tail을 가리킴으로 제일 마지막 위치에 학생 객체 삽입
		temp.setNextNode(newNode);					// 원래 마지막에 있었던 노드객체가 새로 만든 newNode를 가리킴
		numOfData++;								// 리스트 내 데이터 개수 +1
	}
	
	// int size() : 현 리스트 내 데이터 개수 반환
	int size() {
		return numOfData;	// 리스트 내 데이터 개수 반환
	}
	
	// int sizeOfFemaleStd() : 현 리스트 내 여학생 명수 반환
	int sizeOfFemaleStd() {
		Node temp = head.getNextNode();
		int femaleCount = 0;
		
		// 리스트를 순회하면서 해당 노드의 학생객체의 셩별이 여자일 경우 카운트
		while(temp != tail) {
			if(temp.getStdInfo().getGender() == "여")
				femaleCount++;
			
			temp = temp.getNextNode();
		}
		return femaleCount;
	}
	
	// int sizeOfMaleStd() : 현 리스트 내 남학생 명수 반환
	int sizeOfMaleStd() {
		Node temp = head.getNextNode();
		int maleCount = 0;
		
		// 리스트를 순회하면서 해당 노드의 학생객체의 셩별이 남자일 경우 카운트
		while(temp != tail) {
			if(temp.getStdInfo().getGender() == "남")
				maleCount++;
			
			temp = temp.getNextNode();
		}
		return maleCount;
	}
}

// 조 편성 클래스
class GroupManager {
	int		m_numOfGroup;   // 생성할 그룹 수 : 사용자(CLI)로부터 입력
	MyList	rawDataList; 	// 학생 정보 객체(StdInfo)들을 저장하는 리스트
	
	Scanner sc = new Scanner(System.in);
	RawData rawdata; 	 	// 학생 정보 rawData 
	StdInfo std; 	 		// 학생 객체 

	MyList femaleList; 		// 여학생 객체 저장 MyList
	MyList maleList;		// 남학생 객체 저장 MyList
	
	// 생성자
	GroupManager() {
		// rawDataList 리스트에 학생(StdInfo)객체 입력
		LoadData();
	}
	
	// 학생 정보 객체화 후 리스트에 저장
	void LoadData() {
		// RawData 클래스 멤버 변수의 자료들을
		// 각 StdInfo 객체로 생성 후 rawDataList 리스트에 추가
		rawDataList = new MyList();
		rawdata 	= new RawData();
		
		// rawdata의 학생 수만큼 RawData 클래스 멤버 변수의 자료들을 각 StdInfo 객체로 생성 후 rawDataList에 추가
		for(int i=0; i < rawdata.m_stdId.length; i++) {
			std = new StdInfo();
			rawdata = new RawData();
			std.setId(rawdata.m_stdId[i]);
			std.setName(rawdata.m_stdName[i]);
			std.setGrade(rawdata.m_stdGrade[i]);
			std.setGender(rawdata.m_stdGender[i]);
			
			rawDataList.add(std); 
		}
		
		// 리스트 rawDataList에 입력 된 학생 정보 화면에 출력
		PrtStdList();
	}
	
	// rawDataList 내 학생 정보(StdInfo 객체)를 화면에 출력
	void PrtStdList() {
		// 화면 출력 포맷 : 이름, 학번, 성별, 점수 순으로 출력 (아래 참조)
		// 1) 박나래, 1, "여", 10
		// 2) 이정재, 2, "남", 10
		for(int i=0; i < rawDataList.size(); i++) {
			StdInfo std = rawDataList.get(i);
			System.out.printf("%d) %s, %s, %s, %.0f\n",
					i+1, std.getName(), std.getId(), std.getGender(), std.getGrade());
		}
	}
	// 랜덤으로 그룹을 생성하여 화면에 출력
	void GenerateGroup() {
		// 생성 그룹 개수  사용자로부터 입력
		System.out.println("생성할 그룹 개수를 입력 해주세요.");
		m_numOfGroup = sc.nextInt(); 

		// 여학생 객체만 가진 리스트
		femaleList = new MyList(); // 여학생 객체만 저장할 리스트
		
		// 모든 학생 객체가 담긴 rawDataList에서 성별이 여자인 경우 femaleList에 해당 객체 추가
		for(int i=0; i < rawDataList.size(); i++) {
			if (rawDataList.get(i).getGender() == "여") {
				femaleList.add(rawDataList.get(i));
			}
		}
		
		// 남학생 객체만 가진 리스트
		maleList = new MyList(); // 남학생 객체만 저장할 리스트
		// 모든 학생 객체가 담긴 rawDataList에서 성별이 남자인 경우 maleList에 해당 객체 추가
		for(int i=0; i < rawDataList.size(); i++) {
			if (rawDataList.get(i).getGender() == "남") {
				maleList.add(rawDataList.get(i));
			}
		}
		
		// 아래 규칙을 적용하여 그룹 생성
		// 1) 남학생 중 성적 순으로 각 그룹에 랜덤하게 배정 :    
		//    예) 생성 그룹 : 3, 성적순으로 1~3위의 학생들을 랜덤하게 1, 2, 3조에 편성
		//    Math.Random() 메서드 이용 : 0~2 랜덤 값 생성 : (int)(Math.Random() * 3)
		// 2) 모든 남학생이 배정 될때까지 1) 반복
		
		// 사용자로 부터 입력받은 수만큼 그룹 배열 생성
		MyList group[] = new MyList[m_numOfGroup];
		for(int i=0; i < group.length; i++) {
			group[i] = new MyList();
		}
		
		// 1. 그룹수 받아서 랜덤 배열 생성
		// 2. 랜덤 배열을 임의의 수로 초기화 함 
		// 3. 변수에 랜덤 함수를 써서 숫자 넣음 -> 변수가 존재하는지 안하는지 중복제거
		// 4. 중복제거한 변수를 배열에 넣음
		// 5. 남자 리스트의 인덱스 번호에 들어감 
		// 6. 입력한 그룹수만큼 남자 객체 그룹 배열에 추가
		// 7. 그룹 수만큼 remove
		// 8. while 문으로 모든 남학생 넣을 때 까지 반복
		while(maleList.size() > maleList.size() % m_numOfGroup) {
			int ranList[] = new int[m_numOfGroup]; 
			for(int i = 0; i < ranList.length; i++) { // 랜덤함수 -1로 초기화 -> 중복을 피하기 위해
				ranList[i] = -1; 
			}
			
			Loop:
			for(int i=0; i < ranList.length; i++) {
				int value = (int)(Math.random() * m_numOfGroup); // 생성한 그룹 수 사이의 랜덤 수 저장
				for(int j=0; j < ranList.length; j++) {
					if(ranList[j] == value) { // 랜덤 수를 담을 배열에 중복이 생길 경우 continue Loop label에 의해 다시 Loop문으로 돌아감;
						i--;
						continue Loop;
					}
				}
				ranList[i] = value; // 중복이 없는 랜덤 수를 배열에 저장
			}
			
			// 남학생을 생성된 그룹에 랜덤으로 넣음
			for(int i=0; i < m_numOfGroup; i++) { // 입력받은 그룹수만큼 반복
				if(maleList.size() > 0) { // 남학생 리스트 내에 데이터가 있으면 그룹에 랜덤으로 남학생 객체 넣음
					group[i].add(maleList.get(ranList[i]));  
				}
			}
			// 그룹에 추가된 0번쨰 인덱스의 남학생 객체 삭제 하면서 다음 남학생 객체 불러옴
			for(int j = 0; j < ranList.length; j++) {
				maleList.remove(0);
			}
		}
		
		// 3) 남학생 숫자가 생성 그룹 수와 맞아 떨어지지 않을 경우, 남은 학생들은 각 조별 총점이 낮은 순으로 배정
		//    예) 총 학생 수 : 9명, 생성 그룹 : 2, 8명까지 배정 후 조별 점수 총합 -> 1조 총점 : 80점, 2조 총점 : 90점
		//       -> 1조 학생들의 총 점수가 낮음으로 나머지 1명을  1조에 배정
		while(maleList.size() > 0) {
			int g_num = 0; // 그룹 번호
			int g_low = 0; // 그룹별 가장 총점이 낮은 값
			int g_sum[] = new int[m_numOfGroup]; // 그룹별 총점 계산
			
			for(int i=0; i < group.length; i++)
				for(int j=0; j < group[i].size(); j++)
					g_sum[i] += group[i].get(j).getGrade(); // 그룹별 총점 계산
			
			g_low = g_sum[0]; // 총점이 제일 낮은 값 
			
			for(int i=0; i < g_sum.length; i++) {
				if(g_low > g_sum[i]) { // 제일 낮은 값이 총합보다 크면 해당 총합을 낮은 값으로 설정
					g_low = g_sum[i];
					g_num = i;
				}
			}
			group[g_num].add(maleList.get(0)); // 총점이 낮은 그룹에 남은 남학생 배정
			maleList.remove(0);
		}
		
		// 4) 여학생의 경우 1조부터 차례대로 Round Robin 방식으로 배정
        //    예) 생성 그룹 3, 여학생 수 5명 : 1, 2, 3, 1, 2조 순으로 배정
		while(femaleList.size() > 0) { 					// 여학생이 전부 그룹에 배정될떄까지 반복
			for(int i=0; i < m_numOfGroup; i++) { 		// 입력받은 그룹 수만큼 반복
				if(femaleList.size() > 0) {  			// 여학생 객체가 0이 아니면 
					group[i].add(femaleList.get(0));  	// 생성된 그룹배열에 여학생 추가
					femaleList.remove(0);				// 추가한 여학생 객체를 삭제함으로써 다음 노드의 여학생 객체를 그룹에 추가
				}
			}
		}
		
		// 조편성 결과 화면 출력
		System.out.printf("생성 그룹 개수 : %d \n", m_numOfGroup);
		System.out.println("남학생 수 : " + rawDataList.sizeOfMaleStd());
		System.out.println("여학생 수 : " + rawDataList.sizeOfFemaleStd());
		System.out.println("총 학생 수 : " + rawDataList.size());
		System.out.println("-------------------------------");
		System.out.println("-------------------------------");
		System.out.println("                   조 편성 결과");
		System.out.println("-------------------------------");
		// 출력 포맷은 시험지 참조
		for(int i = 0; i < m_numOfGroup; i++) {
			System.out.print("\t");
	         System.out.print(i+1 + " 조 멤버 목록\n");
	         for(int j = 0; j < group[i].size(); j++) {
	        	 System.out.print("\t\t");
	            System.out.print(j+1 + ") 학번 : " + group[i].get(j).getId() + "\t 이름 : " + group[i].get(j).getName() + "\t 성별 : " + group[i].get(j).getGender()+"\n");
	         }
	         System.out.println();
	      }
		
	}
}

public class GGenerator {
	public static void main(String args[]) {
		GroupManager myGrpMgr = new GroupManager();
		// 조 편성 프로그램 실행
		myGrpMgr.GenerateGroup();	
	}
}
