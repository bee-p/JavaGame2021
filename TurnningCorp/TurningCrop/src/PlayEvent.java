import java.util.Scanner;

/*
 * object: 방 내부에 있는 대표적 사물들 (부장 책상, 정돈된 책상 ,,,)
 * object 안의 item: 실제 아이템... (책상 위의 메모지, 일기장, 사탕 껍데기 ,,,)
 */


public class PlayEvent {
	// 스캐너 객체, 선택지 변수 생성
	private Scanner scan = new Scanner(System.in);
	private int num;
	
	// 타이틀 이동 판별
	private boolean goTitle;
	
	// 엔딩 판별할 때 사용하는 포인트 값
	int happyEndingPoint = 0;
	
	// Player, Map 객체
	// -> GameManager 클래스에서 PlayEvent 객체 생성할 때 집어넣기
	private Map[][] map;
	private Player player;
	
	
	// 생성자
	PlayEvent()
	{
		// 임시,,
	}
	PlayEvent(Map[][] map, Player player)
	{
		this.map = map;
		this.player = player;
	}
	
	// getter, setter 메소드
	public boolean getGoTitle()
	{
		return goTitle;
	}
	public void setGoTitle(boolean goTitle)
	{
		this.goTitle = goTitle;
	}
	
	
	// 층간 이동 보조 (엘리베이터)
	private void moveFloor()
	{
		while(true)
		{
			// 층 목록 보여주기
			// ex) 1층(활성화) \n ...
			for (int i = 0; i < 5; i++)
			{
				System.out.println(map[i][0].printActive());
			}
			
			System.out.println("이동할 층의 버튼을 누르자. (숫자 입력)");
			
			num = scan.nextInt();
			
			// 만일 누른 층이 비활성화 상태라면
			if (!map[num - 1][0].getActive())
			{	
				// 이동 실패, 다시 층 목록 출력으로 돌아감
				System.out.println("버튼이 눌리지 않는다.");
				continue;
			}
			
			if (num == 1)
			{
				player.setPosID(10);	// 모두 로비로 이동하는 것이기 때문에 모든 ID의 뒷 번호는 0이다.
				break;
			}
			else if (num == 2)
			{
				player.setPosID(20);
				break;
			}
			else if (num == 3)
			{
				player.setPosID(30);
				break;
			}
			else if (num == 4)
			{
				player.setPosID(40);
				break;
			}
			else if (num == 5)
			{
				player.setPosID(50);
				break;
			}
			else if (num == 666)
			{
				System.out.println("불가능하다.");
				continue;
			}
			else
			{
				System.out.println("그런 버튼은 없다.");
				continue;
			}
		}
	}
	
	
	// ----------------------------------------------------------------- \\
	
	
	// 1층 로비 이벤트 함수
	public void playFloor1_0()
	{
		while(true)
		{
			// 1. 로비 스크립트 출력
			// 로비다!
			
			// 2. 선택지 출력
			System.out.println("1. 출입기에 사원증을 찍자.");	// 저장
			System.out.println("2. 타이틀로 나가자.");			// 타이틀로 나가는 선택지가 있어야 되는데 지문을 어떻게...적어야 할지 모르겠음..
			System.out.println("3. 다른 곳을 둘러보자.");		// 다른 방 이동
			System.out.println("4. 엘리베이터를 타자.");		// 층 이동
			
			num = scan.nextInt();
			
			if (num == 1)			// 저장
			{
				// 현재 게임 데이터 로컬에 저장
				
				System.out.println("사원증을 성공적으로 찍었다!");
			}
			else if (num == 2)		// 저장 후 타이틀로 나가기
			{
				// 1. 현재 데이터 로컬에 저장
				
				// 2. 타이틀로 나가기
				goTitle = true;
				break;
			}
			else if (num == 3)		// 다른 방으로 이동
			{
				// 영업부, 카페, 경비실이 보인다. 어디로 들어갈까?
				System.out.println("1. 영업부로 들어가자.");
				System.out.println("2. 카페로 들어가자.");
				System.out.println("3. 경비실로 들어가자.");
				System.out.println("4. 생각이 달라졌다.");		// 다시 로비 선택지 출력
				
				num = scan.nextInt();
				
				if (num == 1)	// 영업부 들어가기
				{
					player.setPosID(11);
					break;
				}
				else if (num == 2)	// 카페 들어가기
				{
					player.setPosID(12);
					break;
				}
				else if (num == 3)	// 경비실 들어가기
				{
					player.setPosID(13);
					break;
				}
				else if (num == 4)	// 다른 방으로 가지 않기(로비 스크립트/선택지 다시 출력)
				{
					continue;
				}
				else
				{
					// 그 외 오기입 처리
				}
			}
			else if (num == 4)		// 다른 층으로 이동(엘리베이터 탑승)
			{
				// 1. 보스몬스터(케로베로스) 처치 판별
				// (처치하지 않았다면) 플레이어의 평판도 검사 -> 전투/그냥 물러남
				
				// 2. (처치했다면) 층 이동 메소드 수행
				moveFloor();
				break;
			}
			else
			{
				// 그 외 오기입 처리
			}
		}
	}
	
	// 1층 영업부(방1) 이벤트 함수
	public void playFloor1_1()
	{
		// 1. 배틀....확률 돌리기
		
		// 2. 영업부 스크립트 출력...
		// 영업부다. 내가 일하는 부서라 지긋하리만치 익숙할 만한데, 묘하게 서늘한 기운이 목을 감싼다. 밤이라 그런가.
		// 앞에는 익숙한 책상 배열들이 보인다. 개중에는 눈에 띄는 책상도 심심찮게 있다.

		while(true)
		{
			System.out.println("어디를 살펴볼까?");
			
			// for문으로 해당 방의 조사 사물 배열 목록 (숫자와 함께) 출력하기..
			for (int i = 0; i < map[0][1].getAllObject().length; i++)
			{	// ex) 1. 내 책상
				System.out.println((i + 1) + ". " + map[0][1].getObject(i).getObjectName());
			}
			// [마지막 번호]. 로비로 나간다.
			System.out.println((map[0][1].getAllObject().length + 1) + ". 로비로 나간다.");
			System.out.println("(원하는 선택지의 숫자 입력)");
			
			num = scan.nextInt();
			
			if (num == 1)		// 부장 책상
			{
				// 부장님의 책상이다.
				System.out.println(map[0][1].getObject(0).getObjectName() + "이다.");
				// 부장 책상 설명 출력
				System.out.println(map[0][1].getObject(0).getDescription());
				
				/* 
				 * 이 부분을 따로 빼서 만드는 게 좋을지,,고민중
				 * 만약 빼지 않는다면 그냥 "~가 있다",,의 형식으로 수정 필요
				 */
				
				// 책상 위에는 메모지(이)가 있다.
				System.out.println("\n책상 위에는 " + map[0][1].getObject(0).getItem(0).getName() + "(이)가 있다.");
				
				System.out.println("자세히 볼까?");
				System.out.println("1. 본다.");
				System.out.println("2. 안 본다.");
				
				num = scan.nextInt();
				
				if (num == 1)		// 본다
				{
					System.out.println("'" + map[0][1].getObject(0).getItem(0).getName() + "'");
				}
				else if (num == 2)	// 안 본다
				{
					System.out.println("안 보기로 했다.");
				}
				else				// 오기입
				{
					System.out.println("그런 선택지는 없다.");
				}
				
				continue;
			}
			else if (num == 2)	// 수상한 사원의 책상
			{
				
			}
			else if (num == 3)	// 어지러운 책상
			{
				
			}
			else if (num == 4)	// 정돈된 책상
			{
				
			}
			else if (num == 5)	// 배부른 책상.....?
			{
				
			}
			else if (num == 6)	// 플레이어의 책상
			{
				
			}
			else if (num == 7)	// 로비로 나가기
			{	
				// 로비로 위치 재조정
				player.setPosID(10);
				break;
			}
			else 				// 오기입
			{
				
			}
		}
	}
	
	// 1층 카페(방2) 이벤트 함수
	public void playFloor1_2()
	{
		
	}
	
	// 1층 경비실(방3) 이벤트 함수
	public void playFloor1_3()
	{
		
	}
	
	// 1층 화장실(posID: 14) 이벤트 함수
	public void playFloor1_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 2층 로비 이벤트 함수
	public void playFloor2_0()
	{
		
	}
	
	// 2층 식당(방1) 이벤트 함수
	public void playFloor2_1()
	{
		
	}
	
	// 2층 영양사 사무실(방2) 이벤트 함수
	// 식당 내부에 영양사 사무실 존재
	public void playFloor2_2()
	{
		
	}
	
	// 2층 화장실(posID: 24) 이벤트 함수
	public void playFloor2_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 3층 로비 이벤트 함수
	public void playFloor3_0()
	{
		
	}
	
	// 3층 개발실1(방1) 이벤트 함수
	public void playFloor3_1()
	{
		
	}
	
	// 3층 개발실2(방2) 이벤트 함수
	public void playFloor3_2()
	{
		
	}
	
	// 3층 기술부(방3) 이벤트 함수
	public void playFloor3_3()
	{
		
	}
	
	// 3층 화장실(posID: 34) 이벤트 함수
	public void playFloor3_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 4층 로비 이벤트 함수
	public void playFloor4_0()
	{
		
	}
	
	// 4층 (방1) 이벤트 함수
	public void playFloor4_1()
	{
		
	}
	
	// 4층 (방2) 이벤트 함수
	public void playFloor4_2()
	{
		
	}
	
	// 4층 (방3) 이벤트 함수
	public void playFloor4_3()
	{
		
	}
	
	// 4층 화장실(posID: 44) 이벤트 함수
	public void playFloor4_4()
	{
		
	}
	
	
	// * * * * * * * * * * * * * \\
	
	
	// 5층 이벤트 함수(엔딩)
	public void playFloor5()
	{
		// 엔딩화면
		// 	=> 1. 배드엔딩: 회로선을 다 모으지 못한 경우
		//	=> 2. 노말엔딩: 회로선까지 다 모아서 사장을 성불
		// 	=> 3. 진엔딩: 평판도를 다 올려서 사장식에 오름
		// 	===> 엔딩 판별은 각 아이템별로 포인트를 부여하여, 일정 포인트를 얻으면 엔딩 보기 가능
		// 	===> 포인트는 맵에서 아이템 포인트 더해주면 됨.
		
//					if(player.getCurrentRank() == 'S'){
//						// 트루엔딩
//						// 파일에서 불러오기(읽기)
//					}
//					else if(happyEndingPoint == 3){
//						//해피엔딩
//						// 파일에서 불러오기
//					}
//					else{
//						//노말 엔딩
//						// 파일에서 불러오기
//					}
	}
}
